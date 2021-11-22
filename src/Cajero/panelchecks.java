package Cajero;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
public class panelchecks extends JPanel {
	private JCheckBox primera;
	private JCheckBox segunda;
	public panelchecks(String text1,String text2, Color color, Font fuente) {
		setLayout(new GridLayout(1,2));
		setBackground(color);
		primera = new JCheckBox(text1);
		primera.setFont(fuente);
		primera.setBackground(color);
		primera.setOpaque(true);
		add(primera);
		
		segunda = new JCheckBox(text2);
		segunda.setFont(fuente);
		segunda.setBackground(color);
		segunda.setOpaque(true);
		add(segunda);
	}
	
	
	public boolean getprimera() {
		return primera.isSelected();
	}
	
	public boolean getsegunda() {
		return segunda.isSelected();
	}
}
