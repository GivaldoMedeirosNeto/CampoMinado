package br.com.cod3r.cm.visao;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import br.com.cod3r.cm.modelo.Campo;
import br.com.cod3r.cm.modelo.CampoEvento;
import br.com.cod3r.cm.modelo.CampoObservador;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador, MouseListener{
	
	private Campo campo;
	private final Color BG_PADRAO = new Color(184, 184, 184);
	private final Color BG_EXPLODIR = new Color(189, 66, 68);
	private final Color TEXTO_VERDE = new Color(0, 100, 0);
	private ImageIcon bandeira;
		
	
	public BotaoCampo (Campo campo) {
		this.campo = campo;
		setBorder(BorderFactory.createBevelBorder(0));
		//setBackground(BG_PADRAO);
		
		
		addMouseListener(this);
		campo.registrarObservador(this);
	}
	
	@Override
	public void eventoOcorreu(Campo campo, CampoEvento evento) {
		switch(evento) {
		case ABRIR:
			aplicarEstiloAbrir();
			break;
			
		case MARCAR:
			aplicarEstiloMarcar();
			break;
			
		case EXPLODIR:
			aplicarEstiloExplodir();
			break;
		default:
			aplicarEstiloPadrao();
		}
		
		SwingUtilities.invokeLater(() -> {
			repaint();
			validate();
		});
				
	}

	private void aplicarEstiloPadrao() {
		setBackground(null);
		setBorder(BorderFactory.createBevelBorder(0));
		setText("");
		setIcon(null);
	}

	private void aplicarEstiloExplodir() {
		setBackground(BG_EXPLODIR);
		setForeground(Color.WHITE);
		setIcon(imagem("e"));
	}

	private void aplicarEstiloMarcar() {
		setIcon(imagem("m"));
		setForeground(Color.BLACK);
	}

	private void aplicarEstiloAbrir() {
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if(campo.isMinado()) {
			setBackground(BG_EXPLODIR);
			return;
		}
		
		setBackground(BG_PADRAO);
			
		
		switch (campo.minasNaVizinhaca()) {
		case 1:
			setForeground(TEXTO_VERDE);
			break;
		case 2:
			setForeground(Color.BLUE);
			break;
		case 3:
			setForeground(Color.YELLOW);
			break;
		case 4, 5, 6:
			setForeground(Color.RED);
			break;
		default:
			setForeground(Color.PINK);
		}
		
		String valor = !campo.vizinhazaSegura() ? campo.minasNaVizinhaca() + "" : "";
		setText(valor);
		
	}

	//Interface Eveto do Mouse
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1) {
			campo.abrir();
		} else {
			campo.alternarMarcacao();
		}
	}
	
	public ImageIcon imagem(String tipo) {
		if(tipo.equalsIgnoreCase("m")) {
			bandeira = new ImageIcon(BotaoCampo.class.getResource("flag 48x48.png"));
		} else if(tipo.equalsIgnoreCase("e")){
			bandeira = new ImageIcon(BotaoCampo.class.getResource("Explosion 48x48.png"));
		}
		
		Image image = bandeira.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		bandeira = new ImageIcon(image);		
		return bandeira;
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
}
