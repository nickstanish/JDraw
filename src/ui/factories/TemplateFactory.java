package ui.factories;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
/**
 * 
 * @author Nick Stanish
 *
 */
public class TemplateFactory {
	
	private TemplateFactory(){
	}
	public static JPanel createPanel(){
		JPanel panel = new JPanel();
		return panel;
	}
	public static JPanel createVerticalLayout(){
		JPanel panel = createPanel();
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxLayout);
		return panel;
	}
	public static JPanel createVerticalLayout(Component... components){
		JPanel panel = createVerticalLayout();
		for(Component component: components){
			panel.add(component);
		}
		return panel;
	}
	public static JPanel createHorizontalLayout(){
		JPanel panel = createPanel();
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		panel.setLayout(boxLayout);
		return panel;
	}
	public static JPanel createHorizontalLayout(Component... components){
		JPanel panel = createHorizontalLayout();
		for(Component component: components){
			panel.add(component);
		}
		return panel;
	}
}
