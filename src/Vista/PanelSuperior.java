package Vista;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelSuperior extends JPanel{
	private JLabel lblFecha;
	private JLabel lblTotal;
	private JLabel lblLotes;
	private PanelPrincipalInventario principal;
	public PanelSuperior(String fecha, int total, int lotes,PanelPrincipalInventario principal1) {
		// TODO Auto-generated constructor stub
		setBorder(new TitledBorder("Información general"));
		setLayout(new GridLayout(0,3));
		lblFecha=new JLabel("fecha : " + fecha);
		lblTotal=new JLabel("Total de productos : " + total);
		lblLotes=new JLabel("Total de Lotes : " + lotes);
		this.principal = principal1;
		
		add(lblFecha);
		add(lblLotes);
		add(lblTotal);
		
	}
	
	public void setTotal(int total)
	{
		lblTotal.setText("Total de productos : " + total);
		
	}
	
	public void setFecha(String fecha)
	
	{
		lblFecha.setText("Fecha : " + fecha);
		
	}
	
	public void setLotes(int lotes)
	{
		lblLotes.setText("Total de lotes : " + lotes);
	}

}
