/**
 * Created by evan on 2/14/16.
 */
public abstract class Piece {

    protected String stringRepr;

    @Override
    public String toString() {
        return stringRepr;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Piece))
            return false;
        if(this == obj)
            return true;

        Piece that = (Piece)obj;
        return this.toString().equals(that.toString());
    }
}
