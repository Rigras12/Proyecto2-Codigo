package Inventario;

import java.time.LocalDate;

public abstract class Lote {

	
	private String nombre;
	private LocalDate fechaVencimiento ;
	private LocalDate fechaIngreso;
	private double precioVenta;
	private double precioCompra;
	private double total;
	public Lote(String nombre, LocalDate fechaVencimiento, LocalDate fechaIngreso,double precioVenta, double precioCompra,double total) 
	
	{
		this.nombre=nombre;
		this.fechaVencimiento=fechaVencimiento;
		this.fechaIngreso=fechaIngreso;
		this.precioVenta=precioVenta;
		this.precioCompra=precioCompra;
		this.total=total;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	
	public LocalDate getVencimiento()
	{
		return fechaVencimiento;
	}
	public LocalDate getIngreso()
	{
		return fechaIngreso;
	}
	public double getPrecioVenta()
	{
		return precioVenta;
	}
	public double getPrecioCompra()
	{
		return precioCompra;
	}
	public double getTotal()
	{
		return total;
	}
	public void quitarProductos(double cantidad)
	{
		total-=cantidad;
	}
	public String consultarInformacion()
	{
		return "Fecha de Vencimiento : " + getVencimiento() + ", Fecha Ingreso :  " + getIngreso() +", Precio compra :  " 
				+ getPrecioCompra() + ", Precio venta: "  + getPrecioVenta(); 
	}
	public boolean vigente(LocalDate actual) 
	{	
		
		if (getVencimiento().getYear()>actual.getYear())
		{
			return true;
		}
		else if (getVencimiento().getYear()==actual.getYear())
		{
			if(getVencimiento().getDayOfYear()>=actual.getDayOfYear())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		
		
	}
	
}
