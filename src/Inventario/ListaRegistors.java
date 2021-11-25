package Inventario;
import java.util.ArrayList;

public class ListaRegistors {
	private ArrayList<Registro> Registros = new ArrayList<Registro>();
	
	
	
	
	public ArrayList<Registro> darRegistros(){
		return this.Registros;
	}
	
	
	public void OrganizarRegistros() {
		//metodo quicksort
		
	}
	
	
	public void add(Registro nuevo) {
		this.Registros.add(nuevo);
	}
	
	
	private QuickSort() {
		
	}
}
