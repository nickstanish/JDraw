package system;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 
 * @author Nick Stanish
 *
 */
public class FileChooser extends JFileChooser{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6094125932801077317L;

	public FileChooser(){
		super();
		this.setFileFilter(new ImageFileFilter());
		this.setAcceptAllFileFilterUsed(false);
	}
	
	public File openDialog(Component parent){
		File file = null;
		int value = this.showOpenDialog(parent);
		if(value == JFileChooser.APPROVE_OPTION){
			file = this.getSelectedFile();
			
		}
		return file;
	}
	
	public File saveDialog(Component parent){
		File file = null;
		int value = this.showSaveDialog(parent);
		if(value == JFileChooser.APPROVE_OPTION){
			file = this.getSelectedFile();
		}
		return file;
	}
	
	public static void main(String[] args){
		final FileChooser chooser = new FileChooser();
		
		final JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton open = new JButton("Open");
		JButton save = new JButton("Save");
		panel.add(open);
		panel.add(save);
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(300, 300);
		open.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				File file = chooser.openDialog(frame);
				if(file == null) return;
				System.out.println(file.getName());
			}
		});
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				File file = chooser.saveDialog(frame);
				if(file == null) return;
				System.out.println(file.getName());
			}
		});
		
	}

}
