package Inventario;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import com.opencsv.*;


public class Inventario {

	private HashMap<Integer,Producto> articulos=new HashMap<Integer,Producto>();
	
	public HashMap<Integer,Producto> getArticulos()
	{
		return articulos;
	}
	
	
	public void aniadirLote(int codigo,String nombre,LocalDate fechaVencimiento,LocalDate fechaIngreso, double precioVenta,
			double precioCompra, double total,String almacenamiento,String unidad,double
			cantidadUnidad,String categoria, String subcategorias) throws IOException// añadir lote empaquetado
	{	
		loteEmpaquetado lote= new loteEmpaquetado(nombre,fechaVencimiento,fechaIngreso,precioVenta,precioCompra,total);
		Producto empaquetado= articulos.get(codigo);
		if (empaquetado==null)
		{
			aniadirProducto(codigo,almacenamiento,unidad,nombre,cantidadUnidad,categoria);
			empaquetado= articulos.get(codigo);
			System.out.println("Producto empaquetado creado...");
			
		}
		
		empaquetado.aniadirLote(lote);
		String [] subcategoriaslist = subcategorias.split(":");
		for (int x = 0;x<subcategoriaslist.length;x++)
		{
			String subcategoria = subcategoriaslist[x];
			if (empaquetado.getCategorias().contains(subcategoria))
			{
				
			}
			else
			{
				empaquetado.getCategorias().add(subcategoria);
			}
		}
		System.out.println("Lote empaquetado añadido");
		
		
		
	}
	public void aniadirLote(int codigo,String nombre,LocalDate fechaVencimiento,LocalDate fechaIngreso, double precioVenta,
			double precioCompra, double total,String almacenamiento,String unidad,String categoria, String subcategorias) throws IOException // añadir lote no empaquetado
	{
		loteNoEmpaquetado lote= new loteNoEmpaquetado(nombre,fechaVencimiento,fechaIngreso,precioVenta,precioCompra,total);
		Producto noempaquetado= articulos.get(codigo);
		if (noempaquetado==null)
		{
			aniadirProducto(codigo,almacenamiento,unidad,nombre,categoria);
			noempaquetado= articulos.get(codigo);
			System.out.println("Producto no empaquetado creado...");
		}
		
		noempaquetado.aniadirLote(lote);
		String [] subcategoriaslist = subcategorias.split(":");
		for (int x = 0;x<subcategoriaslist.length;x++)
		{
			String subcategoria = subcategoriaslist[x];
			if (noempaquetado.getCategorias().contains(subcategoria))
			{
				
			}
			else
			{
				noempaquetado.getCategorias().add(subcategoria);
			}
		}
		System.out.println("Lote no empaquetado añadido");
		
	}
	
	

	public void aniadirProducto(int codigo,String almacenamiento,String unidad,String nombre,double
			cantidadUnidad,String categoria
			) throws IOException //añadir producto empaquetado
	{
		Empaquetado empaquetado = new Empaquetado(codigo,almacenamiento,unidad,nombre,cantidadUnidad,categoria,"");
		articulos.put(codigo, empaquetado);
		
	}
	public void aniadirProducto(int codigo,String almacenamiento,String unidad,String nombre,String categoria
			) throws IOException //añadir producto no empaquetad
	{
		noEmpaquetado noempaquetado = new noEmpaquetado(codigo,almacenamiento,unidad,nombre,categoria,"");
		articulos.put(codigo, noempaquetado);
		
	}
	
