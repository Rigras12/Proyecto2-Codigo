package Inventario;

import java.time.LocalDate;

public class Registro {
	private LocalDate fecha;
	private int Cantidad;
	
	public Registro(LocalDate fechacreacion,int Cosa) {
		this.Cantidad = Cosa;
		this.fecha = fechacreacion;
		
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public int getCantidad() {
		return Cantidad;
	}
	
	public void setCantidad(int nueva) {
		this.Cantidad = nueva;
	}
}
