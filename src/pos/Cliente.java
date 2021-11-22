package pos;

public class Cliente {
	private int numeroCedula;
	private int edad;
	private char sexo;
	private String estadoCivil;
	private String situacionLaboral;
	private int Gastado=0;
	
	private int puntos;
	public Cliente(int numeroCedula,int edad , char sexo, String estadoCivil, String situacionLaboral) {
		this.numeroCedula=numeroCedula;
		this.edad=edad;
		this.sexo=sexo;
		this.estadoCivil=estadoCivil;
		this.situacionLaboral=situacionLaboral;
		
		puntos=0;
		
		// TODO Auto-generated constructor stub
	}
	public int getCedula()
	{
		return numeroCedula;
	}
	public int getEdad()
	{
		return edad;
	}
	public char getSexo()
	{
		return sexo;
	}
	public String getEstadoCivil()
	{
		return estadoCivil;
	}
	public String getSituacionLaboral()
	{
		return situacionLaboral;
	}
	public int getPuntos()
	{
		return puntos;
	}
	
	public void agregarPuntos(int puntos)
	{
		this.puntos+=puntos;
	}
	public void quitarPuntos(int i) {
		// TODO Auto-generated method stub
		this.puntos -= i;
	}
	public void aniadirgasto(int gasto) {
		this.Gastado += gasto;
	}
	public int getHistoricoCompras() {
		// TODO Auto-generated method stub
		return this.Gastado;
	}

}
