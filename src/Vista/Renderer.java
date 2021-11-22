package Vista;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class Renderer extends DefaultListCellRenderer implements ListCellRenderer<Object> {
	
	private PanelPrincipalInventario panel;
	private int opcion;
	
	public Renderer(PanelPrincipalInventario panel,int opcion)
	{
		this.panel=panel;
		this.opcion=opcion;
	}
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{	
		
		ListaProducto object=(ListaProducto) value;
		
		setEnabled(true);
		
		setText(object.getInfo());
		setFont(object.getFont());
		setForeground(object.getColor());
		setIcon(object.getIcon());
		
		
		
		//label.setIcon(object.getIcon());
		
		
		
		if(opcion==1)
		{
			if(isSelected)
			{
				setBackground(Color.lightGray);
				System.out.println(object.getCode());
				panel.escogerProducto(object.getCode());
				
			}
			
			else
			{
				setBackground(Color.WHITE);
			}
		}
		
		else
		{
			if(isSelected)
			{
				setBackground(Color.lightGray);
				System.out.println(object.getCode());
				panel.escogerLote(object.getCode());
				
			}
			else
			{
				setBackground(Color.WHITE);
			}
		}
		
		
		return this;
	};

}
