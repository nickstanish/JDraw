package graphics.tools;

import graphics.Canvas;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import utils.Mouse;

public class ColorDropperTool extends AbstractTool {
  Color selectedColor;
  BufferedImage temporaryImage;

  // Robot robot;



  public ColorDropperTool(ToolName name, Canvas canvas) {
    super(name, canvas);
    /*
     * try { robot = new Robot(); } catch (AWTException e) { System.err.println(e);
     * System.err.println("Robot not supported"); }
     */

  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub
    Point point = e.getPoint();
    Point2D imagePoint = Mouse.getCanvasPoint(canvas, point);
    if (!canvas.isPointOnImage(imagePoint)) {
      return;
    }
    /*
     * if (robot != null) { // TODO: create a nearly transparent window to intercept clicks with
     * selectedColor = robot.getPixelColor(point.x, point.y); } else { // selectedColor =
     * canvas.getColorAt(point2D);
     * 
     * }
     */
    selectedColor = canvas.getColorAt(imagePoint);

    System.out.println(selectedColor.getRed() + ", " + selectedColor.getGreen() + ", "
        + selectedColor.getBlue());
    canvas.setToolForegroundColor(selectedColor);

  }

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  @Override
  public void mouseDragged(MouseEvent e) {}

  @Override
  public void mouseMoved(MouseEvent e) {}

}
