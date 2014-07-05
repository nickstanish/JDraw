package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Canvas extends JPanel{
	private BufferedImage currentImage;
	private BufferedImage temporaryImage;
	public static int DEFAULT_WIDTH = 1024;
	public static int DEFAULT_HEIGHT = 536;
	/* Need some sort of scaling first..   */
	//public static int DEFAULT_WIDTH = 1920;
	//public static int DEFAULT_HEIGHT = 1080;
	public static int PADDING = 25;
	private double scale = 1;
	
	private Timer timer = new Timer((int)(1.0/30.0 * 1000), new ActionListener(){
		public void actionPerformed(ActionEvent e){
			repaint();
		}
	});
	
	public Canvas(){
		this(null);
	}
	public Canvas(BufferedImage image){
		this.currentImage = image;
		if(this.currentImage == null){
			this.currentImage = new BufferedImage(DEFAULT_WIDTH, DEFAULT_HEIGHT, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = (Graphics2D)currentImage.createGraphics();
			g.setColor(Color.white);
			g.fillRect(0, 0, this.currentImage.getWidth(), this.currentImage.getHeight());
		}
		timer.start();
	}
	public BufferedImage getTemporaryImage(){
		temporaryImage = new BufferedImage(currentImage.getWidth(), currentImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		return temporaryImage;
	}
	public void applyTemporaryImage(){
		Graphics2D g = (Graphics2D)currentImage.createGraphics();
		if(temporaryImage != null && g != null){
			g.drawImage(temporaryImage, 0, 0, temporaryImage.getWidth(), temporaryImage.getHeight(), null);
		}
		g.dispose();
	}
	public void setImage(BufferedImage image){
		this.currentImage = image;
		temporaryImage = null;
	}
	public void paintComponent(Graphics g1){
		Graphics2D g = (Graphics2D)g1;
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, getWidth(), getHeight());
		Rectangle2D drawingArea = new Rectangle2D.Double(PADDING-1, PADDING-1, currentImage.getWidth() + 2, currentImage.getHeight()+2);
		g.setColor(Color.black);
		g.fill(drawingArea);
		if(this.currentImage != null){
			g.drawImage(currentImage, PADDING, PADDING, currentImage.getWidth(), currentImage.getHeight(), null);
		}
		if(this.temporaryImage != null){
			g.drawImage(temporaryImage, PADDING, PADDING, temporaryImage.getWidth(), temporaryImage.getHeight(), null);
		}
	}
}
