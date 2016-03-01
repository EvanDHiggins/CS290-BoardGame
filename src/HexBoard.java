import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by evan on 2/12/16.
 *
 * HexBoard encapsulates the logic of a Hex game board, the console IO, and
 * the algorithms to determine a winner.
 */
public class HexBoard {

    private int size = 11;

    Tile board[][];

    Player leftToRightPlayer;
    Player topToBottomPlayer;

    public HexBoard(List<Player> players) {
        if(players.size() < 2)
            throw new IllegalArgumentException("Not enough players for board initialization.");

        leftToRightPlayer = players.get(0);
        topToBottomPlayer = players.get(1);

        board = new Tile[size][size];

        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                board[i][j] = new Tile(i, j);
            }
        }
    }

    /**
     * This function is ugly.
     */
    public void printBoard() {
        System.out.print(String.format("%1$" + (size+2) + "s ", " "));
        for(int i = 0; i < size; ++i) {
            System.out.print(topToBottomPlayer.getPiece().toString() + " ");
        }
        System.out.println();
        for(int i = size - 1; i >= 0; i--) {
            int lineNumber = i + 1;
            System.out.print(String.format("%1$" + lineNumber + "s " + leftToRightPlayer.getPiece().toString() + " ", lineNumber));

            for(Tile tile : board[i]) {
                System.out.print(tile.toString() + " ");
            }

            System.out.print(" " + leftToRightPlayer.getPiece().toString());
            System.out.println();
        }

        System.out.print("    ");
        for(int i = 0; i < size; ++i) {
            System.out.print(topToBottomPlayer.getPiece().toString() + " ");
        }
        System.out.println();

        System.out.print("    ");
        for(int i = 0; i < size; ++i) {
            System.out.print((char)('A' + i) + " ");
        }
        System.out.println();
    }

    /**
     * Parses the input string and attempts to place the passed piece
     * at the parsed location if the location exists.
     * @return If Piece was successfully placed.
     */
    public boolean tryPlayPosition(String positionString, Piece piece) {
        if(!matchesPattern(positionString))
            return false;

        Optional<BoardPosition> position = parseInput(positionString);

        return position.map(pos -> {
                    setPosition(pos, piece);
                    return true;
               }).orElse(false);
    }

    public boolean doesPlayerWin(Player player) {
        if(player.equals(leftToRightPlayer)) {
            return regionsConnected(getColumn(0), getColumn(size - 1), player.getPiece());
        } else if(player.equals(topToBottomPlayer)) {
            return regionsConnected(Arrays.asList(board[0]), Arrays.asList(board[size - 1]), player.getPiece());
        } else {
            System.out.println("That player is not involved in this game.");
            return false;
        }
    }

    public Tile getTile(BoardPosition bp) {
        return board[bp.row()][bp.column()];
    }

    private List<Tile> getColumn(int colNum) {
        List<Tile> column = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            column.add(board[i][colNum]);
        }
        return column;
    }

    /**
     * Determines if two arbitrary lists of tiles "from", "to" are connected by
     * a path of piece "pieceType" through the recursive function pathBetween.
     */
    private boolean regionsConnected(List<Tile> from, List<Tile> to, Piece pieceType) {
        to.stream().filter(tile -> tile.hasPiece(pieceType))
                   .collect(Collectors.toList());

        for(Tile t : from) {
            if(pathBetween(t.getPosition(), to, pieceType, new ArrayList<>()))
                return true;
        }
        return false;
    }

    private boolean pathBetween(BoardPosition from, List<Tile> to, Piece pieceType, List<BoardPosition> searched) {
        if(!withinBounds(from)) return false;
        if(searched.contains(from)) return false;

        Tile fromTile = getTile(from);
        if(fromTile.isBlank()) return false;
        if(!fromTile.getPiece().get().equals(pieceType)) return false;

        List<BoardPosition> adjacencies = from.getAdjacencies();
        if(to.contains(fromTile)) return true;

        searched.add(from);
        for(BoardPosition bp : adjacencies) {
            if(pathBetween(bp, to, pieceType, searched))
                return true;
        }

        return false;
    }

    /**
     * setPosition does now bounds checking. It assumes that pos is a
     * position which fits on the board which can be checked with validPosition.
     */
    private void setPosition(BoardPosition pos, Piece piece) {
        board[pos.row()][pos.column()].setPiece(piece);
    }

    /**
     * If a wrapped BoardPosition is returned it is guaranteed to fit on
     * the board. Strings which parse correctly are still considered invalid
     * if they do not fit on the board.
     */
    private Optional<BoardPosition> parseInput(String positionString) {
        int columnIdx = positionString.toUpperCase().charAt(0) - 'A';
        int rowIdx = Integer.parseInt(positionString.substring(1, positionString.length())) - 1;
        System.out.println(rowIdx);

        BoardPosition position = new BoardPosition(rowIdx, columnIdx);

        if(validPosition(position))
            return Optional.of(position);
        else
            return Optional.empty();
    }

    private boolean matchesPattern(String positionString) {
        Pattern regex = Pattern.compile("[A-Za-z]\\d{1,2}");
        return regex.matcher(positionString).matches();
    }

    private boolean validPosition(BoardPosition bp) {
        return withinBounds(bp) && tileIsEmpty(bp);
    }

    private boolean tileIsEmpty(BoardPosition bp) {
        return board[bp.row()][bp.column()].isBlank();
    }

    private boolean withinBounds(BoardPosition bp) {
        return bp.column() >= 0 && bp.column() < size &&
               bp.row() >= 0 && bp.row() < size;
    }
}
