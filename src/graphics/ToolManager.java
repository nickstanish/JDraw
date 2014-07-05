package graphics;

import graphics.tools.AbstractTool;
import graphics.tools.EraserTool;
import graphics.tools.PencilTool;
import graphics.tools.ToolName;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class ToolManager extends JPanel{
	private Map<ToolName, AbstractTool>toolBelt;
	private ToolName currentToolKey;
	private Canvas canvas;
	
	public ToolManager(Canvas canvas){
		toolBelt = new HashMap<ToolName, AbstractTool>();
		this.canvas = canvas;
		initializeTools();
	}
	private void initializeTools(){
		PencilTool pencil = new PencilTool(ToolName.PENCIL, canvas);
		EraserTool eraser = new EraserTool(ToolName.ERASER, canvas);
		registerTools(pencil, eraser);
	}
	
	private void registerTools(AbstractTool ... tools){
		for(AbstractTool tool: tools){
			toolBelt.put(tool.name, tool);
		}
	}
	public void selectTool(ToolName key){
		if(canvas == null) return;
		
		if(currentToolKey != null){
			AbstractTool currentTool = toolBelt.get(currentToolKey);
			canvas.removeMouseListener(currentTool);
			canvas.removeMouseMotionListener(currentTool);
		}
		if(key != null){
			AbstractTool newTool = toolBelt.get(key);
			canvas.addMouseListener(newTool);
			canvas.addMouseMotionListener(newTool);
			currentToolKey = key;
		}
	}
}
