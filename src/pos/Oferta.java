package pos;

import java.time.LocalDate;

public class Oferta {
	private LocalDate fechainicio;
	private LocalDate fechafin;
	private MetodoCompra tipo;
	// si tipo es 1 la oferta
	
	public Oferta(LocalDate inicio,LocalDate fin, MetodoCompra tipoOferta) {
		this.fechainicio = inicio;
		this.fechafin = fin;
		tipo = tipoOferta;
	}
	
	
	
	public boolean esValida(LocalDate hoy) {
		if (hoy.compareTo(fechainicio)>=0 && hoy.compareTo(fechafin)<= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public MetodoCompra getOferta(){
		return this.tipo;
	}
	
}

