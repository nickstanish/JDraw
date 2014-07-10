package ui;

import graphics.ToolManager;
import graphics.tools.ToolName;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
/**
 * 
 * @author Nick Stanish
 *
 */
public class DrawToolbar extends JToolBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1857009543972798168L;
	public static String DEFAULT_NAME = "Tools";
	private ToolManager toolManager;
	JToggleButton pencilButton, eraserButton;
	JButton colorButton;
	ButtonGroup toolsGroup;
	
	public DrawToolbar(ToolManager toolManager){
		super(DEFAULT_NAME, JToolBar.VERTICAL);
		this.toolManager = toolManager;
		this.initialize();
	}
	private void initialize(){
		this.setFloatable(false);
		toolsGroup = new ButtonGroup();
		
		pencilButton = new DrawToggleButton("Pencil", pencilListener, toolsGroup);
		eraserButton = new DrawToggleButton("Eraser", eraserListener, toolsGroup);
		
		colorButton = new JButton("Color");
		this.add(pencilButton, eraserButton, colorButton);
	}
	private void add(Component ... components){
		for(Component component: components){
			this.add(component);
		}
	}
	private ActionListener pencilListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			toolManager.selectTool(ToolName.PENCIL);
		}
	};
	private ActionListener eraserListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			toolManager.selectTool(ToolName.ERASER);
		}
	};
}
