package main;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import ui.DrawWindow;
/**
 * 
 * @author Nick Stanish
 *
 */
public class JDraw {
	
	public static void main(String[] args) {
		for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
			System.out.println(info.toString());
		}
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new DrawWindow();
	}

}
