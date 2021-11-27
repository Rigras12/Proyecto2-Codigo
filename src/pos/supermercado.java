package pos;

import java.time.LocalDate;
import java.util.HashMap;

import Inventario.Inventario;
import Inventario.Producto;

public class supermercado {
	private LocalDate fechaActual;
	
	private HashMap<Integer,Cliente> clientes= new HashMap<Integer,Cliente>();
	private Inventario inventario;
	private String factura="";
	private HashMap<Integer,String[]> productos= new HashMap<Integer,String[]>();
	private Cliente clienteActual;
	private int Puntos= 0;
	private int total= 0;
	private HashMap<Integer,Oferta> ofertas = new HashMap<Integer,Oferta>();
	
	public supermercado( LocalDate fechaActual,Inventario inventario)
	{	this.fechaActual=fechaActual;
		
		this.inventario=inventario;
		
	}
	public Inventario getInventario()
	{
		return inventario;
	}
	public Cliente getClienteActual()
	{
		return clienteActual;
	}
	public Cliente getCliente(int numeroCedula)
	{
		return clientes.get(numeroCedula);
	}
	public void cambiarCliente(int cedula)
	{
		this.clienteActual=getCliente(cedula);
	}
	public Cliente agregarCliente(int numeroCedula,int edad, char sexo,String estadoCivil, String situacionLaboral)
	{
		clientes.put(numeroCedula,new Cliente(numeroCedula,edad,sexo,estadoCivil,situacionLaboral));
		return clientes.get(numeroCedula);
	}
	
	public boolean estaRegistrado(int numeroCedula)
	{
		return clientes.get(numeroCedula)!=null;
	}
	
	public HashMap<Integer,String[]> getProductos()
	{
		return productos;
	}
	
	public boolean RegistrarCompra(int codigo, int cantidad)
	{
		Producto producto = inventario.getProducto(codigo);
		
		
		int vendidos=inventario.verificarProducto(producto,cantidad);
		if (vendidos==0)
		{
			return false;
		}
		else
		{	
			double precio=producto.getPrecio()* vendidos;
			
				
				aniadirPuntosxProducto(precio);
				if (productos.containsValue(codigo)) {
					String [] atributos = productos.get(producto.getCodigo());
					String [] atributosnuevos = {String.valueOf((Integer.parseInt(atributos[0])+vendidos)),String.valueOf((Integer.parseInt(atributos[1])+precio))};
					productos.put(codigo, atributosnuevos);
					total+=precio;
			}
				else
				{	
					String[] atributosnuevos= {String.valueOf(vendidos),String.valueOf(precio)};
					productos.put(codigo, atributosnuevos);
					total+=precio;
				}
			
			aniadirProductoFactura(producto,  vendidos);
			return true;
		}
		
		
	//aniadir puntos
	}
	
	
	public boolean RetirarCompra(int codigo, int cantidad)
	{
		Producto producto = inventario.getProducto(codigo);
		if (productos.get(codigo)!=null) {
			double precio=producto.getPrecio()* cantidad;
			String [] atributos = productos.get(producto.getCodigo());
			String [] atributosnuevos = {String.valueOf((Integer.parseInt(atributos[0])-cantidad)),String.valueOf((Double.parseDouble(atributos[1])-precio))};
			quitarPuntosxProducto(precio);
			productos.put(codigo,atributosnuevos);
			total-=precio;
			
			return true;
			
		}
		else {
			return false;
		}
	}
	
	
	public void aniadirPuntosxProducto(double compra) {
		this.Puntos += (int)(compra/1000);
	}
	public void quitarPuntosxProducto(double precio) {
		this.Puntos-= (int)(precio/1000);
		
		
	}
	
	
	public void aniadirPuntos(int puntos, Cliente cliente)
	{	
		
		cliente.agregarPuntos(puntos);
	}
	public void aniadirProductoFactura(Producto producto, int cantidad)
	{
		
		String factura =   String.valueOf(cantidad) + "," + producto.getPrecioCantidad(cantidad) + "," + (int)(producto.getPrecioCantidad(cantidad)/1000);
		
	}
	
	public void finalizarCompra()
	{
		if (clienteActual!=null) {
			clienteActual.agregarPuntos(Puntos);
			clienteActual.aniadirgasto(total);; 
		}
		productos = new HashMap<Integer,String[]>();
		clienteActual=null;
		Puntos= 0;
		total=0;
	}
	public String getFactura() 
	{
		String papel=factura;
		factura="";
		return papel;
	}
	
	public HashMap<Integer,String[]> getListaproductos() {
		return this.productos;
	}
	
	public int VerPuntosdeClientes(int cedula) {
		return clientes.get(cedula).getPuntos();
	}
	
	public int getPuntosActuales() {
		return this.Puntos;
	}
	public int getTotal() {
		return this.total;
	}
	public int getHistoricoComprasCliente() {
		return clienteActual.getHistoricoCompras();
		
	}
}
