package Vista;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelDescripcion extends JPanel{
	private JLabel lblUnidades;
	private JLabel lblLotes;
	private JLabel lblBalance;
	private JLabel lblLote;
	private JLabel lblDescripcion;
	private JLabel lblCodigo;
	private JLabel lblNombre;
	private ImageIcon img;
	private JLabel lblImage;
	public PanelDescripcion(PanelPrincipalInventario principal1) {
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(7,1));
		setBorder(new TitledBorder("Información"));
		lblNombre=new JLabel("Nombre producto : ");
		add(lblNombre);
		
		
		img= new ImageIcon("./data/"+principal1.getProductoActual()+ ".png");
		Image image = img.getImage(); 
		Image newimg = image.getScaledInstance(130, 130,  java.awt.Image.SCALE_SMOOTH); 
		img = new ImageIcon(newimg);  
		lblImage=new JLabel(img);
		add(lblImage);
		
		lblCodigo=new JLabel("Codigo producto : ");
		add(lblCodigo);
		lblUnidades=new JLabel("unidades : ");
		add(lblUnidades);
		
		lblLotes=new JLabel(" Cantidad Lotes : ");
		add(lblLotes);
		
		lblBalance= new JLabel(" Balance : ");
		add(lblBalance);
		
		lblLote=new JLabel("Lote numero : ");
		add(lblLote);
		
		
		
		
		
		
	}
	
	public void actualizarDescripcion(int codigo, String nombre,String unidades, String balance,int lotes ) 
	{	
		lblNombre.setText("Nombre : " +nombre);
		lblCodigo.setText("Codigo : " +codigo);
		lblUnidades.setText("unidades : " + unidades);
		lblLotes.setText("Cantidad lotes : " + lotes);
		lblBalance.setText(" Balance : " + balance );
		lblLote.setText("Lote numero : 1");
		
		img= new ImageIcon("./data/"+codigo+ ".png");
		Image image = img.getImage(); 
		Image newimg = image.getScaledInstance(130, 130,  java.awt.Image.SCALE_SMOOTH); 
		img = new ImageIcon(newimg);  
		lblImage.setIcon(img);
		
		
	}
	
	
	
	
	
	public void setLote(String lote) {
		lblLote.setText("Lote numero : " +lote);
	}
}
