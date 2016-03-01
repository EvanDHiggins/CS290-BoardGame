import java.util.ArrayList;
import java.util.List;

/**
 * Created by evan on 2/15/16.
 */
public class BoardPosition {

    private int columnIdx;
    private int rowIdx;

    public BoardPosition(int rowIdx, int columnIdx) {
        this.rowIdx = rowIdx;
        this.columnIdx = columnIdx;
    }

    public int column() {
        return columnIdx;
    }

    public int row() {
        return rowIdx;
    }

    public String toString() {
        return String.format("(%1d, %2d)", row(), column());
    }

    public BoardPosition plus(BoardPosition that) {
        return new BoardPosition(this.row() + that.row(), this.column() + that.column());
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof BoardPosition))
            return false;
        if(obj == this)
            return true;

        BoardPosition that = (BoardPosition)obj;
        return this.row() == that.row() && this.column() == that.column();
    }

    /**
     * Hex adjacencies are the positions directly adjacent within a
     * row/column to a given position, and the positions adjacent along
     * one of the two diagonals. In this case the forward leaning
     * diagonal is considered an adjacency while the backward leaning
     * diagonal is not.
     */
    public List<BoardPosition> getAdjacencies() {
        List<BoardPosition> result = new ArrayList<>();
        for(int i = -1; i < 2; i++) {
            for(int j = -1; j < 2; j++) {
                if(i + j != 0)
                    result.add(this.plus(new BoardPosition(i, j)));
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int p1 = 37;
        int p2 = 47;
        int n = row();
        int q = column();
        if(n >= 0)
            n += 1;
        if(q >= 0)
            q += 1;
        return n*p1 + q*p2;
    }
}

