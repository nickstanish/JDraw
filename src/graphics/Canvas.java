package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Canvas extends JPanel{
	private BufferedImage currentImage;
	private Timer timer = new Timer((int)(1.0/35.0 * 1000), new ActionListener(){
		public void actionPerformed(ActionEvent e){
			repaint();
		}
	});
	
	public Canvas(){
		this(null);
	}
	public Canvas(BufferedImage image){
		this.currentImage = image;
		timer.start();
	}
	public void setImage(BufferedImage image){
		this.currentImage = image;
	}
	public void paintComponent(Graphics g1){
		Graphics2D g = (Graphics2D)g1;
		g.setColor(Color.WHITE);
		g.clearRect(0, 0, getWidth(), getHeight());
		if(this.currentImage != null){
			g.drawImage(currentImage, 0, 0, getWidth(), getHeight(), null);
		}
	}
}
