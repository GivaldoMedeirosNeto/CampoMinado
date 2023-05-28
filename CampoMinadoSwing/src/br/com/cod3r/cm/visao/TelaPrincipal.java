package br.com.cod3r.cm.visao;

import java.awt.Toolkit;

import javax.swing.JFrame;

import br.com.cod3r.cm.modelo.Tabuleiro;


@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame{
	
	public TelaPrincipal() {
		//Tabuleiro tabuleiro = new Tabuleiro(16, 30, 5);
		Tabuleiro tabuleiro = new Tabuleiro(16, 30, 50);
		//Tabuleiro tabuleiro = new Tabuleiro(16, 30, 479);
		add(new PainelTabuleiro(tabuleiro));
		
		setTitle("Campo Minado - Givaldo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("bombs 48x48.png")));
		setSize(690, 438);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TelaPrincipal();
		
	}
	
}
