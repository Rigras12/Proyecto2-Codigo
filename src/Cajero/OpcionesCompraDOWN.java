package Cajero;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class OpcionesCompraDOWN extends JPanel implements ActionListener {
	private VentanaCompra principal;
	private JButton Finalizar;
	private JLabel total;
	private JLabel puntos;
	private int totalcompra;
	private int totalpuntos;
	
	
	public OpcionesCompraDOWN(VentanaCompra principall,Color color,Font fuente) {
		principal = principall;
		setLayout(new GridLayout(4,2));
		setBackground(color);
		
		add(new JLabel("Total a pagar"));
		
		add(new JLabel(""));
		
		total = new JLabel("0");
		total.setBackground(Color.WHITE);
		total.setOpaque(true);
		add(total);
		
		add(new JLabel(""));
		
		add(new JLabel("Puntos a ganar"));
		
		Finalizar = new JButton("Finalizar compra");
		Finalizar.addActionListener(this);
		Finalizar.setActionCommand("Finalizar");
		add(Finalizar);
		
	
		
		puntos = new JLabel("0");
		puntos.setBackground(Color.WHITE);
		puntos.setOpaque(true);
		add(puntos);
		
		
		
		
		
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals("Finalizar")) {
			principal.finalizarCompra();
		}
		
	}
	
	public void setPuntos(int puntosactuales) {
		puntos.setText(Integer.toString(puntosactuales));
	}
		
	public void setTotal(int totalactual) {
		total.setText(Integer.toString(totalactual));
	}
	

	
}