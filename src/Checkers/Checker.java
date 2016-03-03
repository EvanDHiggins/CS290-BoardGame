package checkers;

import boardgame.IMoveGenerator;
import boardgame.Piece;
import boardgame.Position;

import java.util.Collection;

/**
 * Created by Evan on 3/2/2016.
 */
public abstract class Checker extends Piece {

    protected Checker() {
        super();
    }

    protected Checker(Position position) {
        super(position);
    }

}
