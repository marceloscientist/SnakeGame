import javax.swing.JFrame;

public class GameFrame extends JFrame {
	

//	GamePanel Panel = GamePanel()

	GameFrame(){
		
				
		this.add(new GamePanel()); // cha???ma o construtor GamePanel
		this.setTitle("Snake"); // Titulo da  // deixando o painel visivelJanela
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // para que o // para que a tela apareça no meio da tela do computador botão X funcione
		this.setResizable(false); // nao permite o redimensionamento da Tela
		this.pack();  // ????
		this.setVisible(true); // deixando o painel visivel
		this.setLocationRelativeTo(null); // para que a tela apareça no meio da tela do computador
		
		
	}

}
