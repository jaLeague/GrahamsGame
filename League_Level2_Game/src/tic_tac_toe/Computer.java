package tic_tac_toe;

import javax.swing.JOptionPane;

public class Computer {
	static final int PLAYER = 1;
	static final int COMPUTER = 2;
	
	/*
	 * mode 1 = easy;
	 * mode 2 = intermediate;
	 * mode 3 - semi-impossible;
	 */
	
	static final int EASY = 1;
	static final int INTERMEDIATE = 2;
	static final int IMPOSSIBLE = 3;
	
	int mode = 0;
	
	boolean restart = false;
	
	int[] plays = new int[9];
	
	void update(int index, int type) {
		if (mode == EASY) {
			plays[index] = type;
			
			if (checkIfWon(type)) {
				restart();
			}
		}
	}
	
	boolean isFull() {
		for (int i = 0; i < plays.length; i++) {
			if (plays[i] == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	boolean placeTaken(int place) {
		if (plays[place] == PLAYER || plays[place] == COMPUTER) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean checkIfWon(int player) {
		boolean tableFilled = true;
		boolean needsRestart = false;
		
		if (plays[0] == player && plays[1] == player && plays[2] == player || plays[3] == player && plays[4] == player && plays[5] == player || plays[6] == player && plays[7] == player && plays[8] == player) {
			// Direct from left to right
			if (player == PLAYER) {
				JOptionPane.showMessageDialog(null, "you won gud job");
			} else if (player == COMPUTER) {
				JOptionPane.showMessageDialog(null, "TRASHHHHSHHSHSHHH KID YOU COULDN'T EVEN BEAT  A COMPUTER THAT RANDOMLY GUESSES TRASSHHSHSHSHHHH");
			}
			needsRestart = true;
		} else if (plays[0] == player && plays[3] == player && plays[6] == player || plays[1] == player && plays[4] == player && plays[7] == player || plays[2] == player && plays[5] == player && plays[8] == player) {
			// Top to bottom
			if (player == PLAYER) {
				JOptionPane.showMessageDialog(null, "you won gud job");
			} else if (player == COMPUTER) {
				JOptionPane.showMessageDialog(null, "TRASHHHHSHHSHSHHH KID YOU COULDN'T EVEN BEAT  A COMPUTER THAT RANDOMLY GUESSES TRASSHHSHSHSHHHH");
			}
			needsRestart = true;
		} else if (plays[0] == player && plays[4] == player && plays[8] == player || plays[2] == player && plays[4] == player && plays[6] == player) {
			// Top left to bottom right
			if (player == PLAYER) {
				JOptionPane.showMessageDialog(null, "you won gud job");
			} else if (player == COMPUTER) {
				JOptionPane.showMessageDialog(null, "TRASHHHHSHHSHSHHH KID YOU COULDN'T EVEN BEAT  A COMPUTER THAT RANDOMLY GUESSES TRASSHHSHSHSHHHH");
			}
			needsRestart = true;
		}
		
		// Check if all the spots are filled
		for (int i = 0; i < plays.length; i++) {
			if (!placeTaken(i)) {
				tableFilled=false;
				break;
			}
		}
		
		// Following to the top line
		if (tableFilled && !needsRestart) {
			JOptionPane.showMessageDialog(null, "tie better than loosing i guess.");	
			needsRestart = true;
		}
		
		return needsRestart;
	}
	
	void restart() {
//		Startup.frame.dispose();
//		
//		new Startup();
		plays = new int[9];
		restart=true;
	}
	
	int[] getTable() {
		return plays;
	}
	
	// Returns the current mode //
	void setMode(int mode) {
		this.mode = mode;
	}
	// Gets the current mode //
	int getMode() {
		return this.mode;
	}
}
