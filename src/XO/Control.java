package XO;

public class Control {
	private Game game;
    private View view;

    public Control(Game game, View view) {
        this.game = game;
        this.view = view;
    }

    public void run() {
        view.displayBoard(game.getBoard());
    }
}
