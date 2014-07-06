package ui;

import graphics.Canvas;
import graphics.ToolManager;
import graphics.tools.ToolName;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import sun.org.mozilla.javascript.internal.annotations.JSConstructor;
import system.FileChooser;
import system.ImageFileFilter;
import utils.Files;

public class DrawWindow extends JFrame{
	
	
	public static int DEFAULT_WIDTH = 400;
	public static int DEFAULT_HEIGHT = 400;
	public static String DEFAULT_TITLE = "DrawWindow";
	public static String version = "0.0.0";
	
	public static FileChooser fileChooser;
	public Canvas canvas;
	public JScrollPane scrollCanvas;
	public ToolManager toolManager;
	
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
		menuBar.pencil.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				toolManager.selectTool(ToolName.PENCIL);
			}
		});
		menuBar.eraser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				toolManager.selectTool(ToolName.ERASER);
			}
		});
		
		this.setMenuBar(menuBar);
		canvas = new Canvas();
		this.scrollCanvas = new JScrollPane(canvas, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.toolManager= new ToolManager(canvas);
		this.getContentPane().add(scrollCanvas);
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
	
}
