package ui;

import graphics.Canvas;
import graphics.ToolManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import system.FileChooser;
import system.ImageFileFilter;
import ui.factories.TemplateFactory;
import utils.Files;
/**
 * 
 * @author Nick Stanish
 *
 */
public class DrawWindow extends JFrame{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9222809857750402480L;
	public static int DEFAULT_WIDTH = 400;
	public static int DEFAULT_HEIGHT = 400;
	public static String DEFAULT_TITLE = "DrawWindow";
	public static String version = "0.0.0";
	
	public static FileChooser fileChooser;
	public Canvas canvas;
	public JScrollPane scrollCanvas;
	public ToolManager toolManager;
	public DrawToolbar drawToolbar;
	public JPanel container;
	
	public DrawWindow(){
		this(DEFAULT_TITLE);
	}
	
	public DrawWindow(String title){
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initialize();
		this.pack();
		this.setVisible(true);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	private void initialize(){
		DrawMenuBar menuBar = new DrawMenuBar();
		
		fileChooser = new FileChooser();
		menuBar.openItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				openFile();
			}
		});
		menuBar.saveItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveFile();
			}
		});
		
		this.setJMenuBar(menuBar);
		canvas = new Canvas();
		this.scrollCanvas = new JScrollPane(canvas, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.toolManager= new ToolManager(canvas);
		drawToolbar = new DrawToolbar(this.toolManager);
		this.container = TemplateFactory.createHorizontalLayout(drawToolbar, scrollCanvas);
		this.getContentPane().add(container);
		setShortcuts();
	}
	private void openFile(){
		File file = fileChooser.openDialog(this);
		if(file == null || !file.exists() || !ImageFileFilter.isValidExtension(file)){
			System.err.println("This file cannot be used");
			return;
		}
		try {
			BufferedImage image = ImageIO.read(file);
			canvas.setImage(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void saveFile(){
		File file = fileChooser.saveDialog(this);
		BufferedImage image = canvas.getImage();
		if(file == null ){
			System.err.println("cancelled");
			return;
		}
		try {
			String formatName = Files.validExtensions[0];
			if(ImageFileFilter.isValidExtension(file)){
				formatName = Files.getExtension(file);
			}
			else{
				file = new File(file + "." + formatName);
			}
			ImageIO.write(image, formatName, file);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Unable to save file: " + file);
		}
	}
	private void setShortcuts(){
		container.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, InputEvent.CTRL_DOWN_MASK), "plus");
		container.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK), "plus");
		container.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, InputEvent.CTRL_DOWN_MASK), "minus");
		container.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_DOWN_MASK), "minus");
		
		AbstractAction plusAction = new AbstractAction(){
			private static final long serialVersionUID = 7133234980171337805L;

			public void actionPerformed(ActionEvent e){
				canvas.setScale(canvas.getScale() + 0.25);
				canvas.resetSize();
			}
		};
		AbstractAction minusAction = new AbstractAction(){
			private static final long serialVersionUID = -581488417415204226L;

			public void actionPerformed(ActionEvent e){		
				canvas.setScale(canvas.getScale() - 0.25);
				canvas.resetSize();
			}
		};
		
		container.getActionMap().put( "plus", plusAction );
		container.getActionMap().put( "minus", minusAction );
	}
	
}
