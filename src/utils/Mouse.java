package utils;

import graphics.Canvas;

import java.awt.geom.Point2D;

public class Mouse {
	public static Point2D getCanvasPoint(Canvas canvas, Point2D point){
		double scale = canvas.getScale();
		double newX = (point.getX() - canvas.PADDING) / scale;
		double newY =  (point.getY() - canvas.PADDING) / scale;
		return new Point2D.Double(newX, newY);
	}
}
