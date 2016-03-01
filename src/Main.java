import java.util.*;

/**
 * Created by evan on 2/12/16.
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("White Player", new WhitePiece()));
        players.add(new Player("Black Player", new BlackPiece()));
        Game game = new Game(players);
        game.run();
    }
}
