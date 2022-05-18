import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {

	static final int SCREEN_WIDTH = 600; // dimensão da largura da tela 
	static final int SCREEN_HEIGHT = 600;  // dimensao da altura da yela
	static final int UNIT_SIZE = 25; // tamanho da unidade das celuas da tela
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE; //largura* altura/ pela unidade surgira o tamanho da celula
	static final int DELAY = 75; // é o atrsado da velocidade
	static int x[] = new int[GAME_UNITS]; // Define que a unidade celular da cobra
	static int y[] = new int[GAME_UNITS]; // Define a unidade celular da cobra
	int bodyParts = 6; // define que o corpo da cobra vai comecar com 6 unidade
	int applesEaten; // define as maças comidas
	int appleX; //
	int appleY; //
	char direction = 'R'; // define a direção da cobra
	Boolean running = false; //
	Timer timer; // define o tempo
	Random random; // define a opção de usar o comando ramdomico
	
	// abaixo o método construtor
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	// Metodo inicio do jogo
	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
	// onde será feita a construcao dos gráficos 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	// onde será colocados os parametros para o grafico g
	public void draw(Graphics g) {
		if(running) {
			/*
			for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}
			*/
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		
			for(int i = 0; i < bodyParts; i++) {
				if(i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				else {
					g.setColor(new Color(45, 180, 0));
			//		g.setColor(new Color(random.nextInt(255),random.nextInt(255)));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free", Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Pontuação: " + applesEaten,(SCREEN_WIDTH - metrics.stringWidth("Pontuação :" + applesEaten))/2, g.getFont().getSize());
			
		}
		else {
			gameOver(g);
		}
		
	}
	public void newApple() {
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
		
	}
	// criação do movimento
	public void move() {
		for(int i = bodyParts; i > 0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
		
	}
	// verificacao da maça
	public void checkApple() {
		if((x[0] == appleX) && (y[0] == appleY)){
			bodyParts++;
			applesEaten++;
			newApple();
			
		}
		
	}
	
	// verificaçao da colisao
	public void checkCollisions() {
		// checks if heads collides with body
		for(int i = bodyParts; i > 0; i--) {
			if((x[0] == x[i]) && (y[0] == y[i])){
				running = false;
			}
		}
		// check if head touches left border
		if(x[0] < 0) {
			running = false;
		}
		// CHECK IF HEAD TOUCHES right BORDER
		if(x[0] > SCREEN_WIDTH) {
			running = false;
		}
		// check if head touches top border
		if(y[0] < 0) {
			running = false;
		}
		// check if head touches bottom border
		if(y[0] > SCREEN_HEIGHT) {
				running = false;
		}
		if(!running) {
			timer.stop();
		}
	}
	
	// configuraçao da finalização do jogo
	public void gameOver(Graphics g) {
		// Pontuacao
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Pontuação: " + applesEaten,(SCREEN_WIDTH - metrics1.stringWidth("Pontuação :" + applesEaten))/2, g.getFont().getSize());
		
		// Game Over text
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over",(SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { //
		
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}
	public class MyKeyAdapter extends KeyAdapter{  //Adaptador das teclas utilizadas
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
				
			}
		}
	}

}
