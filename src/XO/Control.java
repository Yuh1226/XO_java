package XO;

import java.util.Stack;

public class Control {
    private Game game;
    private View view;
    private Stack<Move> undoStack;
    private Stack<Move> redoStack;

    public Control(Game game, View view) {
        this.game = game;
        this.view = view;
        this.undoStack = game.getUndoStack();
        this.redoStack = game.getRedoStack();
    }

    public void run() {
        // view.displayBoard(game.getBoard());
        int turn = 0;
        while (!game.isFull()) {
            int option = view.getCommand();
            view.displayBoard(game.getBoard());
            if (option == 1) { 
                play(++turn);
                System.out.println("\n\n\n\n\n");
                view.displayBoard(game.getBoard());
            } else if (option == 2) { 
                undo();
            } else if (option == 3) { 
                redo();
            } else {
                exit();
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
        int value = input[2];

        if (x >= 0 && x < 9 && y >= 0 && y < 9) {
            if (!game.isValidMove(x, y)) { // kiểm tra nước không hợp lệ
                view.showMessage("Nước đi không hợp lệ! Vui lòng thử lại.");
                return false;
            } else {
                int oldValue = game.getBoard()[x][y].getValue();
                game.getBoard()[x][y].setValue(value);
                undoStack.push(new Move(x, y, oldValue, value));
                redoStack.clear();
                return true;
            }
        } else {
            view.showMessage("Đầu vào không hợp lệ! Vui lòng nhập lại.");
            return false;
        }
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            view.showMessage("Không có thao tác để hoàn tác!");
        } else {
            Move move = undoStack.pop();
            game.getBoard()[move.getRow()][move.getCol()].setValue(move.getOldValue());
            redoStack.push(move);
            view.showMessage("Đã hoàn tác!");
            view.displayBoard(game.getBoard());
        }
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            view.showMessage("Không có thao tác để làm lại!");
        } else {
            Move move = redoStack.pop();
            game.getBoard()[move.getRow()][move.getCol()].setValue(move.getNewValue());
            undoStack.push(
                    new Move(move.getRow(), move.getCol(), move.getOldValue(), move.getNewValue()));
            view.showMessage("Đã làm lại!");
            view.displayBoard(game.getBoard());
        }
    }

    public void exit() {
        view.showMessage("Đã thoát game.");
        System.exit(0);
    }
}
