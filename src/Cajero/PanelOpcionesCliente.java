package Cajero;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
public class PanelOpcionesCliente extends JPanel {
	private OpcionesCliente1 panel1;
	private OpcionesCliente2 panel2;
	
	private VentanaCompra principal;
	public PanelOpcionesCliente(VentanaCompra principall, Color color, Font fuente) {
		principal = principall;
		setLayout(new GridLayout(1,3));
		setBackground(color);
		panel1 = new OpcionesCliente1(principal,color);
		panel2 = new OpcionesCliente2(color,fuente);
		
		add(panel1);
		add(panel2);
		
		
	}
	
	
	
	public void setPuntostotales(int puntos) {
		panel2.setPuntostotales(puntos);
	}
	
	public void setComprastotales(int compras) {
		panel2.setComprastotales(compras);
	}
	
	public int getCedula() {
		return panel1.getCedula();
	}

}