	public void cargarProducto(int codigo,String nombre, String almacenamiento, String categoria,double total, String unidad, double preciopublico, double balance,String lotes,String subcategorias,String rutaimagen) throws IOException
	{
		noEmpaquetado noempaquetado = new noEmpaquetado(codigo,almacenamiento,unidad,nombre,categoria,rutaimagen);
		noempaquetado.setPrecio(preciopublico);
		noempaquetado.setbalance(balance);
		String [] lotexs = lotes.split(":");

		for(int x = 0; x < lotexs.length; x += 1)
		{
			String [] lotex = lotexs[x].split("[|]") ;
			loteNoEmpaquetado lote = new loteNoEmpaquetado(lotex[0],LocalDate.parse(lotex[1]),LocalDate.parse(lotex[2]),Double.parseDouble(lotex[3]),Double.parseDouble(lotex[4]),Double.parseDouble(lotex[5])); //nombre,fechaVencimiento,  fechaIngreso, precioVenta, precioCompra,total
			noempaquetado.aniadirLote(lote);
		}
		String [] subcategoriasx = subcategorias.split(":");
		for(int x = 0; x < subcategoriasx.length; x += 1)
		{
			noempaquetado.añadirsubcategoria(subcategoriasx[x]);
		}
		articulos.put(codigo, noempaquetado);
	}
	
	public void cargarProducto(int codigo,String nombre, String almacenamiento, String categoria,double total, String unidad, double preciopublico, double balance,double pesounidad,String lotes,String subcategorias, String rutaimagen) throws IOException
	{
		Empaquetado empaquetado = new Empaquetado(codigo,almacenamiento,unidad,nombre,pesounidad,categoria,rutaimagen);
		empaquetado.setPrecio(preciopublico);
		empaquetado.setbalance(balance);
		String [] lotexs = lotes.split(":");
		
		for(int x = 0; x < lotexs.length; x += 1)
		{
			String [] lotex = lotexs[x].split("[|]") ;
			
			loteEmpaquetado lote = new loteEmpaquetado(lotex[0],LocalDate.parse(lotex[1]),LocalDate.parse(lotex[2]),Double.parseDouble(lotex[3]),Double.parseDouble(lotex[4]),Double.parseDouble(lotex[5])); //nombre,fechaVencimiento,  fechaIngreso, precioVenta, precioCompra,total
			empaquetado.aniadirLote(lote);
			//(String nombre, LocalDate fechaVencimiento, LocalDate fechaIngreso, double precioVenta, double precioCompra, double total
		}
		
		String [] subcategoriasx = subcategorias.split(":");
		for(int x = 0; x < subcategoriasx.length; x += 1)
		{
			empaquetado.añadirsubcategoria(subcategoriasx[x]);
		}
		articulos.put(codigo, empaquetado);
		
	}

	public void eliminarLotes(int codigo,LocalDate fecha)
	{
		Producto producto = getProducto(codigo);
		producto.eliminarLotes(fecha);
	
	}
	public void eliminarLote(int codigo,int index)
	{
		Producto producto = getProducto(codigo);
		producto.eliminarLote(index);
		
		
	}
	public String  [] verLote(int codigo, int strlote)
	{
		Producto producto = getProducto(codigo);
		Lote lote  = producto.getLote(strlote);
		String cantidad;
		if (producto.getCantidadUnidad()==-1) {
			cantidad = Double.toString(lote.getTotal()) +" "+ producto.getUnidad();
		}
		else {
			cantidad = Double.toString(lote.getTotal()) + "Unidades";
		}
		// se retorna el nombre, fecha de vencimiento, cantidad, , precio de compra, fecha de ingreso al inventario 
		String [] salida = {lote.getNombre(),lote.getVencimiento().toString(),cantidad, lote.getIngreso().toString(), Double.toString(lote.getPrecioCompra())};
		return salida;
	}
	public void setCategoria(Producto producto, String categoria)
	{
	
		producto.setCategoria(categoria);
		
		
	}
	public String [] getCaracteristicasProducto(int codigo) {
		// retorna una lista con el nombre, cantidad, balance, cantidad de lotes, precio y tipo de unidad
		Producto producto = getProducto(codigo);
		String caracteristicas = "";
		caracteristicas = caracteristicas + producto.getNombre()+ "," + producto.getCantidad() +"," + producto.getBalance()+ ","+ producto.cantlotes()+ "," + producto.getPrecio() +","+ producto.getUnidad();
		return caracteristicas.split(",");
	}
	public String consultarCantidad(int codigo) {
		Producto producto = getProducto(codigo);
		return producto.getCantidad();
	}
	
