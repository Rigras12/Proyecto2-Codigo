package pos;

import Inventario.Inventario;
import Inventario.Producto;

public class OfertaDescuento implements MetodoCompra {
	private int descuento;
	
	public OfertaDescuento(int num) {
		descuento = num;
	}
	
	
	public int AplicarOferta(int numero, int codigo, int cantidad, Cliente cliente, Inventario inventario,
			supermercado mercado) {
		Producto producto = inventario.getProducto(codigo);
		double precio=(producto.getPrecioCantidad(cantidad)/100*(100-descuento));
		producto.retirarProductos(cantidad);
		producto.setbalance(producto.getBalance()+precio);
		if (cliente!=null) {
			cliente.agregarPuntos((int)precio/1000);
		}
		// TODO Auto-generated method stub
		return (int)precio;
	}

}
