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
        view.displayBoard(game.getBoard());

        int option = view.getCommand();
        int s = 0;
        while (!game.isFull()) {
            if (option == 1) { // nhập tọa độ
                play(s);

            } else if (option == 2) { // undo
                undo();

            } else if (option == 3) { // redo
                redo();

            } else { // thoát
                exit();
            }
            // } else {
            // view.showMessage("Lệnh không hợp lệ! Vui lòng thử lại.");
            // }
        }
    }

    public void play(int s) {
        s = s + 1;
        int[] input = view.getInput(s);
        int x = input[0] - 1;
        int y = input[1] - 1;
        int value = input[2];

        if (x >= 0 && x < 9 && y >= 0 && y < 9) {
            if (!game.isValidMove(x, y)) { // kiểm tra nước không hợp lệ
                view.showMessage("Nước đi không hợp lệ! Vui lòng thử lại.");
                view.displayBoard(game.getBoard());
            } else {
                int oldValue = game.getBoard()[x][y].getValue();
                game.getBoard()[x][y].setValue(value);
                undoStack.push(new Move(x, y, oldValue, value));
                redoStack.clear();

                view.displayBoard(game.getBoard());
            }

            if (s >= 9 && game.checkWinner() == 1) {
                view.showMessage("Người chơi x thắng");
                return;
            } else if (s >= 9 && game.checkWinner() == -1) {
                view.showMessage("Người chơi o thắng");
                return;
            }
        } else {
            view.showMessage("Đầu vào không hợp lệ! Vui lòng nhập lại.");
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
