package Inventario;
import java.util.ArrayList;

public class ListaRegistros {
	private ArrayList<Registro> Registros = new ArrayList<Registro>();
	
	
	
	
	public ArrayList<Registro> getRegistros(){
		return this.Registros;
	}
	
	
	
	
	
	public void add(Registro nuevo) {
		if (Registros.isEmpty()) {
			Registros.add(nuevo);
			
		}
		else {
			boolean incorporado = false;
			for (int i = 0; i<Registros.size();i++) {
				if (Registros.get(i).getFecha().compareTo(nuevo.getFecha())>0){
					Registros.add(i,nuevo);
					incorporado = true;
					break;
				}
			}
			if (!incorporado) {
				Registros.add(nuevo);
			}
		}
	}
	
	
	
}
