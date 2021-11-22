package Inventario;

import java.time.LocalDate;

public class loteNoEmpaquetado extends Lote{
	

	
	public loteNoEmpaquetado(String nombre,LocalDate fechaVencimiento, LocalDate fechaIngreso,double precioVenta,double precioCompra,double total)
	{
		super(nombre,fechaVencimiento,fechaIngreso,precioVenta,precioCompra,total);
		
		
	}
	
	
	
	
	public String consultarInformacion()
	{
		return super.consultarInformacion() + " Cantidad No empaquetados ; " + super.getTotal() + " unidades";
	}




	
	
	
	

}
