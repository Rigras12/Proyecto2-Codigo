package pos;

import Inventario.Inventario;
import Inventario.Producto;

public class CompraNormal implements MetodoCompra {

	
	public int AplicarOferta(int numero, int codigo, int cantidad, Cliente cliente, Inventario inventario,
			supermercado mercado) {
		Producto producto = inventario.getProducto(codigo);
		double precio = producto.getPrecioCantidad(cantidad);
		producto.setbalance(producto.getBalance()+precio);
		if (cliente!=null) {
			cliente.agregarPuntos((int)precio/1000);
		}
		return (int)precio;
	}

}
