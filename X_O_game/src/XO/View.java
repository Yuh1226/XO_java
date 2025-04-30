package XO;

import java.util.Scanner;

public class View {
	private Scanner scanner;
	
	public View() {
		scanner = new Scanner(System.in);
	}
	
	 public void displayBoard(Node[][] board) {
	        System.out.println("X/O Board:");
	        System.out.println(" ─── ─── ─── ─── ─── ─── ─── ─── ───");
	        for (int i = 0; i < 9; i++) {
	        	System.out.print("|");
	            for (int j = 0; j < 9; j++) {
	                System.out.print(" " + board[i][j]);
	                if (j < 8) System.out.print(" |");
	                else System.out.print(" |");
	            }
	            System.out.println();
	            System.out.print(" ");
	            if (i < 8) System.out.println("─── ─── ─── ─── ─── ─── ─── ─── ─── ");
	        }
	        System.out.println("─── ─── ─── ─── ─── ─── ─── ─── ───");
	    }
}
