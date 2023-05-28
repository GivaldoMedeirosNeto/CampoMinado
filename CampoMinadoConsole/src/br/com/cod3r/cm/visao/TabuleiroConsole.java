package br.com.cod3r.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.cod3r.cm.excecao.ExplosaoException;
import br.com.cod3r.cm.excecao.SairException;
import br.com.cod3r.cm.modelo.Tabuleiro;

public class TabuleiroConsole {
	
	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		execultarJogo();
	}

	private void execultarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar){
				
				cicloJogo();
				
				System.out.println("Deseja uma outra partida? (S/N)");
				String resposta = entrada.nextLine();
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();
				}
			}
		} catch (Exception e) {
			System.out.println("Foi bom telo por aqui.");
		} finally {
			entrada.close();
		}
	}

	private void cicloJogo() {
		try {
			
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
				String digitado = valorDigitado("Digite (x,y): ");
				
				Iterator<Integer> xy = Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e.trim())).iterator();
				digitado = valorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
				
				if("1".equals(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				} else if ("2".equals(digitado)) {
					tabuleiro.alternarMarcacao(xy.next(), xy.next());
				}
			}
			
			System.out.println(tabuleiro);
			System.out.println("Você ganhou!!!");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Infelizmente não foi desta vez.");
		}
	}
	
	private String valorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
	}

}
