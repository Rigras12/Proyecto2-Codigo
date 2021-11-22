package Cajero;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;
public class OpcionesCliente1 extends JPanel{
	private VentanaCompra principal;
	private OpcionesCliente1p1 panel1;
	private OpcionesCliente1p2 panel2;
	
	public OpcionesCliente1(VentanaCompra principall, Color color) {
		principal = principall;
		panel1 = new OpcionesCliente1p1(color);
		panel2 = new OpcionesCliente1p2(principal,color);
		setBackground(color);
		
		setLayout(new GridLayout(2,1));
		add(panel1);
		add(panel2);
	}
	
	
	public int getCedula() {
		return panel1.getCedula();
	}

}
