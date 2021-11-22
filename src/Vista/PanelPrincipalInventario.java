package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.opencsv.CSVWriter;

import Inventario.Inventario;

public class PanelPrincipalInventario extends JFrame implements WindowListener{
	private PanelSuperior panelSuperior;
	private PanelOpciones panelOpciones;
	private PanelDescripcion panelDescripcion;
	private PanelProductos panelProductos;
	private Inventario inventario;
	private int productoActual;
	private int loteActual;
	private Font font;
	private Color color;
	private LocalDate fechahoy;
	public PanelPrincipalInventario(String fechap) throws IOException {
		// TODO Auto-generated constructor stub
		
		addWindowListener((WindowListener) this);
		setSize(800,600);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		font=new Font("Serif", Font.BOLD, 23);
		color=Color.black;
		this.productoActual = 12345;
		this.fechahoy = LocalDate.parse(fechap);
		this.loteActual = 1;
		inventario = cargarInventario("./data/Inventario.csv");
		Collection productos=inventario.getArticulos().values();
		
		panelSuperior= new PanelSuperior(fechap,obtenerTotalProductos(),obtenerTotalLotes(),this);
		panelOpciones=new PanelOpciones(this);
		panelDescripcion=new PanelDescripcion(this);
		panelProductos=new PanelProductos(this,color,font,new ArrayList<>(productos),inventario.cantidadLotesProducto(productoActual));
		
		
		add(panelSuperior, BorderLayout.NORTH);
		add(panelOpciones,BorderLayout.WEST);
		add(panelDescripcion,BorderLayout.CENTER);
		add(panelProductos,BorderLayout.EAST);
		
		refrescarInformacionProducto();
		
	}
	   
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		PanelPrincipalInventario panelprincipal = new PanelPrincipalInventario("2021-11-14");
		panelprincipal.setVisible(true);
		panelprincipal.setLocationRelativeTo(null);
		 
	}
	
	public void refrescarInformacionProducto() 
	{
		panelDescripcion.actualizarDescripcion(productoActual,inventario.getNombre(productoActual),inventario.consultarCantidad(productoActual), inventario.consultarBalance(productoActual), inventario.cantidadLotesProducto(productoActual));
		panelDescripcion.setLote("Lote numero: " + loteActual);
		panelProductos.actualizarLotes(inventario.cantidadLotesProducto(productoActual));
		
		
	}
	
	public int obtenerTotalLotes() 
	{
		return inventario.cantidadLotes();
		
	}
	
	public int obtenerTotalProductos()
	{
		return inventario.totalProductos();
	}
	
	public  String [] getCaracteristicas(int codigo) {
		return inventario.getCaracteristicasProducto(codigo);
	}
	
	
	public void actualizarinventario(String ruta, Inventario inventario) throws IOException
	 {
		 System.out.println("Actualizando inventario...");
		 ArrayList<String[]> productos = new ArrayList<String[]>();
		 inventario.getArticulos().forEach((key,producto)->
		 {
			 
			 productos.add(inventario.actualizarProducto(producto));
			 
		 });
		 
		 CSVWriter writer = new CSVWriter(new FileWriter(ruta),CSVWriter.DEFAULT_SEPARATOR , CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
		 
		 writer.writeAll(productos);
		 writer.close();
		 
		 System.out.println("\nInventario actualizado");
		 System.out.println("\nSaliendo de la aplicacion...");
	 }
	
	
	
	private Inventario cargarInventario(String ruta) throws IOException 
	{
		Inventario inventario = new Inventario();
		BufferedReader br = null;
		try {
	         
	         br =new BufferedReader(new FileReader(ruta));
	         String line = br.readLine();
	         while (null!=line) {
	            String [] fields = line.split(",");
	            
	            if (fields[0].charAt(0) == '\"')
	            {
	            	fields[0] = fields[0].substring(1);
	            	fields[9] = fields[9].substring(0, fields[9].length()-1);
	            }
	            
	            
	            if ((fields[8].equals("-1"))||(fields[8].equals("-1.0")))
	            {
	           
	            	inventario.cargarProducto(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3], Double.parseDouble(fields[4]),fields[5], Double.parseDouble(fields[6]), Double.parseDouble(fields[7]),fields[9],fields[10],fields[11]);
	            }
	            else
	            {
	            	
	            	inventario.cargarProducto(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3], Double.parseDouble(fields[4]),fields[5], Double.parseDouble(fields[6]), Double.parseDouble(fields[7]), Double.parseDouble(fields[8]),fields[9],fields[10],fields[11]);
	            	
	            }
	            
	            
	            line = br.readLine();
	         }
	         
	      } catch (Exception e) {
	    	  System.err.println("Error! "+e.getMessage());
	      } finally {
	         if (null!=br) {
	            br.close();
	         }
	      }
		return inventario;
	}
	
	

	public void verDescripcion() {
		
		String message=inventario.descripcion(productoActual);
		JOptionPane.showMessageDialog(this, message);
		
	}

	public void verLote() {
		// se retorna el nombre, fecha de vencimiento, cantidad, fecha de vencimiento, precio de compra, fecha de ingreso al inventario 
		String [] infolote  = inventario.verLote(productoActual, loteActual);
		String message= "" + infolote[0]+ " || Vence el " + infolote[1] + "\n" + infolote[2] +" disponibles || Ingreso en " + infolote[3] +"\n Costo lote: " + infolote[4] + " $";
		JOptionPane.showMessageDialog(this, message);
	}

	public void SeleccionarLote() {
		ArrayList<String> lotes = inventario.consultarCantidadyLotes(productoActual);
		PanelLotes panelsito = new PanelLotes(lotes,color,font);
		panelsito.setVisible(true);
	}

	public void cambiarFecha() {
		String nuevafecha = JOptionPane.showInputDialog(null,"Ingrese la fecha de hoy (aaaa-mm-dd)");
		this.fechahoy = LocalDate.parse(nuevafecha);
		panelSuperior.setFecha(nuevafecha);
	
	}

	public void borrarLote() {
		int anterior=inventario.cantidadLotes();
		int total=inventario.cantidadLotesProducto(productoActual);
		
		inventario.eliminarLote(productoActual, loteActual);
		panelSuperior.setLotes(anterior-(total-inventario.cantidadLotesProducto(productoActual)));
		refrescarInformacionProducto();
		
		JOptionPane.showMessageDialog(this, "Lote eliminado");
	}

	public void borrarLotes() {
		int anterior=inventario.cantidadLotes();
		int total=inventario.cantidadLotesProducto(productoActual);
		System.out.println("total : ");
		System.out.println("XD " +anterior);
		inventario.eliminarLotes(productoActual, fechahoy);
		panelSuperior.setLotes(anterior-(total - inventario.cantidadLotesProducto(productoActual)));
		System.out.println(inventario.cantidadLotesProducto(productoActual)-total);
		refrescarInformacionProducto();
		
		
		
		JOptionPane.showMessageDialog(this, "Lotes vencidos eliminados correctamente");
	}
	
	
		
	

	public void cargarLotes() throws IOException {
		// TODO Auto-generated method stub
		String ruta = JOptionPane.showInputDialog(null,"Ingrese la ruta del archivo con los lotes");
		BufferedReader br = null;
		try {
	         
	         br =new BufferedReader(new FileReader(ruta));
	         String line = br.readLine();
	         while (null!=line) {
	            String [] fields = line.split(",");
	            
	            
	            
	            if (fields[8].equals("-1"))
	            {
	            	
	            	inventario.aniadirLote(Integer.parseInt(fields[0]),fields[1],LocalDate.parse(fields[2]),fechahoy,Double.parseDouble(fields[3]),Double.parseDouble(fields[4]),Double.parseDouble(fields[5]),fields[6],fields[7],fields[9],fields[10]);
	            }
	            else
	            {
	            	
	            	inventario.aniadirLote(Integer.parseInt(fields[0]),fields[1],LocalDate.parse(fields[2]),fechahoy,Double.parseDouble(fields[3]),Double.parseDouble(fields[4]),Double.parseDouble(fields[5]),fields[6],fields[7],Double.parseDouble(fields[8]),fields[9],fields[10]);
	            }
	            //inventario.aniadirLote(fields[0],LocalDate.parse(fields[1]),fecha,Double.parseDouble(fields[2]),Double.parseDouble(fields[3]),Double.parseDouble(fields[4]),Integer.parseInt(fields[5]),fields[6],fields[7],fields[8]);
	           //(               int codigo,String nombre,LocalDate fechaVencimiento,LocalDate fechaIngreso, double precioVenta,double precioCompra, double total,String almacenamiento,String unidad,double cantidadUnidad,String categoria)
	            line = br.readLine();
	         }
	         
	      } catch (Exception e) {
	    	  System.err.println("Error! "+e.getMessage());
	      } finally {
	         if (null!=br) {
	            br.close();
	         }
	      }
	}
	

	public void cargarImagen() {
		String direccion = JOptionPane.showInputDialog(null,"Ingrese el nombre de la imagen: (debe estar en //data)");
		inventario.CargarImagen(productoActual, direccion);
		JOptionPane.showMessageDialog(this, "Imagen añadida correctamente...");
		
	}

	public void escogerProducto(int codigo) {
		// TODO Auto-generated method stub
		productoActual=codigo;
		refrescarInformacionProducto();
		
	}
	public void escogerLote(int lote)
	{
		loteActual=lote;
		refrescarInformacionProducto();
	}
	
	public int getProductoActual()
	{
		return productoActual;
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void windowClosed (WindowEvent arg0) {    

	} 
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		   try {
				actualizarinventario("./data/Inventario.csv",inventario) ;
				System.out.println("Subanos la nota por el esfuerzo ;)");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				
				System.out.println(e1.getMessage()+ "error al cerrar");
			}
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
