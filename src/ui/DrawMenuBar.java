package ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/**
 * 
 * @author Nick Stanish
 *
 */
public class DrawMenuBar extends JMenuBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4422054701603252754L;
	public JMenuItem openItem, saveItem;
	public DrawMenuBar(){
		super();
		JMenu fileMenu = new JMenu("File");
		openItem = new JMenuItem("Open");
		//TODO: openItem.setAccelerator(keyStroke..);
		saveItem = new JMenuItem("Save");
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		
		this.add(fileMenu);
	}
}
