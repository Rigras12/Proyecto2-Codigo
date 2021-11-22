package Cajero;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
public class OpcionesCliente2 extends JPanel{
	private VentanaCompra principal;
	private JLabel LtotalCompras;
	private JLabel Lpuntoscliente;
	
	public OpcionesCliente2(Color col, Font fuente) {
		
		setLayout(new GridLayout(5,1));
		setBackground(col);
		LtotalCompras = new JLabel("0");
		LtotalCompras.setFont(fuente);
		LtotalCompras.setBackground(Color.WHITE);
		LtotalCompras.setOpaque(true);
		
		Lpuntoscliente = new JLabel("0");
		Lpuntoscliente.setFont(fuente);
		Lpuntoscliente.setBackground(Color.WHITE);
		Lpuntoscliente.setOpaque(true);
		add(new JLabel("Total de compras cliente:"));
		
		add(LtotalCompras);
		
		add(new JLabel("Total de puntos cliente:"));
		
		add(Lpuntoscliente);
		
		add(new JLabel(""));
		
		
		
	}
	
	
	public void setPuntostotales(int puntos) {
		Lpuntoscliente.setText(Integer.toString(puntos));
	}
	
	public void setComprastotales(int compras) {
		LtotalCompras.setText(Integer.toString(compras));
	}
}
