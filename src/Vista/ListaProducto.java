package Vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;

public class ListaProducto {
	private Color color;
	private Font font;
	private String info;
	private int code;
	private Icon icon;
	public ListaProducto(Color color,Font font, String info,int code,Icon icon) {
		// TODO Auto-generated constructor stub
		this.color=color;
		this.font=font;
		this.info=info;
		this.icon=icon;
		this.code=code;
	}

	public Color getColor()
	{
		return color;
	}
	public Font getFont()
	{
		return font;
	}
	public String getInfo()
	{
		return info;
	}
	public Icon getIcon()
	{
		return icon;
	}
	public int getCode()
	{
		return code;
	}
}
