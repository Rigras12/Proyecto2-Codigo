package Cajero;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;

import com.opencsv.CSVWriter;

import Inventario.Inventario;
import pos.supermercado;
public class VentanaCajero extends JFrame {
	private Font font;
	private Color color;
	private LocalDate fechahoy;
	private Inventario inventario;
	private supermercado mercado;
	private PanelSuperiorCajero panelsuperior;
	private PanelMenuCaja panelcentro;
	public VentanaCajero(String fechap) throws IOException {
		setSize(200,600);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(color.WHITE);
		font=new Font("Serif", Font.BOLD, 18);
		color=Color.RED;
		inventario = cargarInventario("./data/Inventario.csv");
		fechahoy = LocalDate.parse(fechap);
		mercado = new supermercado(fechahoy,inventario);
		
		panelsuperior = new PanelSuperiorCajero(fechap,"Mercado");
		panelcentro = new PanelMenuCaja(this,font,color);
		
		
		
		
		add(panelsuperior,BorderLayout.NORTH);
		add(panelcentro,BorderLayout.CENTER);
	}
	
	
	
	public static void main(String[] args) throws IOException {
		VentanaCajero inicio = new VentanaCajero("2020-04-10");
		inicio.setVisible(true);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	private Inventario cargarInventario(String ruta) throws IOException 
	{
		Inventario inventario = new Inventario();
		BufferedReader br = null;
		try {
	         
	         br =new BufferedReader(new FileReader(ruta));
	         String line = br.readLine();
	         while (null!=line) {
	            String [] fields = line.split(",");
	            
	            if (fields[0].charAt(0) == '\"')
	            {
	            	fields[0] = fields[0].substring(1);
	            	fields[9] = fields[9].substring(0, fields[9].length()-1);
	            }
	            
	            
	            if ((fields[8].equals("-1"))||(fields[8].equals("-1.0")))
	            {
	           
	            	inventario.cargarProducto(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3], Double.parseDouble(fields[4]),fields[5], Double.parseDouble(fields[6]), Double.parseDouble(fields[7]),fields[9],fields[10],fields[11]);
	            }
	            else
	            {
	            	
	            	inventario.cargarProducto(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3], Double.parseDouble(fields[4]),fields[5], Double.parseDouble(fields[6]), Double.parseDouble(fields[7]), Double.parseDouble(fields[8]),fields[9],fields[10],fields[11]);
	            	
	            }
	            
	            
	            line = br.readLine();
	         }
	         
	      } catch (Exception e) {
	    	  System.err.println("Error! "+e.getMessage());
	      } finally {
	         if (null!=br) {
	            br.close();
	         }
	      }
		return inventario;
	}



	public void registrarCompra() {
		VentanaCompra ventanacompra = new VentanaCompra(inventario,fechahoy.toString(),mercado);
		ventanacompra.setVisible(true);
	}



	public void registrarUsuario() {
		// TODO Auto-generated method stub
		VentanaRegistro ventanaregistro = new VentanaRegistro(mercado, fechahoy.toString());
		ventanaregistro.setVisible(true);
	}



	public void consultarPuntos() {
		// TODO Auto-generated method stub
		String cedula = JOptionPane.showInputDialog("Ingrese la cedula");
		int puntos = mercado.VerPuntosdeClientes(Integer.parseInt(cedula));
		JOptionPane.showConfirmDialog(this, "El cliente con la cedula " + cedula + " tiene " + puntos +" puntos.");
	}



	public void guardarySalir() {
		// TODO Auto-generated method stub
		
	}
}
