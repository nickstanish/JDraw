package graphics.tools;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class AbstractTool implements MouseListener, MouseMotionListener{
	public final ToolName name;
	
	public AbstractTool(ToolName name){
		this.name = name;
	}
}
