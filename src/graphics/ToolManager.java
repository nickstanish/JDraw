package graphics;

import graphics.tools.AbstractTool;
import graphics.tools.ColorDropperTool;
import graphics.tools.EraserTool;
import graphics.tools.PencilTool;
import graphics.tools.ToolName;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

/**
 * 
 * @author Nick Stanish
 *
 */
public class ToolManager extends JPanel {
  /**
	 * 
	 */
  private static final long serialVersionUID = -250752377635101344L;
  private Map<ToolName, AbstractTool> toolBelt;
  private ToolName currentToolKey;
  private Canvas canvas;

  public ToolManager(Canvas canvas) {
    toolBelt = new HashMap<ToolName, AbstractTool>();
    this.canvas = canvas;
    initializeTools();
  }

  private void initializeTools() {
    PencilTool pencil = new PencilTool(ToolName.PENCIL, canvas);
    EraserTool eraser = new EraserTool(ToolName.ERASER, canvas);
    ColorDropperTool colorDropper = new ColorDropperTool(ToolName.COLOR_DROPPER, canvas);
    registerTools(pencil, eraser, colorDropper);
  }

  private void registerTools(AbstractTool... tools) {
    for (AbstractTool tool : tools) {
      toolBelt.put(tool.name, tool);
    }
  }

  public void selectTool(ToolName key) {
    if (canvas == null) {
      return;
    }
    if (currentToolKey != null) {
      AbstractTool currentTool = toolBelt.get(currentToolKey);
      canvas.removeMouseListener(currentTool);
      canvas.removeMouseMotionListener(currentTool);
    }
    if (key != null) {
      AbstractTool newTool = toolBelt.get(key);
      canvas.addMouseListener(newTool);
      canvas.addMouseMotionListener(newTool);
      currentToolKey = key;
    }
  }
}
