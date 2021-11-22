package Cajero;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class OpcionesCliente1p2 extends JPanel implements ActionListener {
	VentanaCompra principal;
	JButton Bingresar;
	JButton Bregistrar;
	
	
	public OpcionesCliente1p2(VentanaCompra principall, Color color) {
		principal = principall;
		setBackground(color);
		setLayout(new GridLayout(1,2));
		
		Bingresar = new JButton("Ingresar");
		Bingresar.addActionListener(this);
		Bingresar.setActionCommand("Ingresar");
		add(Bingresar);
		
		Bregistrar = new JButton("Registrar");
		Bregistrar.addActionListener(this);
		Bregistrar.setActionCommand("Registrar");
		add(Bregistrar);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		if (comando.equals("Ingresar")) {
			principal.ingresarCliente();
		}
		else if (comando.equals("Registrar")) {
			principal.registrarCliente();
		}
	}

}
