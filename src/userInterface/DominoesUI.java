package userInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.*;

import core.Game;


public class DominoesUI extends JFrame implements ActionListener {

	public static void main(String[] args) {
		DominoesUI frame = new DominoesUI();

	}//main

	private int userChoicePosition = -1;
	private int userChoiceDominoIndex = -1;
	private boolean successfulMove = false;
	
	private Game game;
	private ArrayList<JButton> playerHand;
	private ArrayList<JLabel> computerHand;
	
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
		playerHand = new ArrayList<JButton>();
		
		for (int i = 0; i < 7; i++) {
			ImageIcon test = new ImageIcon("src\\images\\" + game.player.getDominoFromHand(i).toValue() + ".png");
			Image scaleImage = test.getImage().getScaledInstance(80, 120, Image.SCALE_DEFAULT);
			ImageIcon test2 = new ImageIcon(scaleImage);
			JButton playerHandButton = new JButton(test2);
			playerHand.add(playerHandButton);
			playerHandButton.setBorderPainted(false);
			playerHandButton.setBorder(null);
			playerHandButton.setMargin(new Insets(0, 0, 0, 0));
			playerHandPanel.add(playerHand.get(i));
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
		//JButton[]computerHandButtons = new JButton[7];
		computerHand = new ArrayList<JLabel>();
		
		for (int i = 0; i < 7; i++) {
			ImageIcon test = new ImageIcon("src\\images\\xx.png");
			Image scaleImage = test.getImage().getScaledInstance(80, 120, Image.SCALE_DEFAULT);
			ImageIcon icon = new ImageIcon(scaleImage);
			JLabel computerHandLabel = new JLabel(icon);
			computerHand.add(computerHandLabel);
			//computerHandButtons[i].setBorderPainted(false);
			//computerHandButtons[i].setBorder(null);
			//computerHandButtons[i].setMargin(new Insets(0, 0, 0, 0));
			computerHandPanel.add(computerHand.get(i));
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
