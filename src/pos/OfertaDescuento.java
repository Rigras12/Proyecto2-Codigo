package pos;

import Inventario.Inventario;

public class OfertaDescuento implements MetodoCompra {
	private int descuento;
	
	public OfertaDescuento(int num) {
		descuento = num;
	}
	
	
	public boolean AplicarOferta(int numero, int codigo, int cantidad, Cliente cliente, Inventario inventario,
			supermercado mercado) {
		// TODO Auto-generated method stub
		return false;
	}

}
