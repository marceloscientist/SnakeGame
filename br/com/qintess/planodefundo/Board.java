package br.com.qintess.planodefundo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Board {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		JFrame quadro = new JFrame();
		
		JPanel jp = new JPanel();
		//JPanel jp = new JPanel(new BorderLayout());
		
		jp.setLayout(new GridBagLayout());
		
		JLabel jl = new JLabel("Username: ");
		JTextField un= new JTextField(20);
		jp.add(jl);
		jp.add(un);
		jp.setBackground(Color.black);
		quadro.add(jp);
		quadro.setSize(400, 300);
		quadro.show();
		
	}
}