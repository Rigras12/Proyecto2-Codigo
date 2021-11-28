package pos;

import Inventario.Inventario;

public interface MetodoCompra {
	
	
	
	
	public boolean AplicarOferta(int numero, int codigo, int cantidad, Cliente cliente,Inventario inventario, supermercado mercado);
	
}
// Para cada compra hacer un mapa con los producos a comprar que sea <Producto,Cantidad> y al final de la compra 
// buscamos y aplicamos el metodo respectivo para cada producto.