	public void verLotes(Producto producto)
	{
		
		
		int cantidad = producto.getLotes().size();
		int i=0;
		while(i<cantidad)
		{
			Lote lote = producto.getLotes().get(i);
			System.out.println(i  + " " + lote.consultarInformacion());
			i+=1;
		}
		
	}
	public int cantidadLotes()
	{	
		ArrayList<Producto> productos=new ArrayList<>(articulos.values());
		int contador=0;
		for (int i=0;i<productos.size();i++)
		{
			contador+=productos.get(i).cantlotes();
		}
		return contador;
	}
	public int totalProductos()
	{
		return articulos.size();
	}
	public String consultarBalance(int codigo)
	{
		Producto producto = getProducto(codigo);
		if (producto!=null) {
			return String.valueOf(producto.getBalance());
		}
		else {
			
		}
		return "Codigo no encontrado";
	}
	
	public ArrayList<String> consultarCantidadyLotes(int codigo) 
	{
		Producto producto = getProducto(codigo);
		ArrayList<String> lotes = new ArrayList<String>();
		if (producto!=null) {
		System.out.println(producto.getCantidad());
		
		for(int x=0;x<producto.cantlotes();x+=1)
		{
			lotes.add("Lote numero : " + (x+1)  +" ||Existencias : " + producto.getLote(x).getTotal());
		}
		return lotes;
		}
		else {
			return lotes;
		}
	} 
	
	
	
	
	 public Producto getProducto(int codigo)
	 {
		 Producto producto=articulos.get(codigo);
		 return producto;
	 }
	 
	 public String getNombre(int codigo)
	 {
		 return getProducto(codigo).getNombre();
	 }
	 
	 public String [] actualizarProducto(Producto p)
	 {
		 String lotes = actualizarlotes(p);
		 String subcategorias = p.obtenerStringCategorias();
 		 String [] productocargar = {String.valueOf(p.getCodigo()),p.getNombre(),p.getAlmacenamiento(),p.getCategoria(),String.valueOf(p.getTotal()),p.getUnidad(),String.valueOf(p.getPrecio()),String.valueOf(p.getBalance()),String.valueOf(p.getCantidadUnidad()),lotes,subcategorias,p.getrutaImagen()}; 
		 return productocargar;
	 }
	 
	 public int cantidadLotesProducto(int codigo) {
		 Producto producto=articulos.get(codigo);
		 return producto.cantlotes();
	 }
	 
	 
	 
	 public String actualizarlotes(Producto p)
	 {
		 String salida = "";
		 ArrayList<Lote> listlotes = p.getLotes();
		 for(int x=0;x<=p.cantlotes()-1;x++)
		 {
			 Lote lotesito = listlotes.get(x);
			 String lotees = "";
			 lotees = "" + lotesito.getNombre() + "|" + lotesito.getVencimiento().toString() + "|" 
			 + lotesito.getIngreso().toString() + "|" + String.valueOf(lotesito.getPrecioVenta()) 
			 + "|" + String.valueOf(lotesito.getPrecioCompra()) + "|" + String.valueOf(lotesito.getTotal());
			 if (x==0)
			 {
				 salida = salida + lotees;
			 }
			 else
			 {
				 salida = salida + ":" + lotees;
			 }
		 }
		 return salida;
	 }
	 public int verificarProducto(Producto producto, int cantidad)
	 {
		 if (producto.getTotal()<cantidad)
		 {
			 cantidad=producto.getTotal();
		 }
		 producto.retirarProductos(cantidad);
		 
		 return cantidad;
	 }
	 public String descripcion(int codigo)
	 {
		 Producto producto=articulos.get(codigo);
		 if(producto!=null)
			{
				return producto.getDescripcion();
			}
			else
			{
				return ("Codigo no encontrado");
			}
	 }
	 
	 
	 public void CargarImagen(int codigo, String nombre) {
		 Producto producto = getProducto(codigo);
		 producto.setrutaImagen(nombre);
	 }

	 
}
