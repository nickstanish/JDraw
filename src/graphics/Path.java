package graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Nick Stanish
 *
 */
public class Path {
	public List<Point2D> points;
	public Color color;
	
	public Path(){
		this.points = new ArrayList<Point2D>();
	}
	public void addPoint(Point2D point){
		this.points.add(point);
	}
	public void draw(Graphics2D g){
		if(points.size() < 2) return;
		if(color == null) color = Color.BLACK;
		Color oldColor = g.getColor();
		g.setColor(color);
		for(int i = 0; i < points.size() - 1; i++){
			Line2D line = new Line2D.Double(points.get(i), points.get(i+1));
			g.draw(line);
		}
		g.setColor(oldColor);
	}
}
