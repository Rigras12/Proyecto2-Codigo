package Cajero;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Registroclienteder extends JPanel implements ActionListener {
	private panelchecks situacionciv;
	private panelchecks situacionlab;
	private JButton finalizar;
	private VentanaRegistro principal;
	
	public Registroclienteder(VentanaRegistro principall, Color color, Font fuente) {
		principal = principall;
		setBackground(color);
		setLayout(new GridLayout(7,1));
		situacionciv = new panelchecks("Soltero","Casado",color,fuente);
		situacionciv.setBackground(color);
		situacionlab = new panelchecks("Empleado","Desempleado",color,fuente);
		situacionlab.setBackground(color);
		
		finalizar = new JButton("Finalizar");
		finalizar.addActionListener(this);
		finalizar.setActionCommand("Finalizar");
		
		
		add(new JLabel(""));
		add(situacionciv);
		add(new JLabel(""));
		add(situacionlab);
		add(new JLabel(""));
		add(finalizar);
		add(new JLabel(""));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		if (comando.equals("Finalizar")) {
			principal.finalizarRegistro();
		}
	}

	public boolean getLaboral() {
		// TODO Auto-generated method stub
		if (situacionlab.getprimera() & situacionlab.getsegunda()==false) {
			return true;
		}
		else if (situacionlab.getsegunda() & situacionlab.getprimera()==false) {
			return true;
		}
		else {
			return false;
		}
			
		
	}

	public boolean getCivil() {
		// TODO Auto-generated method stub
		if (situacionciv.getprimera() & situacionciv.getsegunda()==false) {
			return true;
		}
		else if (situacionciv.getsegunda() & situacionciv.getprimera()==false) {
			return true;
		}
		else {
			return false;
		}
	}
}
