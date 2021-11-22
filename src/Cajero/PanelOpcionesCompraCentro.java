package Cajero;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
public class PanelOpcionesCompraCentro extends JPanel {
	private VentanaCompra principal;
	private OpcionesCompraUP panelup;
	private OpcionesCompraDOWN paneldn;
	public PanelOpcionesCompraCentro(VentanaCompra principall,Color color, Font fuente) {
		
		principal = principall;
		panelup = new OpcionesCompraUP(principal, color,fuente);
		paneldn = new OpcionesCompraDOWN(principal, color,fuente);
		setLayout(new GridLayout(2,1));
		add(panelup);
		add(paneldn);
	}
	public int getCantidad() {
		// TODO Auto-generated method stub
		return panelup.getCantidad();
	}
	public void setPuntos(int puntos) {
		// TODO Auto-generated method stub
		paneldn.setPuntos(puntos);
	}
	public void setTotal(int total) {
		// TODO Auto-generated method stub
		paneldn.setTotal(total);
	}
	
	

}
