package Cajero;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

public class PanelSuperiorCajero extends JPanel {
	private JLabel lblFecha;
	private JLabel lblTotal;
	
	public PanelSuperiorCajero (String fecha, String texto) {
		setBorder(new TitledBorder("Información general"));
		setLayout(new GridLayout(0,3));
		lblFecha=new JLabel("fecha : " + fecha);
		lblTotal=new JLabel(texto);
		add(lblFecha);
		add(lblTotal);
	}
	
	
	
public void setFecha(String fechap)
	
	{
		lblFecha.setText("Fecha : " + fechap);
		
	}
}
