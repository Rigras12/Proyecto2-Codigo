package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JScrollPane;

import Inventario.Producto;

public class JListProductos {
	private JList<ListaProducto> lista;
	public JListProductos(PanelPrincipalInventario principal1,Color color,Font font, ArrayList productos) {
		// TODO Auto-generated constructor stub
		DefaultListModel<ListaProducto> dm =new DefaultListModel<ListaProducto>();
		lista=new JList<ListaProducto>();
		
		ArrayList<Producto> temp= productos;
		String info="";
		for(int i=0;i<temp.size();i++)
		{
			info= i+1 + ".) Codigo : " + temp.get(i).getCodigo() ; 
			ImageIcon img = new ImageIcon("./data/" +temp.get(i).getCodigo()+".png");
			Image image=img.getImage();
			Image newi=image.getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
			img=new ImageIcon(newi);
			
			dm.addElement(new ListaProducto(color,font,info,temp.get(i).getCodigo(),img));
		}
		
		lista.setCellRenderer(new Renderer(principal1,1));
		lista.setModel(dm);
		lista.setPreferredSize(new Dimension(250,150));
	}
	
	public JList<ListaProducto> getLista()
	{
		return lista;
	}

}
