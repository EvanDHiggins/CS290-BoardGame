package boardgame;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Evan on 2/29/2016.
 */
public abstract class Game {

    protected Player currentPlayer;

    Queue<Player> otherPlayers;

    protected Game() {
        otherPlayers = new LinkedList<>();
    }

    protected Game(List<Player> players) {
        otherPlayers = new LinkedList<>(players);
        currentPlayer = otherPlayers.remove();
    }

    /**
     * A Queue of players keeps player order and makes
     * cycling through players easy. An arbitrary number
     * of players are available with this implementation.
     */
    protected void nextPlayer() {
        otherPlayers.add(currentPlayer);
        currentPlayer = otherPlayers.remove();
    }

    public abstract void run();
}
