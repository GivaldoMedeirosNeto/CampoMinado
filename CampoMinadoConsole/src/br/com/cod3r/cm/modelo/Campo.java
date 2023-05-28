package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class Campo {	
	
	private final int linha;
	private final int coluna;
	
	private boolean minado = false;	
	private boolean aberto = false;
	private boolean marcado = false;
	
	private List<Campo> vizinhos = new ArrayList<>();
	
	Campo(int linha, int coluna){		
		this.linha = linha;
		this.coluna = coluna;		
	}
	
	boolean addVizinho(Campo vizinho) {
		
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaGeral = deltaLinha + deltaColuna;
		
		if(deltaGeral == 1 && !diagonal) {			
			vizinhos.add(vizinho);
			return true;			
		} else if(deltaGeral == 2 && diagonal) {			
			vizinhos.add(vizinho);
			return true;			
		} else {			
			return false;			
		}
		
	}
	
	void alternarMarcacao() {
		if(!aberto) {
			marcado = !marcado;
		}
	}
	
	public boolean isMarcado() {
		return marcado;
	}
	
	void minar() {
		minado = true;
	}
	
	public boolean isMinado() {
		return minado;
	}
	
	public boolean isAberto() {
		return aberto;
	}
	
	void setAberto(boolean aberto) {
		this.aberto = aberto;
	}
	
	public boolean isFechado() {
		return !aberto;
	}
	
	boolean abrir() {		
		if(!aberto && !marcado) {
			aberto = true;			
			
			if(minado) {
				throw new ExplosaoException();
			}
			
			if(vizinhazaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			
			return true;
			
		} else {
			
			return false;
			
		}				
	}
	
	boolean vizinhazaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}

	public int getLinha() {
		return linha;
	}
	
	public int getColuno() {
		return coluna;
	}
	
	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	
	long minasNaVizinhaca() {
		return vizinhos.stream().filter(v -> v.minado).count();
	}

	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}

	public String toString() {
		if(marcado) {
			return "!";
		} else if (aberto && minado) {
			return "*";
		} else if (aberto && minasNaVizinhaca() > 0) {
			return Long.toString(minasNaVizinhaca());
		} else if (aberto) {
			return " ";
		} else {
			return "?";
		}
	}
	
}
