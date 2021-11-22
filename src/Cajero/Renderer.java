package Cajero;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import Cajero.ListaProducto;

public class Renderer  extends DefaultListCellRenderer implements ListCellRenderer<Object>{
	private VentanaCompra ventana;
	public Renderer(VentanaCompra ventana) {
		// TODO Auto-generated constructor stub
		this.ventana=ventana;
	}
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		ListaProducto object=(ListaProducto) value;
		setEnabled(true);
		
		setText(object.getInfo());
		setFont(object.getFont());
		setForeground(Color.BLACK);
		setIcon(object.getIcon());
		
		if(isSelected)
		{
			setBackground(Color.LIGHT_GRAY);
		}
		else
		{
			setBackground(Color.white);
		}
		return this;
	}
}
