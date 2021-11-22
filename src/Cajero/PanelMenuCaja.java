package Cajero;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class PanelMenuCaja extends JPanel implements ActionListener {
	JButton Comprar;
	JButton Registrar;
	JButton ConsultarPuntos;
	JButton GuardarySalir;
	VentanaCajero principal;
	String []  textos = {"REALIZAR COMPRA","REGISTRAR CLIENTE","CONSULTAR PUNTOS","GUARDAR Y SALIR"};
	public PanelMenuCaja(VentanaCajero principall, Font font, Color color) {
		principal = principall;
		setLayout(new GridLayout(8,3));
		setBackground(color);
		
		
		
		JButton [] botones = {Comprar,Registrar,ConsultarPuntos,GuardarySalir};
		
		for (int i=0;i<4;i++) {
			botones[i] = new JButton(textos[i]);
			botones[i].addActionListener(this);
			botones[i].setActionCommand(textos[i]);
			add(botones[i]);
			add(new JLabel(""));
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando=e.getActionCommand();
		if(comando.equals(textos[0])) {
			principal.registrarCompra();
		}
		else if (comando.equals(textos[1])) {
			principal.registrarUsuario();
		}
		else if (comando.equals(textos[2])) {
			principal.consultarPuntos();
		}
		else if (comando.equals(textos[3])) {
			principal.guardarySalir();
		}
	}

}
