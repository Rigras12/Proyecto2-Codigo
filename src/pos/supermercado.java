package pos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
	private CompraNormal compraNormal = new CompraNormal();
	private HashMap<Integer,ArrayList<Oferta>> ofertas = CargarOfertas();
	
	public supermercado( LocalDate fechaActual,Inventario inventario)
	{	this.fechaActual=fechaActual;
		
		this.inventario=inventario;
		
	}
	
	
	private HashMap<Integer, ArrayList<Oferta>> CargarOfertas()  {
		HashMap<Integer, ArrayList<Oferta>> MapaOfertas = new HashMap<Integer, ArrayList<Oferta>>();
		BufferedReader br = null;
		try {
	         
	         br =new BufferedReader(new FileReader("./data/Ofertas.txt"));
	         String line = br.readLine();
	         while (null!=line) {
	            String [] fields = line.split(",");
	            String tipo = fields[1];
	            if (tipo.equals("Descuento")) {
	            	OfertaDescuento metodocompra = new OfertaDescuento(Integer.parseInt(fields[2]));
	            	Oferta nuevaOferta = new Oferta(LocalDate.parse(fields[3]),LocalDate.parse(fields[4]), metodocompra);
	            	int codigo = Integer.parseInt(fields[0]);
	            	if (MapaOfertas.containsKey(codigo)) {
	            		ArrayList<Oferta> lista = MapaOfertas.get(codigo);
	            		lista.add(nuevaOferta);
	            	}
	            	else {
	            		ArrayList<Oferta> lista = new ArrayList<Oferta>();
	            		lista.add(nuevaOferta);
	            		MapaOfertas.put(codigo, lista);
	            	}
	            }
	            else if (tipo.equals("Adicional")) {
	            	OfertaRegalos metodocompra = new OfertaRegalos(Integer.parseInt(fields[2]),Integer.parseInt(fields[5]));
	            	Oferta nuevaOferta = new Oferta(LocalDate.parse(fields[3]),LocalDate.parse(fields[4]), metodocompra);
	            	int codigo = Integer.parseInt(fields[0]);
	            	if (MapaOfertas.containsKey(codigo)) {
	            		ArrayList<Oferta> lista = MapaOfertas.get(codigo);
	            		lista.add(nuevaOferta);
	            	}
	            	else {
	            		ArrayList<Oferta> lista = new ArrayList<Oferta>();
	            		lista.add(nuevaOferta);
	            		MapaOfertas.put(codigo, lista);
	            	}
	            }
	            else if (tipo.equals("Punticos")) {
	            	OfertaPuntos metodocompra = new OfertaPuntos(Integer.parseInt(fields[2]));
	            	Oferta nuevaOferta = new Oferta(LocalDate.parse(fields[3]),LocalDate.parse(fields[4]), metodocompra);
	            	int codigo = Integer.parseInt(fields[0]);
	            	if (MapaOfertas.containsKey(codigo)) {
	            		ArrayList<Oferta> lista = MapaOfertas.get(codigo);
	            		lista.add(nuevaOferta);
	            	}
	            	else {
	            		ArrayList<Oferta> lista = new ArrayList<Oferta>();
	            		lista.add(nuevaOferta);
	            		MapaOfertas.put(codigo, lista);
	            	}
	            }
	            
	            
	            
	            line = br.readLine();
	         }
	         
	      } catch (Exception e) {
	    	  System.err.println("Error! "+e.getMessage());
	       
	      }
		return MapaOfertas;
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
	
	
	public void aniadirProducto(int codigo,double cantidad) {
		
		if (productos.containsKey(codigo)) {
			cantidad = productos.get(codigo)+(int)cantidad;
			productos.put(codigo, (int)cantidad);
			
		}
		else {
			productos.put(codigo,(int)cantidad);
			
		}
		
		double precio = inventario.getProducto(codigo).getPrecioCantidad(cantidad);
		total += precio;
		Puntos += (int)precio/1000;
		
		
		
		
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
	
	
	public void finalizarCompra(boolean PagaconPuntos)
	{
		
		Set<Integer> keys = productos.keySet();
		for(Integer key:keys) {
			boolean encontro = false;
			int cantidad = productos.get(key);
			
			if (ofertas.containsKey(key)) {
				
				ArrayList<Oferta> listaofertas = ofertas.get(key);
				
				for (int i=0;i<listaofertas.size() && !encontro;i++) {
					
					Oferta ofertaActual = listaofertas.get(i);
					if (ofertaActual.esValida(fechaActual)) {
						ofertaActual.getOferta().AplicarOferta( key, cantidad, clienteActual, inventario, this);
						encontro = true;
					}
				}
				
			}
			else if (!encontro){
				compraNormal.AplicarOferta(key, cantidad, clienteActual, inventario, this);
				
			}
				
			
			
			
			
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

	public void setTotal(int toal) {
		this.total = toal;
	}
	
	public void setPuntos(int pun) {
		this.Puntos = pun;
	}

	public void actualizarCantidadProducto(int actual, int codigo) {
		// TODO Auto-generated method stub
		productos.put(codigo, actual);
	}
}
