package Cajero;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;

import com.opencsv.CSVWriter;

import Inventario.Inventario;
import pos.supermercado;
public class VentanaCompra extends JFrame {
	
	private PanelSuperiorCajero panelsuperior;
	private PanelOpcionesCompra panelcompras;
	private PanelOpcionesCliente panelcliente;
	private supermercado mercado;
	private Font font;
	private Color color;
	private String fecha;
	
	public VentanaCompra(Inventario inventario, String fecha,supermercado mercados) {
		setSize(900,600);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		color = Color.WHITE;
		setBackground(Color.WHITE);
		this.fecha = fecha;
		panelsuperior = new PanelSuperiorCajero(fecha,"  Registro de compas");
		this.mercado = mercados;
		panelcompras = new PanelOpcionesCompra(this, color,font);
		panelcliente = new PanelOpcionesCliente(this, color, font);
		
		add(panelcompras,BorderLayout.CENTER);
		add(panelsuperior,BorderLayout.NORTH);
		add(panelcliente,BorderLayout.SOUTH);
	}
	
	public supermercado getSupermercado()
	{
		return mercado;
	}
	public void quitarProducto() {
		// TODO Auto-generated method stub
		
		String codigo =  JOptionPane.showInputDialog("Ingrese el codigo del producto");
		boolean resultado = mercado.RetirarCompra(Integer.valueOf(codigo),panelcompras.getCantidad());
		if (resultado) {
			actualizarPaneles();
		} else {
			JOptionPane.showConfirmDialog(this,"Error retirando la compra");
		}
		actualizarScroll();
		
		
	}

	public void aniadirProducto() {
		// TODO Auto-generated method stub
		
		String codigo =  JOptionPane.showInputDialog("Ingrese el codigo del producto");
		boolean resultado = mercado.RegistrarCompra(Integer.valueOf(codigo), panelcompras.getCantidad());
		System.out.println(mercado.getPuntosActuales());
		if (resultado) {
			actualizarPaneles();
		} else {
			JOptionPane.showConfirmDialog(this,"El producto no existe o no quedan unidades en inventario");
		}
		actualizarScroll();
	}

	private void actualizarPaneles() {
		// TODO Auto-generated method stub
		
		panelcompras.setPuntos(mercado.getPuntosActuales());
		panelcompras.setTotal(mercado.getTotal());
		if (mercado.estaRegistrado(panelcliente.getCedula())) {
			panelcliente.setComprastotales(mercado.getHistoricoComprasCliente());
			panelcliente.setPuntostotales(mercado.VerPuntosdeClientes(panelcliente.getCedula()));
		}
		else {
			panelcliente.setComprastotales(0);
			panelcliente.setPuntostotales(0);
		}
	}

	public void finalizarCompra() {
		// TODO Auto-generated method stub
		mercado.finalizarCompra();
		try {
			actualizarinventario("./data/Inventario.csv",mercado.getInventario());
			System.out.println("subanos monito");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error bb");
		}
		JOptionPane.showConfirmDialog(this,"Compra finalizada");
		System.exit(0);
		
	}

	public void ingresarCliente() {
		// TODO Auto-generated method stub
		try {
			mercado.cambiarCliente(panelcliente.getCedula());
			JOptionPane.showConfirmDialog(this, "Ingresado correctamente");
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(this, "Numero de cedula no encontrado");
		}
		
	}

	public void registrarCliente() {
		// TODO Auto-generated method stub
		VentanaRegistro registro = new VentanaRegistro(mercado,fecha);
		registro.setVisible(true);
		repaint();
	}
	
	public void actualizarScroll() 
	{
		panelcompras.actualizarScroll();
	}
	public void actualizarinventario(String ruta, Inventario inventario) throws IOException
	 {
		 System.out.println("Actualizando inventario...");
		 ArrayList<String[]> productos = new ArrayList<String[]>();
		 inventario.getArticulos().forEach((key,producto)->
		 {
			 
			 productos.add(inventario.actualizarProducto(producto));
			 
		 });
		 
		 CSVWriter writer = new CSVWriter(new FileWriter(ruta),CSVWriter.DEFAULT_SEPARATOR , CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
		 
		 writer.writeAll(productos);
		 writer.close();
		 
		 System.out.println("\nInventario actualizado");
		 System.out.println("\nSaliendo de la aplicacion...");
	 }

}
