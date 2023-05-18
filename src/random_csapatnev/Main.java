package random_csapatnev;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Program belépési osztálya.
 */
public class Main implements Serializable
{
	static MainFrame mf;
	static JFrame StartFrame;
	/**
	 * Program belépési pontja
	 * @param args Program paraméterei.
	 */
	public static void main(String[] args) 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		StartFrame();
	}
	
	@SuppressWarnings("unchecked")
	private static void load(String[] split) {
		if(split.length >= 2) {
			try {
				System.out.println("load " + split[1] + " Sikeres!");
				FileInputStream fis = new FileInputStream(split[1]);
				ObjectInputStream ois = new ObjectInputStream(fis);

				MainFrame tempmf = new MainFrame((MainFrame)ois.readObject());
				mf = tempmf;
				
				// TODO Load grafikus bug de amúgy működik
				
				ois.close();
			} catch (Exception e1) { System.out.println(e1); }
		}
	}
	
	// Alap Frame
	private static void StartFrame() {
		JFrame jf = new JFrame("random_csapatnev base_frame");
		StartFrame = jf;
		jf.setSize(400, 500);
		jf.setLayout(new GridLayout(4, 1));
		
		JPanel jp1 = new JPanel(new GridLayout());
		jp1.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		JPanel jp2 = new JPanel(new GridLayout());
		jp2.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		JPanel jp3 = new JPanel(new GridLayout(1, 2));
		jp3.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		JPanel jp4 = new JPanel(new GridLayout());
		jp4.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		
		
		JButton jbt1 = new JButton(new AbstractAction("New Game") {
			public void actionPerformed(ActionEvent ae) {
				//jf.setVisible(false);
				NewGameFrame();
			}
		});
		JButton jbt2 = new JButton(new AbstractAction("Load Game") {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser jfc = new JFileChooser();
				
				jfc.setDialogTitle("Betöltendö fájl kiválasztása");
		        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jfc.addChoosableFileFilter(new FileNameExtensionFilter("Vak Virológus Játék Fájl", "vak"));
				jfc.setAcceptAllFileFilterUsed(false);
				int result = jfc.showOpenDialog(StartFrame);
				
				if(result == JFileChooser.APPROVE_OPTION) {
					try {
						FileInputStream file_is = new FileInputStream(jfc.getSelectedFile());
						load(new String[] {"", jfc.getSelectedFile().getPath()});
						StartFrame.setVisible(false);
					} catch (Exception e1) {}
				}
			}
		});
		JTextField jtf3 = new JTextField(10);
		jtf3.setFont(new Font("Arial", Font.BOLD, 15));
		JButton jbt3 = new JButton(new AbstractAction("Save") {
			public void actionPerformed(ActionEvent ae) {
				saveGameFrame(jtf3.getText());
			}
		});
		JButton jbt4 = new JButton(new AbstractAction("Exit") {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});
		
		jbt1.setFont(new Font("Arial", Font.BOLD, 25));
		jbt2.setFont(new Font("Arial", Font.BOLD, 25));
		jbt3.setFont(new Font("Arial", Font.BOLD, 25));
		jbt4.setFont(new Font("Arial", Font.BOLD, 25));
		
		jp1.add(jbt1);
		jp2.add(jbt2);
		jp3.add(jbt3);
		jp3.add(jtf3);
		jp4.add(jbt4);
		
		
		jf.add(jp1);
		jf.add(jp2);
		jf.add(jp3);
		jf.add(jp4);
		
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true); 
	}
	
	public static void endGame(boolean winner) {
		if(winner) {
			JOptionPane.showMessageDialog(null, "Victory!", "random_csapatnev", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "Game over!", "random_csapatnev", JOptionPane.INFORMATION_MESSAGE);
		}
		System.exit(0);
	}
	
	private static void NewGameFrame() {
		JFrame jf = new JFrame("random_csapatnev new_game_frame");
		JTextField jtf2 = new JTextField(40);
		jf.setSize(750, 500);
		jf.setLayout(new GridLayout(5, 1));
		
		JPanel jp1 = new JPanel(new FlowLayout());
		jp1.setBorder(BorderFactory.createEmptyBorder(15, 10, 0, 10));
		JPanel jp2 = new JPanel(new FlowLayout());
		jp2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		JPanel jp3 = new JPanel(new FlowLayout());
		jp3.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		JPanel jp4 = new JPanel(new FlowLayout());
		jp4.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		JPanel jp5 = new JPanel(new GridLayout());
		jp5.setBorder(BorderFactory.createEmptyBorder(0, 150, 10, 150));
		
		JLabel jl1 = new JLabel("Ellenfelek száma: ");
		jl1.setFont(new Font("Arial", Font.BOLD, 15));
		JTextField jtf1 = new JTextField(5);
		jtf1.setFont(new Font("Arial", Font.BOLD, 15));
		jtf1.setText("2");
		jp1.add(jl1);
		jp1.add(jtf1);
		
		JLabel jl2 = new JLabel("Random generált játéktér?");
		jl2.setFont(new Font("Arial", Font.BOLD, 15));
		JCheckBox jc1 = new JCheckBox();
        jc1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	jtf2.setEnabled(!jc1.isSelected());
            }
        });
        jc1.setSelected(true);
		jp2.add(jl2);
		jp2.add(jc1);
		
		JLabel jl3 = new JLabel("(vagy)");
		jl3.setFont(new Font("Arial", Font.BOLD, 15));
		jp3.add(jl3);
		
		JLabel jl4 = new JLabel("Játéktér String: ");
		jl4.setFont(new Font("Arial", Font.BOLD, 15));
		jtf2.setEnabled(false);
		jtf2.setFont(new Font("Arial", Font.BOLD, 13));
		jp4.add(jl4);
		jp4.add(jtf2);
		
		JButton jbt = new JButton(new AbstractAction("Játék indítása") {
			public void actionPerformed(ActionEvent ae) {
				mf = new MainFrame(jc1.isSelected(), jc1.isSelected() ? "" : jtf2.getText(), Integer.parseInt(jtf1.getText()));
				jf.setVisible(false);
				jf.dispose();
			}
		});
		jp5.add(jbt);
		
		jf.add(jp1);
		jf.add(jp2);
		jf.add(jp3);
		jf.add(jp4);
		jf.add(jp5);
		
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true); 
	}
	
	public static void saveGameFrame(String fname) {
			try {
				if(!fname.endsWith(".vak")) { fname += ".vak"; }
				FileOutputStream file_os = new FileOutputStream(fname);
				ObjectOutputStream oos = new ObjectOutputStream(file_os);
				oos.writeObject(mf);
				oos.close();
			} catch (Exception ex) { System.out.println(ex); }
	}
}
