package XO;

public class Game {
	private Node[][] board;
	
	public Game(int[][] Board) {
        board = new Node[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new Node(i, j,Board[i][j]);
            }
        }
    }
	
	public Node[][] getBoard() {
		return board;
	}
}
