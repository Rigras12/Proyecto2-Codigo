package Cajero;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import Cajero.ListaProducto;
import Cajero.Renderer;
public class PanelOpcionesCompra extends JPanel{
	
	private VentanaCompra principal;
	private PanelOpcionesCompraCentro panelopciones;
	private JList<ListaProducto> lista;
	private DefaultListModel<ListaProducto> dm ;
	private Color color;
	private Font font;
	public PanelOpcionesCompra(VentanaCompra principall, Color color,Font fuente) {
		principal = principall;
		this.color=color;
		font=fuente;
		setLayout(new GridLayout(1,2));
		 dm =new DefaultListModel<ListaProducto>();
		 lista=new JList<ListaProducto>();
		
		HashMap<Integer,String[]> productos=principal.getSupermercado().getListaproductos();
		ArrayList<String[]> codigos=new ArrayList(productos.values());
		String info="";
		for(int i=0;i<codigos.size();i++)
		{	
			
			info= i+1 + ".) Codigo : " + codigos.get(i)[0] + " Cantidad : "+ codigos.get(i)[1]+ " Coste : "+codigos.get(i)[2] ; 
			ImageIcon img = new ImageIcon("./data/" +codigos.get(i)[0]+".png");
			Image image=img.getImage();
			Image newi=image.getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
			img=new ImageIcon(newi);
			
			dm.addElement(new ListaProducto(color,fuente,info,Integer.valueOf(codigos.get(i)[0]),img));
		}
		
		lista.setCellRenderer(new Renderer(principal));
		lista.setModel(dm);
		lista.setPreferredSize(new Dimension(250,150));
		
		JScrollPane scrollLote=new JScrollPane(lista,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scrollLote);
		
		panelopciones = new PanelOpcionesCompraCentro(principal,color,fuente);
		add(panelopciones);
	}
	
	public int getCantidad() {
		return panelopciones.getCantidad();
	}
	
	public void setPuntos(int puntos) {
		panelopciones.setPuntos(puntos);
	}
	public void setTotal(int total) {
		panelopciones.setTotal(total);
	}
	
	public void actualizarScroll()
	{
		
		dm.removeAllElements();
		System.out.println("TAMAÑO:"+dm.size());
		
		String info="";
		HashMap<Integer,String[]> productos=principal.getSupermercado().getListaproductos();
		System.out.println(productos);
		
		ArrayList<String[]> codigos=new ArrayList(productos.values());
		ArrayList<Integer> codigos1=new ArrayList(productos.keySet());
		
		for(int i=0;i<codigos1.size();i++) 
			{
			
			info= i+1 + ".) Codigo : " + codigos1.get(i)+ " Cantidad : "+ codigos.get(i)[0]+ " Coste : "+codigos.get(i)[1] ; 
			System.out.println(info);
			ImageIcon img = new ImageIcon("./data/" +codigos1.get(i)+".png");
			Image image=img.getImage();
			Image newi=image.getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
			img=new ImageIcon(newi);
			
			dm.addElement(new ListaProducto(color,font,info,Integer.valueOf(codigos1.get(i)),img));
			}
		System.out.println("atamaño numeo" + dm.size());
	}
}
