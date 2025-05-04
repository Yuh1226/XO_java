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
	}

	public int getCommand() {
		System.out.println("====== GAME X/O MENU ======");
		System.out.println("1. Set");
		System.out.println("2. Undo ");
		System.out.println("3. Redo ");
		System.out.println("4. Exit ");
		System.out.println("==========================");
		System.out.print("Option(1-4): ");
		return scanner.nextInt();
	}

	public int[] getInput(int index) {
		System.out.print("coordinate : ");
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		int value;
		if (index % 2 != 0)
			value = 1;
		else
			value = -1;
		return new int[] { x, y, value };
	}

	public void showMessage(String message) {
		System.out.println(message);
	}
}
