package pos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Combos {

	
	private ArrayList<Integer> productos; 
	
	private OfertaDescuento Aplicador;
	private int puntos;
	private int precio;
	private int descuento;
	
	public Combos( ArrayList<Integer> listaproductos, int desc) {
		
		productos = listaproductos;
		Aplicador = new OfertaDescuento(desc);
		descuento = desc;
	}
	
	
	
	
	
	public ArrayList<Integer> getCombo(){
		return productos;
	}
	
	public int getDescuento() {
		return descuento;
	}
	
	public OfertaDescuento getOferta() {
		return Aplicador;
	}
}

