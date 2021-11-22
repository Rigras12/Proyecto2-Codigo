package Cajero;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class OpcionesCompraUP extends JPanel implements ActionListener {
	JButton quitarProducto;
	JButton aniadirProducto;
	JTextField cantidad;
	JLabel fondo;
	VentanaCompra principal;
	public OpcionesCompraUP(VentanaCompra principall,Color color,Font fuente) {
		principal = principall;
		setBackground(color);
		setLayout(new GridLayout(2,2));
		fondo = new JLabel("Cantidad a comprar:");
		fondo.setBackground(color);
		add(fondo);
		
		aniadirProducto = new JButton("Añadir producto");
		aniadirProducto.addActionListener(this);
		aniadirProducto.setActionCommand("Aniadir producto");
		aniadirProducto.setFont(fuente);
		add(aniadirProducto);
		
		cantidad = new JTextField("0");
		
		add(cantidad);
		
		
		quitarProducto = new JButton("Quitar producto");
		quitarProducto.addActionListener(this);
		quitarProducto.setActionCommand("Quitar producto");
		quitarProducto.setFont(fuente);
		add(quitarProducto);
		
		
		
	}
	
	
	public int getCantidad() {
		return Integer.parseInt(cantidad.getText());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals("Aniadir producto")) {
			principal.aniadirProducto();
		}
		else if (comando.equals("Quitar producto")) {
			principal.quitarProducto();
			
		}
		
	}
}
