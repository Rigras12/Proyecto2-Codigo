package pos;

import java.time.LocalDate;

public class Oferta {
	private LocalDate fechainicio;
	private LocalDate fechafin;
	private int tipo;
	// si tipo es 1 la oferta
	
	public Oferta(LocalDate inicio,LocalDate fin, ) {
		this.fechainicio = inicio;
		this.fechafin = fin;
		this.tipo = tipos;
		
	}
	
	
	
	public boolean esValida(LocalDate hoy) {
		if (hoy.compareTo(fechainicio)>=0 && hoy.compareTo(fechafin)<= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	getOferta
	
}

