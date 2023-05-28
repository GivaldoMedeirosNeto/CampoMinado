package br.com.cod3r.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class CampoTeste {
	
	private Campo campo;
	
	@BeforeEach
	void iniciCampo() {
		campo = new Campo(3,3);
	}
	
	/* Teste Vizinhos */
	@Test
	void testeVizinhoEsquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.addVizinho(vizinho);
		
		assertTrue(resultado);
	}	
	
	@Test
	void testeVizinhoDireta() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.addVizinho(vizinho);
		
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoCima() {
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.addVizinho(vizinho);
		
		assertTrue(resultado);
	}	
	
	@Test
	void testeVizinhoBaixo() {
		Campo vizinho = new Campo(4,3);
		boolean resultado = campo.addVizinho(vizinho);
		
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoEsquerdaCima() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.addVizinho(vizinho);
		
		assertTrue(resultado);
	}	
	
	@Test
	void testeVizinhoDireitaCima() {
		Campo vizinho = new Campo(2, 4);
		boolean resultado = campo.addVizinho(vizinho);
		
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoEsquerdaBaixa() {
		Campo vizinho = new Campo(4,2);
		boolean resultado = campo.addVizinho(vizinho);
		
		assertTrue(resultado);
	}	
	
	@Test
	void testeVizinhoDireitaBaixo() {
		Campo vizinho = new Campo(4,4);
		boolean resultado = campo.addVizinho(vizinho);
		
		assertTrue(resultado);
	}
	
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1,1);
		boolean resultado = campo.addVizinho(vizinho);
		
		assertFalse(resultado);
	}
	
	@Test
	void testeAbrirVizinhos() {
		Campo campo11 = new Campo(1,1);
		Campo campo22 = new Campo(2,2);
		
		campo22.addVizinho(campo11);
		campo.addVizinho(campo22);		
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isAberto());
	}
	
	@Test
	void testeAbrirVizinhos2() {
		Campo campo11 = new Campo(1,1);
		Campo campo12 = new Campo(1,2);
		campo12.minar();
		Campo campo22 = new Campo(2,2);
		
		campo22.addVizinho(campo11);
		campo22.addVizinho(campo12);
		campo.addVizinho(campo22);		
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isFechado());
	}

	/*Teste Marcação*/
	@Test
	void testeValorMarcado() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	
	/*Teste Abrir*/
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
	}
	
}
