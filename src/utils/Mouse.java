package utils;

import graphics.Canvas;

import java.awt.geom.Point2D;

public class Mouse {
	public static Point2D getCanvasPoint(Point2D point){
		return new Point2D.Double(point.getX() - Canvas.PADDING, point.getY() - Canvas.PADDING);
	}
}
