package system;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import utils.Files;

public class ImageFileFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
		if(file.isDirectory()) return true;
		return isValidExtension(file);
	}

	@Override
	public String getDescription() {
		return ".gif .png";
	}
	public static boolean isValidExtension(File file){
		String extension = Files.getExtension(file);
		if(extension != null){
			for(String validExtension: Files.validExtensions){
				if(extension.equals(validExtension)) return true;
			}
		}
		return false;
	}

}
