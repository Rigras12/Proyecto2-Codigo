package Cajero;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
public class Registroclienteizq extends JPanel {
	private JTextField cedula;
	private JTextField edad;
	private panelchecks genero;
	
	
	public Registroclienteizq(Color color,Font fuente) {
		setLayout(new GridLayout(7,1));
		setBackground(color);
		
		add(new JLabel("Digite la cedula:"));
		
		
		cedula = new JTextField("");
		cedula.setBackground(Color.WHITE);
		cedula.setOpaque(true);
		add(cedula);
		add(new JLabel(""));
		
		add(new JLabel("Digite su edad:"));
		
		edad = new JTextField("");
		edad.setBackground(Color.WHITE);
		edad.setOpaque(true);
		add(edad);
		add(new JLabel(""));
		
		genero = new panelchecks("Hombre","Mujer",color,fuente);
		add(genero);
		
		
	}


	public int getCedula() {
		// TODO Auto-generated method stub
		
		return Integer.parseInt(cedula.getText());
		
		
	}
	
	public int getEdad() {
		return Integer.parseInt(edad.getText());
	}
	
	
	public String getGenero() {
		if (genero.getprimera() & genero.getsegunda()==false) {
			return "H";
		}
		else if (genero.getsegunda() & genero.getprimera()==false){
			return "M";
		}
		else {
			return "";
		}
	}

}
