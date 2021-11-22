package Inventario;

import java.time.LocalDate;

public class loteEmpaquetado extends Lote {
	

	
	public loteEmpaquetado(String nombre,LocalDate fechaVencimiento, LocalDate fechaIngreso,double precioVenta,double precioCompra,double total)
	{
		super(nombre,fechaVencimiento,fechaIngreso,precioVenta,precioCompra,total);
		
		
		
	}
	
	
	public String consultarInformacion()
	{
		return super.consultarInformacion() + " Total Empaquetados : " + super.getTotal();
	}
	
	
}

