package Cajero;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import pos.supermercado;
public class VentanaRegistro extends JFrame {
	private PanelSuperiorCajero panelsuperior;
	private panelCentralregistros panelcentral;
	private supermercado mercado;
	private Color color1;
	private Color color2;
	private Font fuente;
	public VentanaRegistro(supermercado mercados, String fecha) {
		setSize(600,300);
		setLayout(new BorderLayout());
		mercado = mercados;
		color1 = new Color(46,139,87);
		color2 = new Color(143,188,143);
		fuente = new Font("Serif", Font.BOLD, 18);
		panelsuperior = new PanelSuperiorCajero(fecha,"Registro cliente");
		add(panelsuperior, BorderLayout.NORTH);
		panelcentral = new panelCentralregistros(this, color1,color2, fuente); 
		add(panelcentral,BorderLayout.CENTER);
	}
	public void finalizarRegistro() {
		// TODO Auto-generated method stub
		if (panelcentral.getCivil() & panelcentral.getLaboral() & panelcentral.getGenero()!="") {
			//(int numeroCedula,int edad, char sexo,String estadoCivil, String situacionLaboral)
			mercado.agregarCliente(panelcentral.getCedula(), 34, "M".charAt(0), "Soltero", "Empleado");
			JOptionPane.showMessageDialog(this, "Registrado correctamente");
			this.getDefaultCloseOperation();
		}
	}

}
