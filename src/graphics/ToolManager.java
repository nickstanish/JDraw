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
	
	public ToolManager(){
		toolBelt = new HashMap<ToolName, AbstractTool>();
		initializeTools();
	}
	private void initializeTools(){
		PencilTool pencil = new PencilTool(ToolName.PENCIL);
		EraserTool eraser = new EraserTool(ToolName.ERASER);
		registerTools(pencil, eraser);
	}
	
	private void registerTools(AbstractTool ... tools){
		for(AbstractTool tool: tools){
			toolBelt.put(tool.name, tool);
		}
	}
	public void selectTool(Component component, ToolName key){
		if(component == null) return;
		
		if(currentToolKey != null){
			AbstractTool currentTool = toolBelt.get(currentToolKey);
			component.removeMouseListener(currentTool);
			component.removeMouseMotionListener(currentTool);
		}
		if(key != null){
			AbstractTool newTool = toolBelt.get(key);
			component.addMouseListener(newTool);
			component.addMouseMotionListener(newTool);
			currentToolKey = key;
		}
	}
}
