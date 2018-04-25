package userInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.*;

import core.Domino;
import core.Game;


public class DominoesUI extends JFrame {

	public static void main(String[] args) {
		frame = new DominoesUI();

	}//main

	private int userChoicePosition = -1;
	private int userChoiceDominoIndex = -1;
	private boolean successfulMove = false;
	private int computerDominoRemove = -1;
	
	private static DominoesUI frame;
	
	private Game game;
	private ArrayList<JButton> playerHand;
	private ArrayList<JLabel> computerHand;
	private ArrayList<JLabel> playedLabel2;
	//Menu Variables
	private JMenuBar menuBar;
	private JMenu gameMenu;
	private JMenuItem newGame;
	private JMenuItem setName;
	private JMenuItem exit;
	//Board Variables
	private JPanel boardPanel;
	private JButton rightButton;
	private JButton leftButton;
	private JLabel playedDominoesLabel;
	//Player Hand Variables
	private JPanel playerHandPanel;
	private JPanel playerScorePanel;
	private String playerScoreStr;
	private JLabel playerScoreLabel;
	private JButton endGameButton;
	//Computer Hand Variables
	private JPanel computerHandPanel;
	private JPanel computerScorePanel;
	private String computerScoreStr;
	private JLabel computerScoreLabel;
	
