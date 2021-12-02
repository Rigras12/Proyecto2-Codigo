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
	private ArrayList<Combos> combos = new ArrayList<Combos>();
	private ArrayList<Combos> combosAComprar = new ArrayList<Combos>();
	
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
	
	
	
	
	public void cargarCombos() {
		
		BufferedReader br = null;
		try {
	         
	         br =new BufferedReader(new FileReader("./data/Combos.txt"));
	         String line = br.readLine();
	         while (null!=line) {
	            String [] fields = line.split(",");
	            
	            if (fields[0].charAt(0) == '\"')
	            {
	            	fields[0] = fields[0].substring(1);
	            	
	            }
	            LocalDate fechaini = LocalDate.parse(fields[0]);
	            LocalDate fechafin = LocalDate.parse(fields[1]);
	            if (fechaActual.compareTo(fechaini)>=0 && fechaActual.compareTo(fechafin)<=0) {
	            	ArrayList<Integer> codigos = new ArrayList<Integer>();
		            int cant = Integer.parseInt(fields[2]);
		            for (int i=0;i<cant;i++) {
		            	codigos.add(Integer.parseInt(fields[4+i]));
		            }
		            combos.add(new Combos(codigos,Integer.parseInt(fields[3])));
		            
	            }
	            
	            
	           
	           
	            
	            
	            line = br.readLine();
	         }
	         
	      } catch (Exception e) {
	    	  System.err.println("Error! "+e.getMessage());
	      } 
		
		
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
	
		
		
	
	
	public void AniadirCombo(int indice) {
		
		Combos combo = combos.get(indice);
		ArrayList<Integer> productoscombo = combo.getCombo();
		
		for (int i=0;i<productoscombo.size();i++) {
			Producto producto = inventario.getProducto(productoscombo.get(i));
			 int precio = (int)((producto.getPrecio()/100)*(100-combo.getDescuento()));
			 total += precio;
			 Puntos += (int)precio/1000;
		}
		combosAComprar.add(combo);
		
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
		
		for (int j=0;j<combosAComprar.size();j++) {
			Combos comboactual = combosAComprar.get(j);
			ArrayList<Integer> productosCombo = comboactual.getCombo();
			for  (int p=0;p<productosCombo.size();p++) {
				comboactual.getOferta().AplicarOferta(productosCombo.get(p), 1, clienteActual, inventario, this);
			}
		}
		
		if (clienteActual != null && !PagaconPuntos) {
			clienteActual.agregarPuntos(Puntos);
		}
		if (PagaconPuntos && clienteActual != null) {
			
				int puntosgastados = clienteActual.getPuntos()*15;
				if (puntosgastados>total) {
					clienteActual.quitarPuntos(total);
				} else {
					total -= puntosgastados;
					clienteActual.quitarPuntos(puntosgastados);
				}
			
		}
		
		
		combosAComprar = new ArrayList<Combos>();
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
