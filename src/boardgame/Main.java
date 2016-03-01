package boardgame;

import Hex.BlackHexPiece;
import Hex.HexGame;
import Hex.WhiteHexPiece;
import boardgame.Player;

import java.util.*;

/**
 * Created by evan on 2/12/16.
 */
public class Main {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("White boardgame.Player", new WhiteHexPiece()));
        players.add(new Player("Black boardgame.Player", new BlackHexPiece()));
        HexGame game = new HexGame(players, 11);
        game.run();
    }
}
