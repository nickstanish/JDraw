package graphics.tools;

import graphics.Canvas;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class AbstractTool implements MouseListener, MouseMotionListener{
	public final ToolName name;
	protected Canvas canvas;
	
	public AbstractTool(ToolName name, Canvas canvas){
		this.name = name;
		this.canvas = canvas;
	}
}
