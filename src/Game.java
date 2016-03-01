import java.util.*;

/**
 * Created by evan on 2/14/16.
 */
public class Game {

    Scanner in;

    Player currentPlayer;

    //Player order is maintained by cycling through a queue.
    Queue<Player> otherPlayers;
    HexBoard board;

    public Game(Collection<Player> players) {
        otherPlayers = new LinkedList<>(players);
        currentPlayer = otherPlayers.remove();
        in = new Scanner(System.in);

        board = new HexBoard(new ArrayList<>(players));
    }

    public void run() {
        while(true) {
            board.printBoard();
            playerTurn(currentPlayer);
            if(currentPlayerHasWon()) {
                playerWins(currentPlayer);
            }
            nextPlayer();
        }
    }

    /**
     * Executes a player's turn until a valid position has been chosen.
     */
    private void playerTurn(Player player) {
        while(true) {
            playerPrompt(player);
            String input = in.nextLine();
            if(input.toLowerCase().equals("exit"))
                exitGame();
            if(board.tryPlayPosition(input, player.getPiece()))
                return;
            System.out.println("Invalid position, try again.");
        }
    }

    private void exitGame() {
        System.out.println("Thanks for playing!");
        System.exit(0);
    }

    private  void playerPrompt(Player player) {
        System.out.println("Choose a position, " + player.getName() + ".");
        System.out.print("Your piece is " + player.getPiece().toString() + ":");
    }

    private void playerWins(Player player) {
        System.out.println("Congratulations, " + player.getName() + "! You won!");
        System.exit(0);
    }

    private boolean currentPlayerHasWon() {
        return board.doesPlayerWin(currentPlayer);
    }

    /**
     * A Queue of players keeps player order and makes
     * cycling through players easy. An arbitrary number
     * of players are available with this implementation.
     */
    private void nextPlayer() {
        otherPlayers.add(currentPlayer);
        currentPlayer = otherPlayers.remove();
    }
}