	//action listeners
	private class ExitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
            if (JOptionPane.showConfirmDialog(getThisParent(), "Are you sure you want to exit?", "Exit?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
	}
	
	private class NewGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			dispose();
            frame = new DominoesUI();
		}
	}
	
	private class SetNameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			String name = JOptionPane.showInputDialog("What is your name?");
			game.player.setName(name);
			playerHandPanel.setBorder(BorderFactory.createTitledBorder(game.player.getName() + "'s Hand"));
		}
	}
	
	private class PlayerHandListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			for (int i = 0; i < game.player.dominoCount(); i++) {
				if(ae.getSource() == playerHand.get(i)) {
					userChoiceDominoIndex = i;
				}
			}
			playerHand.get(userChoiceDominoIndex).setContentAreaFilled(true);
			playerHand.get(userChoiceDominoIndex).removeActionListener(this);
			playerHand.get(userChoiceDominoIndex).addActionListener(new PlayerHandListener2());
			for (int j = 0; j < game.player.dominoCount(); j++) {
				if (j != userChoiceDominoIndex) {
					playerHand.get(j).setEnabled(false);
				}
			}
			rightButton.setVisible(true);
			leftButton.setVisible(true);
		}
	}
	
	private class PlayerHandListener2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			userChoiceDominoIndex = -1;
			for (int i = 0; i < game.player.dominoCount(); i++) {
				if(ae.getSource() == playerHand.get(i)) {
					userChoiceDominoIndex = i;
				}
			}
			playerHand.get(userChoiceDominoIndex).setContentAreaFilled(false);
			playerHand.get(userChoiceDominoIndex).removeActionListener(this);
			playerHand.get(userChoiceDominoIndex).addActionListener(new PlayerHandListener());
			for (int j = 0; j < game.player.dominoCount(); j++) {
				if (j != userChoiceDominoIndex) {
					playerHand.get(j).setEnabled(true);
				}
			}
			rightButton.setVisible(false);
			leftButton.setVisible(false);
		}
	}
	
	private class RightButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			successfulMove = game.doEndMove(game.player.getDominoFromHand(userChoiceDominoIndex));
			
			if(successfulMove) {
				game.player.removeDominoFromHand(userChoiceDominoIndex);
				
				Domino firstDomino = game.playedDominoes.get(0);
				Domino lastDomino = game.playedDominoes.get(game.playedDominoes.size()-1);
				
				game.player.addToScore(firstDomino.getLeftVal() + lastDomino.getRightVal());
				
				playerHand.get(userChoiceDominoIndex).setVisible(false);
				playerHand.remove(userChoiceDominoIndex);
				
				if(game.playComputer()) {
					boardPanel.removeAll();
					
					for(int i = 0; i < game.playedDominoes.size(); i++) {
						if(game.playedDominoes.get(i).isDouble()) {
							ImageIcon test = new ImageIcon("src\\images\\sideways\\" + game.playedDominoes.get(i).toValue() + ".png");
							Image scaleImage = test.getImage().getScaledInstance(60, 90, Image.SCALE_DEFAULT);
							ImageIcon icon = new ImageIcon(scaleImage);
							JLabel played = new JLabel(icon);
							playedLabel2.add(i, played);
							boardPanel.add(playedLabel2.get(i));
						}else {
							ImageIcon test = new ImageIcon("src\\images\\sideways\\" + game.playedDominoes.get(i).toValue() + ".png");
							Image scaleImage = test.getImage().getScaledInstance(90, 60, Image.SCALE_DEFAULT);
							ImageIcon icon = new ImageIcon(scaleImage);
							JLabel played = new JLabel(icon);
							playedLabel2.add(i, played);
							boardPanel.add(playedLabel2.get(i));
						}
					}
					playerScoreLabel.setText("" + game.player.getScore());
					computerScoreLabel.setText("" + game.computer.getScore());
					computerDominoRemove++;
				}else {
					playerScoreLabel.setText("" + game.player.getScore());
					computerScoreLabel.setText("" + game.computer.getScore());
					
					if (game.player.getScore() > game.computer.getScore()) {
						if (JOptionPane.showConfirmDialog(getThisParent(), "You Won!  Do you want to play again?", "Game Over!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							dispose();
				            frame = new DominoesUI();
						} else { 
							System.exit(0);
						}
					}else if (game.player.getScore() < game.computer.getScore()){
						if (JOptionPane.showConfirmDialog(getThisParent(), "You Lost...  Do you want to play again?", "Game Over!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							dispose();
				            frame = new DominoesUI();
						} else {
							System.exit(0);
						}
					}else {
						if (JOptionPane.showConfirmDialog(getThisParent(), "You Tied with the Computer.  Do you want to play again?", "Game Over!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							dispose();
				            frame = new DominoesUI();
						} else {
							System.exit(0);
						}
					}
				}
				
				for (int j = 0; j < game.player.dominoCount(); j++) {
						playerHand.get(j).setEnabled(true);
				}
				rightButton.setVisible(false);
				leftButton.setVisible(false);
				computerHand.get(computerDominoRemove).setVisible(false);
			}
		}
	}
	
	private class LeftButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			successfulMove = game.doBegMove(game.player.getDominoFromHand(userChoiceDominoIndex));
			
			if(successfulMove) {
				game.player.removeDominoFromHand(userChoiceDominoIndex);
				
				Domino firstDomino = game.playedDominoes.get(0);
				Domino lastDomino = game.playedDominoes.get(game.playedDominoes.size()-1);
				
				game.player.addToScore(firstDomino.getLeftVal() + lastDomino.getRightVal());
				
				playerHand.get(userChoiceDominoIndex).setVisible(false);
				playerHand.remove(userChoiceDominoIndex);
				
				if(game.playComputer()) {
					//playedDominoesLabel.setText(game.ToStringPlayedTiles());
					boardPanel.removeAll();
					
					for(int i = 0; i < game.playedDominoes.size(); i++) {
						if(game.playedDominoes.get(i).isDouble()) {
							ImageIcon test = new ImageIcon("src\\images\\sideways\\" + game.playedDominoes.get(i).toValue() + ".png");
							Image scaleImage = test.getImage().getScaledInstance(60, 90, Image.SCALE_DEFAULT);
							ImageIcon icon = new ImageIcon(scaleImage);
							JLabel played = new JLabel(icon);
							playedLabel2.add(i, played);
							boardPanel.add(playedLabel2.get(i));
						}else {
							ImageIcon test = new ImageIcon("src\\images\\sideways\\" + game.playedDominoes.get(i).toValue() + ".png");
							Image scaleImage = test.getImage().getScaledInstance(90, 60, Image.SCALE_DEFAULT);
							ImageIcon icon = new ImageIcon(scaleImage);
							JLabel played = new JLabel(icon);
							playedLabel2.add(i, played);
							boardPanel.add(playedLabel2.get(i));
						}
					}
					
					playerScoreLabel.setText("" + game.player.getScore());
					computerScoreLabel.setText("" + game.computer.getScore());
					computerDominoRemove++;
				}else {
					playerScoreLabel.setText("" + game.player.getScore());
					computerScoreLabel.setText("" + game.computer.getScore());
					
					if (game.player.getScore() > game.computer.getScore()) {
						if (JOptionPane.showConfirmDialog(getThisParent(), "You Won!  Do you want to play again?", "Game Over!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							dispose();
				            frame = new DominoesUI();
						} else { 
							System.exit(0);
						}
					}else if (game.player.getScore() < game.computer.getScore()){
						if (JOptionPane.showConfirmDialog(getThisParent(), "You Lost...  Do you want to play again?", "Game Over!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							dispose();
				            frame = new DominoesUI();
						} else {
							System.exit(0);
						}
					}else {
						if (JOptionPane.showConfirmDialog(getThisParent(), "You Tied with the Computer.  Do you want to play again?", "Game Over!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							dispose();
				            frame = new DominoesUI();
						} else {
							System.exit(0);
						}
					}
				}
				
				for (int j = 0; j < game.player.dominoCount(); j++) {
						playerHand.get(j).setEnabled(true);
				}
				rightButton.setVisible(false);
				leftButton.setVisible(false);
				computerHand.get(computerDominoRemove).setVisible(false);
			}
		}
	}
	
	private class EndGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (game.player.getScore() > game.computer.getScore()) {
				if (JOptionPane.showConfirmDialog(getThisParent(), "You Won!  Do you want to play again?", "Game Over!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					dispose();
		            frame = new DominoesUI();
				} else { 
					System.exit(0);
				}
			}else if (game.player.getScore() < game.computer.getScore()){
				if (JOptionPane.showConfirmDialog(getThisParent(), "You Lost...  Do you want to play again?", "Game Over!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					dispose();
		            frame = new DominoesUI();
				} else {
					System.exit(0);
				}
			}else {
				if (JOptionPane.showConfirmDialog(getThisParent(), "You Tied with the Computer.  Do you want to play again?", "Game Over!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					dispose();
		            frame = new DominoesUI();
				} else {
					System.exit(0);
				}
			}			
		}
	}
	
	public DominoesUI() {
		initGame();
		initComponents();
	}
	
	public DominoesUI getThisParent() {
		return this;
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
		
		//Menu
		menuBar = new JMenuBar();
        gameMenu = new JMenu("Game Options");
        menuBar.add(gameMenu);
        
        newGame = new JMenuItem("New Game");
        newGame.addActionListener(new NewGameListener());
        gameMenu.add(newGame);
        
        setName = new JMenuItem("Change Player Name");
        setName.addActionListener(new SetNameListener());
        gameMenu.add(setName);
        
        exit = new JMenuItem("Exit");
        exit.addActionListener(new ExitListener());
        gameMenu.add(exit);
        
		/*Board*/
		//Board Panel
		boardPanel = new JPanel();
		boardPanel.setMinimumSize(new Dimension(750,389));
		boardPanel.setMaximumSize(new Dimension(750,389));
		boardPanel.setPreferredSize(new Dimension(750,389));
		boardPanel.setLayout(new GridBagLayout());
		
		//Play Direction Button
		rightButton = new JButton("Play Right!");
		rightButton.addActionListener(new RightButtonListener());
		rightButton.setVisible(false);
		leftButton = new JButton("Play Left!");
		leftButton.addActionListener(new LeftButtonListener());
		leftButton.setVisible(false);
		
		//Played Label
		playedLabel2 = new ArrayList<JLabel>();
		//System.out.println(game.ToStringPlayedTiles());
		boardPanel.removeAll();
		for(int i = 0; i < game.playedDominoes.size(); i++) {
			if(game.playedDominoes.get(i).isDouble()) {
				ImageIcon test = new ImageIcon("src\\images\\sideways\\" + game.playedDominoes.get(i).toValue() + ".png");
				Image scaleImage = test.getImage().getScaledInstance(60, 90, Image.SCALE_DEFAULT);
				ImageIcon icon = new ImageIcon(scaleImage);
				JLabel played = new JLabel(icon);
				playedLabel2.add(i, played);
				boardPanel.add(playedLabel2.get(i));
			}else {
				ImageIcon test = new ImageIcon("src\\images\\sideways\\" + game.playedDominoes.get(i).toValue() + ".png");
				Image scaleImage = test.getImage().getScaledInstance(90, 60, Image.SCALE_DEFAULT);
				ImageIcon icon = new ImageIcon(scaleImage);
				JLabel played = new JLabel(icon);
				playedLabel2.add(i, played);
				boardPanel.add(playedLabel2.get(i));
			}
		}
		
		//player Hand panel
		playerHandPanel = new JPanel(new GridLayout(1,7));
		playerHandPanel.setMinimumSize(new Dimension(750,175));
		playerHandPanel.setMaximumSize(new Dimension(750,175));
		playerHandPanel.setPreferredSize(new Dimension(750,175));
		playerHandPanel.setBorder(BorderFactory.createTitledBorder(game.player.getName() + "'s Hand"));
		
		//player Score panel
		playerScorePanel = new JPanel();
		playerScorePanel.setMinimumSize(new Dimension(400,200));
		playerScorePanel.setMaximumSize(new Dimension(400,200));
		playerScorePanel.setPreferredSize(new Dimension(400,200));
		playerScorePanel.setBorder(BorderFactory.createTitledBorder("Score"));
		
		//Player Score label
		playerScoreStr = "" + game.player.getScore();
		playerScoreLabel = new JLabel(playerScoreStr);
		playerScoreLabel.setFont(playerScoreLabel.getFont().deriveFont(50.0f));
		playerScorePanel.add(playerScoreLabel, BorderLayout.CENTER);
		
		//End Game Button
		endGameButton = new JButton("End Game");
		endGameButton.addActionListener(new EndGameListener());
		playerScorePanel.add(endGameButton, BorderLayout.SOUTH);
		
		
		//players Hand Buttons
		playerHand = new ArrayList<JButton>();
		
		for (int i = 0; i < 7; i++) {
			ImageIcon test = new ImageIcon("src\\images\\" + game.player.getDominoFromHand(i).toValue() + ".png");
			Image scaleImage = test.getImage().getScaledInstance(80, 120, Image.SCALE_DEFAULT);
			ImageIcon test2 = new ImageIcon(scaleImage);
			JButton playerHandButton = new JButton(test2);
			playerHand.add(playerHandButton);
			playerHand.get(i).setContentAreaFilled(false);
			playerHand.get(i).setBorder(null);
			playerHandPanel.add(playerHand.get(i));
			playerHand.get(i).addActionListener(new PlayerHandListener());
		}
		
		//Computer Hand Panel
		computerHandPanel = new JPanel(new GridLayout(1,7));
		computerHandPanel.setMinimumSize(new Dimension(750,175));
		computerHandPanel.setMaximumSize(new Dimension(750,175));
		computerHandPanel.setPreferredSize(new Dimension(750,175));
		computerHandPanel.setBorder(BorderFactory.createTitledBorder(game.computer.getName() + "'s Hand"));
		
		//computer Score panel
		computerScorePanel = new JPanel();
		computerScorePanel.setMinimumSize(new Dimension(400,200));
		computerScorePanel.setMaximumSize(new Dimension(400,200));
		computerScorePanel.setPreferredSize(new Dimension(400,200));
		computerScorePanel.setBorder(BorderFactory.createTitledBorder("Score"));
		
		//Computer Score Label
		computerScoreStr = "" + game.computer.getScore();
		computerScoreLabel = new JLabel(computerScoreStr);
		computerScoreLabel.setFont(computerScoreLabel.getFont().deriveFont(50.0f));
		computerScorePanel.add(computerScoreLabel, BorderLayout.CENTER);

		
		//computer Hand Labels
		computerHand = new ArrayList<JLabel>();
		
		for (int i = 0; i < 7; i++) {
			ImageIcon test = new ImageIcon("src\\images\\xx.png");
			Image scaleImage = test.getImage().getScaledInstance(80, 120, Image.SCALE_DEFAULT);
			ImageIcon icon = new ImageIcon(scaleImage);
			JLabel computerHandLabel = new JLabel(icon);
			computerHand.add(computerHandLabel);
			computerHandPanel.add(computerHand.get(i));
		}
	
		
		//Add to UI
		playerHandPanel.add(playerScorePanel, BorderLayout.WEST);
		computerHandPanel.add(computerScorePanel, BorderLayout.WEST);
		
		this.setJMenuBar(menuBar);
		
		this.add(computerHandPanel, BorderLayout.NORTH);
		this.add(boardPanel, BorderLayout.CENTER);
		this.add(playerHandPanel, BorderLayout.SOUTH);
		
		this.add(rightButton, BorderLayout.EAST);
		this.add(leftButton, BorderLayout.WEST);
				
		this.setVisible(true);
	}
	
}
