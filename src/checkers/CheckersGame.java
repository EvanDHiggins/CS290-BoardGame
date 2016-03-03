package checkers;

import boardgame.TwoPlayerGame;
import boardgame.Main;
import boardgame.Player;

/**
 * Created by Evan on 3/2/2016.
 */
public class CheckersGame extends TwoPlayerGame {

    CheckerBoard board;

    public CheckersGame(Player player1, Player player2, int boardSize) {
        super(player1, player2);

        board = new CheckerBoard(boardSize);
    }

    @Override
    public void run() {
        while(true) {
            board.printBoard();
            Main.input.nextLine();
        }
    }
}
