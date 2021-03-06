package graphics.tools;

import graphics.Canvas;
import graphics.Path;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import utils.Mouse;
/**
 * 
 * @author Nick Stanish
 *
 */
public class EraserTool extends AbstractTool{
	BufferedImage temporaryImage;
	Path path;
	boolean drawing = false;
	
	public void updateGraphics(){
		Graphics2D g = (Graphics2D)temporaryImage.createGraphics();
		g.setColor(new Color(0, 0, 0, 0));
		g.fillRect(0, 0, temporaryImage.getWidth(), temporaryImage.getHeight());
		g.setColor(Color.white);
		BasicStroke stroke = new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g.setStroke(stroke);
		path.color = Color.white;
		path.draw(g);
		g.dispose();
	}
	
	public EraserTool(ToolName name, Canvas canvas) {
		super(name, canvas);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		temporaryImage = canvas.getTemporaryImage();
		path = new Path();
		path.addPoint(Mouse.getCanvasPoint(canvas, e.getPoint()));
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		path.addPoint(Mouse.getCanvasPoint(canvas, e.getPoint()));
		updateGraphics();
		canvas.applyTemporaryImage();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(path != null){
			//System.out.println(path);
			path.addPoint(Mouse.getCanvasPoint(canvas, e.getPoint()));
			updateGraphics();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		
	}

}
