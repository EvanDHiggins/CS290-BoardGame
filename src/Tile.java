import java.util.Optional;

/**
 * Created by evan on 2/12/16.
 */
public class Tile {

    static String emptyTileString = ".";

    private Optional<Piece> piece;
    private BoardPosition position;

    public Tile(BoardPosition position) {
        piece = Optional.empty();
        this.position = position;
    }

    public Tile(int rowIdx, int colIdx) {
        this(new BoardPosition(rowIdx, colIdx));
    }

    public void setPiece(Piece piece) {
        this.piece = Optional.ofNullable(piece);
    }

    public Optional<Piece> getPiece() {
        return piece;
    }

    public BoardPosition getPosition() {
        return position;
    }

    public boolean hasPiece(Piece piece) {
        return this.piece.equals(Optional.of(piece));
    }

    @Override
    public String toString() {
        return piece.map(Piece::toString)
                    .orElse(Tile.emptyTileString);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Tile))
            return false;
        if(obj == this)
            return true;

        Tile that = (Tile)obj;
        return this.getPiece().equals(that.getPiece()) && this.getPosition().equals(that.getPosition());
    }

    public boolean isBlank(){
        return !piece.isPresent();
    }
}
