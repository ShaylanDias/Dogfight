import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Menu {

	public static boolean counting = false;
	public static String countdown = "3";
	public static int xMinus = 30;
	private boolean onMenu;
	private Image spitfire = new ImageIcon("spitfire.png").getImage();
	private Image bf109 = new ImageIcon("bf109.png").getImage();
	private Image mig = new ImageIcon("mig.png").getImage();
	private Image thunderbolt = new ImageIcon("thunderbolt.png").getImage();
	private Image zero = new ImageIcon("zero.png").getImage();
	private int width = 200, height = 50;

	private JPanel start = new JPanel();


	public Menu(){
		onMenu = true;
		spitfire = spitfire.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		bf109 = bf109.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		mig = mig.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		thunderbolt = thunderbolt.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		zero = zero.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	}


	public boolean getOnMenu(){
		return onMenu;
	}

	public void setOnMenu(boolean x){
		onMenu = x;
	}


	public JPanel getStart(){
		return start;
	}

	public void start(){
		Dogfight.setBackground(new ImageIcon("dogfight.jpg").getImage().getScaledInstance((int)Dogfight.bounds.getWidth(), (int)Dogfight.bounds.getHeight(), 100));

	}

	public void paintMenu(Graphics g, ImageObserver i){

		if(Dogfight.panel.getMenu().getOnMenu() == true){
			g.setColor(Color.RED);
			g.setFont(new Font("Serif", Font.BOLD, 60));
			g.drawString("DOGFIGHT", 350, 50);
			g.setFont(new Font("Serif", Font.BOLD, 30));
			g.drawString("Select Your Plane", 400, 100);

			g.setColor(Color.WHITE);
			g.fillRect(100, 300, width, height);
			g.fillRect(100, 550, width, height);
			g.fillRect(600, 300, width, height);
			g.fillRect(600, 550, width, height);
			g.fillRect(350, 400, width, height);
			
			g.drawImage(bf109, 100, 300, i);
			g.drawImage(spitfire, 100, 550, i);
			g.drawImage(mig, 600, 300, i);
			g.drawImage(zero, 600, 550, i);
			g.drawImage(thunderbolt, 350, 400, i);
			
		}


	}


	//Performs the countdown
	public void countdown(){
		counting = true;
		Dogfight.panel.repaint();
		sleep(1200);
		for(int i = 0; i < Integer.parseInt(countdown); countdown = (Integer.parseInt(countdown) - 1) + "" ){
			Dogfight.panel.repaint();
			sleep(1200);
		}
		countdown = "GO";
		xMinus = 50;
		Dogfight.panel.repaint();
		sleep(1200);
		xMinus = 30;
		counting = false;
		countdown = "3";
		Dogfight.panel.repaint();
	}

	//To save on writing try/catch blocks
	private void sleep(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
