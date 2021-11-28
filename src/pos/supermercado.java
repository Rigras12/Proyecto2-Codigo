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
	private HashMap<Integer,Integer> productos= new HashMap<Integer,Integer>();
	private Cliente clienteActual;
	private int Puntos= 0;
	private int total= 0;
	private HashMap<Integer,Oferta> ofertas = CargarOfertas();
	
	public supermercado( LocalDate fechaActual,Inventario inventario)
	{	this.fechaActual=fechaActual;
		
		this.inventario=inventario;
		
	}
	private HashMap<Integer, Oferta> CargarOfertas() {
		// TODO Auto-generated method stub
		return null;
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
		productos = new HashMap<Integer,Integer>();
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
	
	public HashMap<Integer,Integer> getListaproductos() {
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
