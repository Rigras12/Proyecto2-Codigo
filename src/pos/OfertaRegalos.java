package pos;

import Inventario.Inventario;
import Inventario.Producto;

public class OfertaRegalos implements MetodoCompra {
	private int adicional;
	private int minimo; 
	
	public OfertaRegalos (int min, int add){
		adicional = add;
		minimo = min;
	}
	
	
	public int AplicarOferta(int numero, int codigo, int cantidad, Cliente cliente, Inventario inventario,
			supermercado mercado) {
		// TODO Auto-generated method stub
		int ganados = (int)(cantidad/minimo)*adicional;
		Producto producto = inventario.getProducto(codigo);
		double precio = producto.getPrecioCantidad(cantidad);
		producto.retirarProductos(ganados);
		producto.setbalance(producto.getBalance()+precio);
		if (cliente!=null) {
			cliente.agregarPuntos((int)precio/1000);
		}
		return (int)precio;
	}

	
	

}
