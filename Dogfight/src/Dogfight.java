import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dogfight extends JPanel{

	private static Airplane plane1, plane2;
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public static Listener listener = new Listener();
	public static Dogfight panel = new Dogfight();
	
	
	public static void main(String[] args){
		setup();
	}
	
	
	//Creates the window
	public static void setup(){
		JFrame frame = new JFrame("Dogfight!");
		frame.setBounds(200, 100, 1000, 700);
		frame.getContentPane().add(panel);
		panel.setBackground(Color.WHITE);
		
		
		frame.setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public void paint(Graphics g){
		plane1.paint(g);
		plane2.paint(g);
	}
}
