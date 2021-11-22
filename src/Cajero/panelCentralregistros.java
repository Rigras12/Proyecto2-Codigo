package Cajero;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
public class panelCentralregistros extends JPanel {
	private VentanaRegistro principal;
	private Registroclienteizq panelizq;
	private Registroclienteder panelder;
	
	public panelCentralregistros( VentanaRegistro principall,Color color1,Color color2,Font fuente) {
		setLayout(new GridLayout(1,2));
		principal = principall;
		panelizq = new Registroclienteizq(color1,fuente);
		panelder = new Registroclienteder(principal,color2,fuente);
		add(panelizq);
		add(panelder);
	}
	
	
	public int getCedula() {
		return panelizq.getCedula();
	}
	
	public int getEdad() {
		return panelizq.getEdad();
	}
	
	public String getGenero() {
		return panelizq.getGenero();
	}
	
	public boolean getLaboral() {
		return panelder.getLaboral();
	}
	public boolean getCivil() {
		return panelder.getCivil();
	}
}
