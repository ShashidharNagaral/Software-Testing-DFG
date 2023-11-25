import java.util.Scanner;

public class ChessGame {
    private ChessBoard chessBoard;
    private COLOR currentPlayer;

    public ChessGame() {
        this.chessBoard = new ChessBoard();
    }

    private void gameSetup() {
        // TODO: we will create all the pieces and will put them at initial location on chess board
        currentPlayer = COLOR.WHITE;

    }

    public void start() {
        gameSetup();
        Scanner userInput = new Scanner(System.in);
        // while game is not over
        while(!isGameOver()) {

            System.out.println("Player turn: " + currentPlayer.toString());




            // change the player turn
            if(currentPlayer == COLOR.WHITE)
                currentPlayer = COLOR.BLACK;
            else if(currentPlayer ==  COLOR.BLACK)
                currentPlayer = COLOR.WHITE;
        }

    }


    private boolean isGameOver() {
        // TODO: this method will check if game has ended i.e CHECKMATE or STALEMATE
        return true;
    }




}
