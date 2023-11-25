import java.util.Scanner;

public class ChessGame {
    private ChessBoard chessBoard;
    private COLOR currentPlayer;

    public ChessGame() {
        this.chessBoard = new ChessBoard();
        currentPlayer = COLOR.WHITE;
    }

    public void gameSetup() {
        // TODO: we will create all the pieces and will put them at initial location on chess board

    }

    public void start() {
        gameSetup();

        Scanner userInput = new Scanner(System.in);

    }




}
