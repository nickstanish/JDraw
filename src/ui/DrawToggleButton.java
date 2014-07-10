package ui;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
/**
 * 
 * @author Nick Stanish
 *
 */
public class DrawToggleButton extends JToggleButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3637072388952346639L;

	public DrawToggleButton(String text, ActionListener listener, ButtonGroup buttonGroup){
		super(text);
		this.addActionListener(listener);
		buttonGroup.add(this);
	}
}
