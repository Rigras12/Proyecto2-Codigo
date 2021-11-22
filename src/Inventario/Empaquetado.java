package Inventario;

import java.io.IOException;

public class Empaquetado extends Producto{
	
	//private ArrayList<loteEmpaquetado> lotesEmpaquetados=new ArrayList<loteEmpaquetado>();
	

	private double cantidadUnidad; //cantidadde gramos/ml por la unidad del paquete
	
	public Empaquetado(int codigo, String almacenamiento, String unidad,String nombre,double cantidadUnidad , String categoria, String rutaimagen) throws IOException
	{	
		super(codigo,almacenamiento,unidad,nombre,categoria,rutaimagen);
		this.cantidadUnidad=cantidadUnidad;
	}
	
	
	public double getCantidadUnidad()
	{
		return cantidadUnidad;
	}
	
	public double precioXunidad()
	{
		return super.getPrecio()/cantidadUnidad;
	}
	
	/*public ArrayList<loteEmpaquetado> getLotes()
	{
		return lotesEmpaquetados;
	}*/

	public String getFactura() 
	{
		return super.getFactura() + "Peso : " + getCantidadUnidad()  
				+" Precio por unidad : " + precioXunidad() + " Pesos " + " Precio por paquete : " + super.getPrecio() ;
	}
	public String getDescripcion()
	{
		return getFactura()+ " unidades disponibles : " + super.getTotal();
	}
	
	public String getCantidad()
	{
		String cantidad = super.getTotal() + " Unidades";
		return cantidad;
	}

	

	

	
	
}
