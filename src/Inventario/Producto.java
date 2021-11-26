package Inventario;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public abstract class Producto {

	private int codigo; //codigo de barras
	private String almacenamiento; // gondola, refrigeración o congelación
	private String unidad; // gr/ ml
	private String nombre;
	private int total;
	private double precio;
	private double desempenio;
	private String categoria;
	private ArrayList<Lote> lotes=new ArrayList<Lote>();
	private ArrayList<String> Subcategorias= new ArrayList<String>();
	private String rutaimage;
	private ListaRegistros historial;
	
	public Producto(int codigo, String almacenamiento,String unidad,String nombre,String categoria, String rutaimagen) throws IOException
	{
		this.codigo=codigo;
		this.almacenamiento=almacenamiento;
		this.unidad=unidad;
		this.nombre=nombre;
		this.categoria=categoria;
		this.desempenio=0;
		total=0;
		precio=0;
		this.rutaimage = rutaimagen;
		this.historial = new ListaRegistros();
		
		
	}
	
	public int getCodigo()
	{
		return codigo;
	}
	public int getTotal()
	{
		return total;
	}
	public double getPrecio()
	{
		return precio;
	}
	
	public String getAlmacenamiento()
	{
		return almacenamiento;
	}

	public String getrutaImagen()
	{
		return rutaimage;
	}
	
	public String getUnidad()
	{
		return unidad;
	}
	public double getDesempenio()
	{
		return desempenio;
	}
	public String getCategoria()
	{
		return categoria;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setPrecio(double precio)
	{
		this.precio=precio;
	}
	public void aumentarStock(double cantidad)
	{
		this.total+=cantidad;
	}
	public void disminuirStock(double cantidad)
	{
		this.total-=cantidad;
	}
	
	public String getFactura()
	{
		return "Codigo : " + codigo + " Almacenamiento : " + almacenamiento + " Nombre : " + getNombre()  ;
	}
	public abstract String getDescripcion();
	
	public abstract String getCantidad();
	
	public abstract double getCantidadUnidad();
	
	public void SetTotal(int nuevo)
	{
		this.total = nuevo;
	}
	
	public double ImprimirBalance()
	{	
		
		return desempenio;
	}
	
	public double getBalance()
	{
		return this.desempenio;
	}
	public void setCategoria(String categoria)
	{
		this.categoria=categoria;
	}
	
	public ListaRegistros getHistorial() {
		return this.historial;
	}
	
	
	public void aniadirLote(Lote lote)
	{	
		double cantidadNueva;
		double precio;
		lotes.add(lote);
		cantidadNueva=lote.getTotal();
		aumentarStock(cantidadNueva);
		precio=lote.getPrecioVenta();
		setPrecio(precio);
		
		
		//aumentar cantidad productos, actualizar precios
	}
	public void retirarProductos(double cantidad) // la función que realiza la compra de los productos
	{
		double cantidadDisponible=lotes.get(0).getTotal();
		if (cantidad>cantidadDisponible)
		{
			double nuevaCantidad=cantidad-cantidadDisponible;
			lotes.remove(0);
			disminuirStock(cantidadDisponible);
			retirarProductos(nuevaCantidad);
			aumentarBalance(cantidadDisponible);
			
		}
		else
		{
			lotes.get(0).quitarProductos(cantidad);
			disminuirStock(cantidad);
			aumentarBalance(cantidad);
			
		}
		
	}
	public void aumentarBalance(double cantidad)//aumenta el balance al comprar una cantidad
	{	
		
		double precioCompra=lotes.get(0).getPrecioCompra();
		double diferencia=getPrecio()-precioCompra;
		desempenio+=(cantidad*diferencia);
	}
	public void disminuirBalance(double cantidad,int index)
	{	
		double precioCompra=lotes.get(index).getPrecioCompra();
		
		System.out.println(lotes.get(index).getPrecioCompra());
		desempenio-= (cantidad*precioCompra);
	}
	public void eliminarLotes(LocalDate actual)//elimina lotes vencidos
	{
		int i=0;
		Lote lote;
		System.out.println(lotes.size());
		while(i<lotes.size())
		{	
			lote= lotes.get(i);
			if (!lote.vigente(actual))
			{	
				double cantidad=lote.getTotal();
				disminuirStock(cantidad);
				disminuirBalance(cantidad,i);
				lotes.remove(i);
				i-=1;
				System.out.println("Eliminado correctamente");
			}
			i+=1;	
		}
	}
	public void eliminarLote(int index)
	{
		Lote lote=lotes.get(index);
		
		double cantidad=lote.getTotal();
		disminuirStock(cantidad);
		disminuirBalance(cantidad,index);
		lotes.remove(index);
		System.out.println("Eliminado correctamente");
		
	}
	public Lote getLote(int lote)
	{
		return lotes.get(lote);
	}
	
	
	public int cantlotes()
	{
		return lotes.size();
	}

	public void setbalance(double balance)
	{
		this.desempenio = balance;
	}
	public double getPrecioCantidad(double cantidad)
	{
		return getPrecio()*cantidad;
	}
	public void añadirsubcategoria(String nuevaCategoria)
	{
		this.Subcategorias.add(nuevaCategoria);
	}
	public ArrayList<String> getCategorias()
	{
		return this.Subcategorias;
	}
	public ArrayList<Lote> getLotes()
	{
		return lotes;
	}
	public String obtenerStringCategorias()
	{
		String subcategories = "";
		for (int x=0;x<this.Subcategorias.size();x++)
		{
			if (x==0)
			{
				subcategories = this.Subcategorias.get(x);
			}
			else
			{
				subcategories = subcategories + ":" + this.Subcategorias.get(x);
			}
			
		}
		
		return subcategories;
	}
	public void setrutaImagen(String ruta) {
		this.rutaimage = ruta;
		
		
		
	}
}
