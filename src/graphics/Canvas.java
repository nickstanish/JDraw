package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Canvas extends JPanel{
	private BufferedImage currentImage;
	private BufferedImage temporaryImage;
	public static int DEFAULT_WIDTH = 1920;
	public static int DEFAULT_HEIGHT = 1080;
	public static int PADDING = 25;
	private double scale = 1.0;
	
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
		resetSize();
		
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, InputEvent.CTRL_DOWN_MASK), "plus");
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK), "plus");
		
		AbstractAction plusAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				scale += 0.25;
				resetSize();
			}
		};
		getActionMap().put( "plus", plusAction );
		
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, InputEvent.CTRL_DOWN_MASK), "minus");
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_DOWN_MASK), "minus");
		AbstractAction minusAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){		
				scale -= 0.25;
				if(scale <= 0.25) scale = 0.25;
				resetSize();
			}
		};
		getActionMap().put( "minus", minusAction );
		
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
		resetSize();
	}
	public BufferedImage getImage(){
		return currentImage;
	}
	public void paintComponent(Graphics g1){
		Graphics2D g = (Graphics2D)g1;
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, getWidth(), getHeight());
		Rectangle2D drawingArea = new Rectangle2D.Double(PADDING-1, PADDING-1, getScaledWidth(currentImage) + 2, getScaledHeight(currentImage)+2);
		g.setColor(Color.black);
		g.fill(drawingArea);
		if(this.currentImage != null){
			g.drawImage(currentImage, PADDING, PADDING, getScaledWidth(currentImage), getScaledHeight(currentImage), null);
		}
		if(this.temporaryImage != null){
			g.drawImage(temporaryImage, PADDING, PADDING, getScaledWidth(temporaryImage), getScaledHeight(temporaryImage), null);
		}
	}
	public double getScale(){
		return this.scale;
	}
	public void setScale(double scale){
		this.scale = scale;
	}
	private int getScaledWidth(BufferedImage image){
		return (int)(image.getWidth() * scale);
	}
	private int getScaledHeight(BufferedImage image){
		return (int)(image.getHeight() * scale);
	}
	private void resetSize(){
		int width = (int)(currentImage.getWidth() * scale) + PADDING * 2;
		int height = (int)(currentImage.getHeight() * scale) + PADDING * 2;
		this.setPreferredSize(new Dimension(width, height));
		this.revalidate();
	}
}
