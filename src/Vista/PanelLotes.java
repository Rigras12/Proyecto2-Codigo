package Vista;
import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.util.ArrayList;

import javax.swing.*;

public class PanelLotes extends JFrame {
	ArrayList<String> lotes;
	
	public PanelLotes(ArrayList<String> listalotes, Color color, Font font) {
		this.lotes = listalotes;
		
		
		JList<ListaProducto> lista=new JList();
		
		String info = "";
		for (int i = 0;i<lotes.size();i++) {
			info = lotes.get(i);
			
		}
		
		
		
		JScrollPane scroll = new JScrollPane(lista,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		add(scroll);
	}
	
	
	
	
	
	
	
}
