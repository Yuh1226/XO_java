package XO;

import java.util.Stack;

public class Game {
    private Node[][] board;
    private Stack<Move> undoStack;
    private Stack<Move> redoStack;

    public Game(int[][] Board) {
        board = new Node[9][9];
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new Node(i, j, Board[i][j]);
            }
        }
    }

    public Node[][] getBoard() {
        return board;
    }

    public Stack<Move> getUndoStack() {
        return undoStack;
    }

    public Stack<Move> getRedoStack() {
        return redoStack;
    }

    private static boolean checkline(Node[][] Board, int x, int y, int dx, int dy, int win_length, int player) {
        for (int k = 0; k < 5; k++) {
            int nx = x + k * dx;
            int ny = y + k * dy;
            if (nx < 0 || nx >= 9 || ny < 0 || ny >= 9 || Board[nx][ny].getValue() != player) {
                return false;
            }
        }
        return true;
    }

    public int checkWinner() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int player = board[i][j].getValue();
                if (player == 0)
                    continue;

                // 4 hướng
                if (checkline(board, i, j, 0, 1, 5, player))
                    return player; // ngang
                if (checkline(board, i, j, 1, 0, 5, player))
                    return player; // dọc
                if (checkline(board, i, j, 1, 1, 5, player))
                    return player; // chéo chính
                if (checkline(board, i, j, 1, -1, 5, player))
                    return player; // chéo phụ
            }
        }

    }

    // kiểm tra xem đầy ô chưa
    public boolean isFull() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j].getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidMove(int x, int y) {
        if (x < 0 || x >= 9 || y < 0 || y >= 9) {
            return false;
        }
        return board[x][y].getValue() == 0;
    }
}
