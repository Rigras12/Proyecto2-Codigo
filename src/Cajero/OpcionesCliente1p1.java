package Cajero;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;
public class OpcionesCliente1p1 extends JPanel   {
	
	private JTextField cedula;
	public OpcionesCliente1p1( Color color) {
		setBackground(color);
		setLayout(new FlowLayout());
		cedula = new JTextField("Ingrese la cedula");
		
		add(cedula);
	}
	
	
	public int getCedula() {
		return Integer.parseInt(cedula.getText());
	}
}
