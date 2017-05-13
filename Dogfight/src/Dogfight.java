import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dogfight extends JPanel implements KeyListener{

	private Airplane plane1, plane2;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public static Dogfight panel;

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;

	public static final Rectangle bounds = new Rectangle(0, 0, WIDTH, HEIGHT);
	public static final Rectangle ground = new Rectangle(0, HEIGHT - 100, WIDTH, 100); 
	private static Image background = new ImageIcon("background.png").getImage();

	private boolean w = false, a = false, s = false, d = false, space = false;
	private boolean up = false, down = false, left = false, right = false, slash = false;

	public static Menu menu;
	public static boolean started = false;


	public Dogfight(){
		//Instantiating airplanes, later will add a selection to this
		plane1 = new Airplane(new ImageIcon("spitfire.png").getImage(), true);
		plane2 = new Airplane(new ImageIcon("zero.png").getImage(), false);
	}

	public static void main(String[] args){
		started = false;
		background = background.getScaledInstance(WIDTH, HEIGHT, 100);
		JFrame frame = new JFrame("Dogfight!");
		frame.setBounds(200, 100, 1000, 700);
		panel = new Dogfight();
		frame.setFocusable(true);
		frame.getContentPane().add(panel);
		panel.setBackground(Color.WHITE);
		frame.addKeyListener(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

		//To give time for stuff to load
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		menu = new Menu();
		menu.countdown();

		started = true;
		while(started == true){
			frame.setFocusable(true);
			frame.requestFocus();
			//Airplane movement
			if(panel.w == true){
				panel.plane1.rotate();
			}
			if(panel.a == true){
				panel.plane1.changeSpeed(-1);
			}
			if(panel.s == true){
				panel.plane1.rotateDown();
			}
			if(panel.d == true){
				panel.plane1.changeSpeed(1);
			}
			if(panel.up == true){
				panel.plane2.rotate();
			}
			if(panel.down == true){
				panel.plane2.rotateDown();
			}
			if(panel.left == true){
				panel.plane2.changeSpeed(-1);
			}
			if(panel.right == true){
				panel.plane2.changeSpeed(1);
			}
			if(panel.space == true){
				panel.plane1.shoot();
				KList.playSound2();
			}
			if(panel.slash == true){
				panel.plane2.shoot();
				KList.playSound();
			}
			
			//Move bullets, happens before planes move so they don't run into their own bullets
			for(int i = 0; i < panel.bullets.size(); i++){
				panel.bullets.get(i).move();
				if(!panel.bullets.get(i).getHitbox().intersects(bounds)){
				}
				if(panel.bullets.get(i).getHitbox().intersects(panel.plane1.getHitbox().getBounds2D())){
					panel.plane1.takeHit(panel.bullets.get(i).getDmg());
					panel.bullets.get(i).setImage(null);
				}
				if(panel.bullets.get(i).getHitbox().intersects(panel.plane2.getHitbox().getBounds2D())){
					panel.plane2.takeHit(panel.bullets.get(i).getDmg());
					panel.bullets.get(i).setImage(null);
				}
			}
			
			if(!panel.plane1.getHitbox().intersects(bounds)){
				panel.plane1.returnToMap(bounds);
			}
			if(!panel.plane2.getHitbox().intersects(bounds)){
				panel.plane2.returnToMap(bounds);
			}
			
			panel.plane1.move();
			panel.plane2.move();
			
			//Delay between loops
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}





	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W){
			if(s != true){
				w = true;
			}
		}
		if(code == KeyEvent.VK_A){
			if(d != true){
				a = true;
			}
		}
		if(code == KeyEvent.VK_S){
			if(w != true){
				s = true;
			}
		}
		if(code == KeyEvent.VK_D){
			if(a != true){
				d = true;
			}
		}
		if(code == KeyEvent.VK_SPACE){
			space = true;
		}
		if(code == KeyEvent.VK_UP){
			if(down != true){
				up = true;
			}
		}
		if(code == KeyEvent.VK_DOWN){
			if(up != true){
				down = true;
			}
		}
		if(code == KeyEvent.VK_LEFT){
			if(right != true){
				left = true;
			}
		}
		if(code == KeyEvent.VK_RIGHT){
			if(left != true){
				right = true;
			}
		}
		if(code == KeyEvent.VK_SLASH){
			slash = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if(code == KeyEvent.VK_W){
			w = false;
		}
		if(code == KeyEvent.VK_A){
			a = false;
		}
		if(code == KeyEvent.VK_S){
			s = false;
		}
		if(code == KeyEvent.VK_D){
			d = false;
		}
		if(code == KeyEvent.VK_SPACE){
			space = false;
		}
		if(code == KeyEvent.VK_UP){
			up = false;
		}
		if(code == KeyEvent.VK_DOWN){
			down = false;
		}
		if(code == KeyEvent.VK_LEFT){
			left = false;
		}
		if(code == KeyEvent.VK_RIGHT){
			right = false;
		}
		if(code == KeyEvent.VK_SLASH){
			slash = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//nada
	}

	
	public static void addBullet(Bullet bullet){
		panel.bullets.add(bullet);
	}
	
	public void removeBullet(Bullet bullet){
		panel.bullets.remove(bullet);
		bullet = new Bullet();
	}

	public static void setBackground(Image image){
		background = image;
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(background, 0, 0, this);

		//plane1
		plane1.draw(g, this);

		//plane2
		plane2.draw(g, this);
		
		
		//Health
		int p1H = plane1.getHealth();
		int p2H = plane2.getHealth();
		if(p1H < 0){
			p1H = 0;
		}
		if(p2H < 0){
			p2H = 0;
		}
		g.setColor(Color.BLACK);
		g.drawRect(10, 5, 100, 10);
		g.drawRect(WIDTH - 110, 5, 100, 10);
		g.setColor(Color.BLUE);
		g.fillRect(10, 5, p1H, 10);
		g.setColor(Color.RED);
		g.fillRect(WIDTH - 110, 5, p2H, 10);

		
		if(started == true){ //If the game has been started
			for(int i = 0; i < bullets.size(); i++){
				bullets.get(i).paint(g);
			}
		}
		else{
			if(Menu.counting == true){
				g.setColor(Color.RED);
				g.setFont(new Font("Serif", Font.BOLD, 50));
				g.drawString(Menu.countdown + "", WIDTH/2 - Menu.xMinus, HEIGHT/2 - 20);
			}
		}
	}
}
