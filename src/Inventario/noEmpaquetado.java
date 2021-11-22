package Inventario;


import java.io.IOException;
import java.util.ArrayList;

public class noEmpaquetado extends Producto{
	
	
	//private ArrayList<loteNoEmpaquetado> lotesNoEmpaquetados= new ArrayList<loteNoEmpaquetado>();
	
	public noEmpaquetado(int codigo, String almacenamiento,String unidad,String nombre,String categoria, String rutaimagen) throws IOException
	{
		super(codigo,almacenamiento,unidad,nombre,categoria,rutaimagen);
		
		
	}
	

	
	public String getFactura()
	{
		return super.getFactura() + " Precio por unidad : " + super.getPrecio();
	}
	public String getDescripcion()
	{
		return getFactura() + " Cantidad Total : " + super.getTotal();
	}
	
	public String getCantidad()
	{
		return super.getTotal() + " "  + super.getUnidad();
	}



	@Override
	public double getCantidadUnidad() {
		// TODO Auto-generated method stub
		return -1;
	}
	}
	/*
	public ArrayList<loteNoEmpaquetado> getLotes()
	{
		return lotesNoEmpaquetados;
	}*/
	
	

	

	

