package XO;

import java.util.Scanner;

public class View {
	private Scanner scanner;

	public View() {
		scanner = new Scanner(System.in);
	}

	public void displayBoard(Node[][] board, String message) {
		System.out.println("X/O Board:");
		System.out.println(" ─── ─── ─── ─── ─── ─── ─── ─── ───");
		for (int i = 0; i < 9; i++) {
			System.out.print("|");
			for (int j = 0; j < 9; j++) {
				System.out.print(" " + board[i][j]);
				if (j < 8)
					System.out.print(" |");
				else
					System.out.print(" |");
			}
			System.out.println();
			System.out.print(" ");
			if (i < 8)
				System.out.println("─── ─── ─── ─── ─── ─── ─── ─── ─── ");
		}
		System.out.println("─── ─── ─── ─── ─── ─── ─── ─── ───");
		
		if (message != null && !message.isEmpty()) {
	        System.out.println(message);
	    }
	}
	
	public void displayBoard(Node[][] board) {
	    displayBoard(board, null);
	}

	public int getCommand() {
		System.out.println();
		System.out.println("====== GAME X/O MENU ======");
		System.out.println("1. Chơi");
		System.out.println("2. Undo ");
		System.out.println("3. Redo ");
		System.out.print("Nhấn lựa chọn, nhấn bất kỳ khác để thoát: ");
		try {
			int option = Integer.parseInt(scanner.nextLine());
			System.out.println("==========================\n");
			return option;
		} catch (NumberFormatException e) {
			return -1; // để thoát game
		}
	}
	
	

	public int[] getInput(int index) {
		System.out.print("Người chơi " + (index % 2 != 0 ? "X" : "O") + ", nhập toạ độ (x(1-9), y(1-9)): ");
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		scanner.nextLine(); // bỏ dòng thừa sau nextInt()
		return new int[] { x, y };
	}	

	public void showMessage(String message) {
		System.out.println(message);
	}
}
