package checkers;

import boardgame.Position;

/**
 * Created by Evan on 3/2/2016.
 */
public class Move {

    Position from;
    Position to;

    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }
}
