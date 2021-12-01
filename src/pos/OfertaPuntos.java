package pos;

import Inventario.Inventario;
import Inventario.Producto;

public class OfertaPuntos implements MetodoCompra{
	private int multiplicacion;
	
	public OfertaPuntos(int num) {
		multiplicacion = num;
	}
	
	
	public int AplicarOferta( int codigo, int cantidad, Cliente cliente, Inventario inventario,
			supermercado mercado) {
		// TODO Auto-generated method stub
		Producto producto = inventario.getProducto(codigo);
		double precio = producto.getPrecioCantidad(cantidad);
		producto.retirarProductos(cantidad);
		producto.setbalance(producto.getBalance()+precio);
		int puntos = (int)(precio/1000)*multiplicacion;
		if (cliente!=null) {
			cliente.agregarPuntos(puntos);
		}
		mercado.setPuntos((int)puntos+mercado.getPuntosActuales()-(puntos/multiplicacion));
		return (int)precio;
	}
	
	
	
	
}
