package utils;

import java.io.File;
/**
 * 
 * @author Nick Stanish
 *
 */
public class Files {
	public static String getExtension(File file) {
		String ext = null;
		String s = file.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}
	public static String[] validExtensions = {"png","gif"};
}
