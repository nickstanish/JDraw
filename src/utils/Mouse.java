package utils;

import graphics.Canvas;

import java.awt.geom.Point2D;

/**
 * 
 * @author Nick Stanish
 *
 */
public class Mouse {
  public static Point2D getCanvasPoint(Canvas canvas, Point2D point) {

    double scale = canvas.getScale();
    double newX = (point.getX() - Canvas.PADDING) / scale;
    double newY = (point.getY() - Canvas.PADDING) / scale;
    // System.out.println(point.getX() + ", " + point.getY() + " ---> " + newX + ", " + newY);
    return new Point2D.Double(newX, newY);
  }
}
