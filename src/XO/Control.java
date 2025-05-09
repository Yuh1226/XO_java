package XO;

import java.util.Stack;

public class Control {
    private Game game;
    private View view;
    private Stack<Move> undoStack;
    private Stack<Move> redoStack;
    private int turn = 1;

    public Control(Game game, View view) {
        this.game = game;
        this.view = view;
        this.undoStack = game.getUndoStack();
        this.redoStack = game.getRedoStack();
    }
    
    public void run() {
        view.displayBoard(game.getBoard());
        while (!game.isFull()) {
            int option = view.getCommand();
            if (option == 1) {
        
            	// xác định lượt chơi
            	if (option == 1) {
                    System.out.println("Lượt của người chơi " + (turn % 2 != 0 ? "X" : "O"));
                    if (play(turn)) turn++;
                    view.displayBoard(game.getBoard());
                }
                
            	// view.displayBoard(game.getBoard());
            } else if (option == 2) { 
                undo();
            } else if (option == 3) { 
                redo();
            } else {
                view.showMessage("Đã thoát game!");
                return;
            }
            if (game.checkWinner() == 1) {
                view.showMessage("Người chơi X thắng");
                view.displayBoard(game.getBoard());
                return;
            } else if (game.checkWinner() == -1) {
                view.showMessage("Người chơi O thắng");
                view.displayBoard(game.getBoard());
                return;
            }
        }
        view.displayBoard(game.getBoard());
        view.showMessage("Hai bên hoà nhau!");
    }

    public boolean play(int turn) {
        int[] input = view.getInput(turn);
        int x = input[0] - 1;
        int y = input[1] - 1;
        int value = (turn % 2 != 0) ? 1 : -1; // X = 1, O = -1
    
        if (x < 0 || x >= 9 || y < 0 || y >= 9) {
            view.displayBoard(game.getBoard(), "Đầu vào không hợp lệ! Vui lòng nhập lại.");
            return false;
        }
        if (!game.isValidMove(x, y)) {
            view.displayBoard(game.getBoard(), "Nước đi không hợp lệ! Vui lòng thử lại.");
            return false;
        }
        redoStack.clear();
        int oldValue = game.getBoard()[x][y].getValue();
        game.getBoard()[x][y].setValue(value);
        undoStack.push(new Move(x, y, oldValue, value));
        return true;
    }
    

    public void undo() {
        if (undoStack.isEmpty()) {
            view.displayBoard(game.getBoard(), "Không có thao tác để hoàn tác!");
            return;
        }
        turn--;
        Move move = undoStack.pop();
        game.getBoard()[move.getRow()][move.getCol()].setValue(move.getOldValue());
        redoStack.push(move);
        view.displayBoard(game.getBoard(), "Đã hoàn tác!");
    }
    
    public void redo() {
        if (redoStack.isEmpty()) {
            view.displayBoard(game.getBoard(), "Không có thao tác để làm lại!");
            return;
        }
        turn++;
        Move move = redoStack.pop();
        game.getBoard()[move.getRow()][move.getCol()].setValue(move.getNewValue());
        undoStack.push(move);
        view.displayBoard(game.getBoard(), "Đã làm lại!");
    }
    

    // public void exit() {
    //     view.showMessage("Đã thoát game.");
    //     break();
    // }
}
