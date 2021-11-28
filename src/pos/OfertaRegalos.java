package pos;

import Inventario.Inventario;

public class OfertaRegalos implements MetodoCompra {
	private int adicional;
	private int minimo; 
	
	public OfertaRegalos (int min, int add){
		adicional = add;
		minimo = min;
	}
	
	
	public boolean AplicarOferta(int numero, int codigo, int cantidad, Cliente cliente, Inventario inventario,
			supermercado mercado) {
		// TODO Auto-generated method stub
		return false;
	}

	
	

}
