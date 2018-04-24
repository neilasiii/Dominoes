package userInterface;

import java.awt.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import core.Game;


public class DominoesUI extends JFrame {

	public static void main(String[] args) {
		DominoesUI frame = new DominoesUI();

	}//main

	private int userChoicePosition = -1;
	private int userChoiceDominoIndex = -1;
	private boolean successfulMove = false;
	
	private Game game;
	private ArrayList playerHand;
	
	public DominoesUI() {
		initGame();
		initComponents();
	}
	
	private void initGame() {
		game = new Game();
		game.initGame();
		game.doFirstMove();
	}
	
	private void initComponents() {
		this.setTitle("Group 5: Block Dominoes");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1000,800));
		this.setMinimumSize(new Dimension(1000,800));
				
		//Game Container
		JPanel gamePanel = new JPanel();
		gamePanel.setMinimumSize(new Dimension(750,800));
		gamePanel.setMaximumSize(new Dimension(750,800));
		gamePanel.setPreferredSize(new Dimension(750,800));	
		
		//Board Panel
		JPanel boardPanel = new JPanel();
		boardPanel.setMinimumSize(new Dimension(750,389));
		boardPanel.setMaximumSize(new Dimension(750,389));
		boardPanel.setPreferredSize(new Dimension(750,389));	
		
		//player Hand panel
		JPanel playerHandPanel = new JPanel(new GridLayout(1,7));
		playerHandPanel.setMinimumSize(new Dimension(750,175));
		playerHandPanel.setMaximumSize(new Dimension(750,175));
		playerHandPanel.setPreferredSize(new Dimension(750,175));
		playerHandPanel.setBorder(BorderFactory.createTitledBorder(game.player.getName() + "'s Hand"));
		
		//player Score panel
		JPanel playerScorePanel = new JPanel();
		playerScorePanel.setMinimumSize(new Dimension(400,200));
		playerScorePanel.setMaximumSize(new Dimension(400,200));
		playerScorePanel.setPreferredSize(new Dimension(400,200));
		playerScorePanel.setBorder(BorderFactory.createTitledBorder("Score"));
		
		//players Hand Buttons
		JButton[]playerHandButtons = new JButton[7];
		
		for (int i = 0; i < 7; i++) {
			playerHandButtons[i] = new JButton(game.player.getDominoFromHand(i).toString());
			playerHandPanel.add(playerHandButtons[i]);
		}
		
		//Computer Hand Panel
		JPanel computerHandPanel = new JPanel(new GridLayout(1,7));
		computerHandPanel.setMinimumSize(new Dimension(750,175));
		computerHandPanel.setMaximumSize(new Dimension(750,175));
		computerHandPanel.setPreferredSize(new Dimension(750,175));
		computerHandPanel.setBorder(BorderFactory.createTitledBorder(game.computer.getName() + "'s Hand"));
		
		//player Score panel
		JPanel computerScorePanel = new JPanel();
		computerScorePanel.setMinimumSize(new Dimension(400,200));
		computerScorePanel.setMaximumSize(new Dimension(400,200));
		computerScorePanel.setPreferredSize(new Dimension(400,200));
		computerScorePanel.setBorder(BorderFactory.createTitledBorder("Score"));
		
		//players Hand Buttons
		JButton[]computerHandButtons = new JButton[7];
				
		for (int i = 0; i < 7; i++) {
			ImageIcon test = new ImageIcon("images/00.png");
			computerHandButtons[i] = new JButton(game.computer.getDominoFromHand(i).toString());
			computerHandButtons[i].setIcon(test);
			computerHandPanel.add(computerHandButtons[i]);
		}
		
		//Menu Panel
		JPanel menuPanel = new JPanel();
		menuPanel.setMinimumSize(new Dimension(230,800));
		menuPanel.setMaximumSize(new Dimension(230,800));
		menuPanel.setPreferredSize(new Dimension(230,800));
		menuPanel.setBorder(BorderFactory.createTitledBorder("User Menu"));
		
		
		//Add to UI
		playerHandPanel.add(playerScorePanel, BorderLayout.WEST);
		computerHandPanel.add(computerScorePanel, BorderLayout.WEST);
		
		gamePanel.add(computerHandPanel, BorderLayout.NORTH);
		gamePanel.add(boardPanel, BorderLayout.CENTER);
		gamePanel.add(playerHandPanel, BorderLayout.SOUTH);

		this.add(gamePanel, BorderLayout.EAST);
		this.add(menuPanel, BorderLayout.WEST);
				
		this.setVisible(true);
	}
}
