package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import Inventario.Producto;

public class PanelProductos extends JPanel {
	
	private DefaultListModel<ListaProducto> dmLote;
	private ListaProducto producto;
	public PanelProductos(PanelPrincipalInventario principal1,Color color,Font font, ArrayList productos,int lotes) {
		// TODO Auto-generated constructor stub
		setBorder(new TitledBorder("Productos "));
		setLayout(new GridLayout(2,1));
		
		JListProductos listproductos=new JListProductos(principal1,color,font,productos);
		JScrollPane scroll = new JScrollPane(listproductos.getLista(),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
		
		
		 dmLote =new DefaultListModel<ListaProducto>();
		JList<ListaProducto> listaLote=new JList<ListaProducto>();
		
		
		String info="";
		
		ImageIcon img = new ImageIcon("./data/lote.png");
		Image image = img.getImage(); 
		Image newimg = image.getScaledInstance(100, 50,  java.awt.Image.SCALE_SMOOTH); 
		img = new ImageIcon(newimg);  
		
		
		for(int i=0;i<lotes;i++)
		{
			info= "Lote : " + i;
			producto=new ListaProducto(color, font, info, i, img);
			dmLote.addElement(producto);
		}
		
		listaLote.setCellRenderer(new Renderer(principal1,2));
		listaLote.setModel(dmLote);
		listaLote.setPreferredSize(new Dimension(100,100));
		
		JScrollPane scrollLote=new JScrollPane(listaLote,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scrollLote);
		
	}
	
	public void actualizarLotes(int numero)
	{	
		int size=dmLote.getSize();
		int cantidad=size - numero ;
		
		if (cantidad>0)
		{
		for(int i=0;i<cantidad;i++)
		{
			dmLote.remove(size-(i+1));
		}
		} 
		
		else
		{
			cantidad=numero-size;
			for(int i=0;i<cantidad;i++)
			{	
				String info="Lote : " + (i+size);
				ListaProducto productito=new ListaProducto(producto.getColor(),producto.getFont(),info,i+size,producto.getIcon());
				dmLote.addElement(productito);
			}
		}
	}

}