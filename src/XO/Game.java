package XO;

import java.util.Stack;

public class Game {
    private static final int BOARD_SIZE = 9;
    private static final int WIN_LENGTH = 5;

    private Node[][] board;
    private Stack<Move> undoStack;
    private Stack<Move> redoStack;

    public Game(int[][] Board) {
        board = new Node[BOARD_SIZE][BOARD_SIZE];
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
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

    private static boolean checkline(Node[][] board, int x, int y, int dx, int dy, int player) {
        for (int k = 0; k < WIN_LENGTH; k++) {
            int nx = x + k * dx;
            int ny = y + k * dy;
            if (nx < 0 || nx >= BOARD_SIZE || ny < 0 || ny >= BOARD_SIZE || board[nx][ny].getValue() != player) {
                return false;
            }
        }
        return true;
    }

    public int checkWinner() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                int player = board[i][j].getValue();
                if (player == 0) continue;

                // Kiểm tra 4 hướng: ngang, dọc, chéo chính, chéo phụ
                if (checkline(board, i, j, 0, 1, player) ||  // ngang
                    checkline(board, i, j, 1, 0, player) ||  // dọc
                    checkline(board, i, j, 1, 1, player) ||  // chéo chính
                    checkline(board, i, j, 1, -1, player))   // chéo phụ
                {
                    return player;
                }
            }
        }
        return 0; // chưa ai thắng
    }

    public boolean isFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j].getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && board[x][y].getValue() == 0;
    }

    // dùng để reset bàn cờ
    // public void setBoard(int[][] Board) {
    //     for (int i = 0; i < BOARD_SIZE; i++) {
    //         for (int j = 0; j < BOARD_SIZE; j++) {
    //             board[i][j].setValue(Board[i][j]);
    //         }
    //     }
    //     undoStack.clear();
    //     redoStack.clear();
    // }
}
