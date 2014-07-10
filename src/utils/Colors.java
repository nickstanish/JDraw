package utils;

import java.awt.Color;
/**
 * 
 * @author Nick Stanish
 *
 */
public class Colors {
	public static Color randomColor(){
		float red = (float)Math.random();
		float blue = (float)Math.random();
		float green = (float)Math.random();
		return new Color(red, green, blue);
	}
}
