package ui;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

public class DrawMenuBar extends MenuBar{
	public MenuItem openItem, pencil, eraser;
	public DrawMenuBar(){
		super();
		Menu fileMenu = new Menu("File");
		openItem = new MenuItem("Open");
		pencil = new MenuItem("Pencil");
		eraser = new MenuItem("Eraser");
		fileMenu.add(openItem);
		fileMenu.add(pencil);
		fileMenu.add(eraser);
		
		
		this.add(fileMenu);
	}
}
