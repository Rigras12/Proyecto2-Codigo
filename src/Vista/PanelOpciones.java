package Vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelOpciones extends JPanel implements ActionListener{
	private JButton btnEscogerProducto;
	private JButton btnCargarImagen;
	private JButton btnCargarLotes;
	private JButton btnBorrarLotes;
	private JButton btnBorrarLote;
	private JButton btnCambiarFecha;
	private JButton btnSeleccionarLote;
	private JButton btnVerLote;
	private JButton btnVerDescripcion;
	private PanelPrincipalInventario principal;
	private String [] textos;
	public PanelOpciones(PanelPrincipalInventario principal1) {
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(12,1));
		setBorder(new TitledBorder("Opciones"));
		textos = new String[] {"Cargar Lotes","Borrar Lotes vencidos","Borrar Lote","Cambiar fecha","Ver lote","Ver descripcion"};
		this.principal = principal1;
	
		
		btnCargarLotes=new JButton("Cargar Lotes");
		btnCargarLotes.addActionListener(this);
		btnCargarLotes.setActionCommand("Cargar Lotes");
		add(btnCargarLotes);
		add(new JLabel(""));
		
		btnBorrarLotes=new JButton("Borrar Lotes vencidos");
		btnBorrarLotes.addActionListener(this);
		btnBorrarLotes.setActionCommand("Borrar Lotes vencidos");
		add(btnBorrarLotes);
		add(new JLabel(""));
		
		btnBorrarLote=new JButton("Borrar Lote");
		btnBorrarLote.addActionListener(this);
		btnBorrarLote.setActionCommand("Borrar Lote");
		add(btnBorrarLote);
		add(new JLabel(""));
		
		btnCambiarFecha=new JButton("Cambiar fecha");
		btnCambiarFecha.addActionListener(this);
		btnCambiarFecha.setActionCommand("Cambiar fecha");
		add(btnCambiarFecha);
		add(new JLabel(""));
		
		
		btnVerLote=new JButton("Ver lote");
		btnVerLote.addActionListener(this);
		btnVerLote.setActionCommand("Ver lote");
		add(btnVerLote);
		add(new JLabel(""));
		
		btnVerDescripcion= new JButton("Ver descripcion");
		btnVerDescripcion.addActionListener(this);
		btnVerDescripcion.setActionCommand("Ver descripcion");
		add(btnVerDescripcion);
		add(new JLabel(""));
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando=e.getActionCommand();
	
		if(comando.equals(textos[0]))
		{
			try {
				principal.cargarLotes();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this, "Fallo cargando los lotes");
				e1.printStackTrace();
			}
		}
		else if (comando.equals(textos[1]))
		{
			principal.borrarLotes();
		}
		else if (comando.equals(textos[2]))
		{
			principal.borrarLote();
		}
		else if (comando.equals(textos[3]))
		{
			principal.cambiarFecha();
		}
		else if (comando.equals(textos[4]))
		{
			principal.verLote();
		}
		else if (comando.equals(textos[5]))
		{
			principal.verDescripcion();
		}
		
	}
}
