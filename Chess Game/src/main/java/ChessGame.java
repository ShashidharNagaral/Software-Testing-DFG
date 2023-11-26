import java.util.ArrayList;
import java.util.Scanner;

public class ChessGame {
    private ChessBoard chessBoard;
    private COLOR currentPlayer;

    private ArrayList<ChessPiece> blackPieces, whitePieces;

    private King whiteKing, blackKing;

    public ChessGame() {
        this.chessBoard = new ChessBoard(this);
    }

    public ArrayList<ChessPiece> getBlackPieces() {
        return blackPieces;
    }

    public ArrayList<ChessPiece> getWhitePieces() {
        return whitePieces;
    }

    /** Layout of Chess board with coordinate axes

                        BLACK PLAYER

                    0  1  2  3  4  5  6  7 → X
                0
                1
                2
                3
                4
                5
                6
                7
                ↓
                Y

                        WHITE PLAYER
    */


    private void gameSetup() {
        this.whitePieces = new ArrayList<>();
        this.blackPieces = new ArrayList<>();

        currentPlayer = COLOR.WHITE; // set current player to white

        // put all the BLACK pawns
        this.blackPieces.add(new Pawn(0, 1, COLOR.BLACK, this.chessBoard));
        this.blackPieces.add(new Pawn(1, 1, COLOR.BLACK, this.chessBoard));
        this.blackPieces.add(new Pawn(2, 1, COLOR.BLACK, this.chessBoard));
        this.blackPieces.add(new Pawn(3, 1, COLOR.BLACK, this.chessBoard));
        this.blackPieces.add(new Pawn(4, 1, COLOR.BLACK, this.chessBoard));
        this.blackPieces.add(new Pawn(5, 1, COLOR.BLACK, this.chessBoard));
        this.blackPieces.add(new Pawn(6, 1, COLOR.BLACK, this.chessBoard));
        this.blackPieces.add(new Pawn(7, 1, COLOR.BLACK, this.chessBoard));

        // put black rooks
        this.blackPieces.add(new Rook(0, 0, COLOR.BLACK, this.chessBoard));
        this.blackPieces.add(new Rook(7, 0, COLOR.BLACK, this.chessBoard));

        // put black knight
        this.blackPieces.add(new Rook(1, 0, COLOR.BLACK, this.chessBoard));
        this.blackPieces.add(new Rook(6, 0, COLOR.BLACK, this.chessBoard));

        // put black bishops
        this.blackPieces.add(new Bishop(2, 0, COLOR.BLACK, this.chessBoard));
        this.blackPieces.add(new Bishop(5, 0, COLOR.BLACK, this.chessBoard));

        // put black queen
        this.blackPieces.add(new Queen(3, 0, COLOR.BLACK, this.chessBoard));

        // put black king
        this.blackKing = new King(4, 0, COLOR.BLACK, this.chessBoard);
        this.blackPieces.add(this.blackKing);

        // put all the WHITE pawns
        this.whitePieces.add(new Pawn(0, 6, COLOR.WHITE, this.chessBoard));
        this.whitePieces.add(new Pawn(1, 6, COLOR.WHITE, this.chessBoard));
        this.whitePieces.add(new Pawn(2, 6, COLOR.WHITE, this.chessBoard));
        this.whitePieces.add(new Pawn(3, 6, COLOR.WHITE, this.chessBoard));
        this.whitePieces.add(new Pawn(4, 6, COLOR.WHITE, this.chessBoard));
        this.whitePieces.add(new Pawn(5, 6, COLOR.WHITE, this.chessBoard));
        this.whitePieces.add(new Pawn(6, 6, COLOR.WHITE, this.chessBoard));
        this.whitePieces.add(new Pawn(7, 6, COLOR.WHITE, this.chessBoard));

        // put white rooks
        this.whitePieces.add(new Rook(0, 7, COLOR.WHITE, this.chessBoard));
        this.whitePieces.add(new Rook(7, 7, COLOR.WHITE, this.chessBoard));

        // put white knight
        this.whitePieces.add(new Rook(1, 7, COLOR.WHITE, this.chessBoard));
        this.whitePieces.add(new Rook(6, 7, COLOR.WHITE, this.chessBoard));

        // put white bishops
        this.whitePieces.add(new Bishop(2, 7, COLOR.WHITE, this.chessBoard));
        this.whitePieces.add(new Bishop(5, 7, COLOR.WHITE, this.chessBoard));

        // put white queen
        this.whitePieces.add(new Queen(3, 7, COLOR.WHITE, this.chessBoard));

        // put white king
        this.whiteKing = new King(4, 7, COLOR.WHITE, this.chessBoard);
        this.whitePieces.add(this.whiteKing);
    }

    // call this method to start the game
    public void start() {
        gameSetup();
        Scanner userInput = new Scanner(System.in);
        // while game is not over
        while(!isGameOver()) {
            this.chessBoard.display();

            System.out.println("Player turn: " + currentPlayer.toString());

            System.out.println("What piece to move?");
            System.out.print("X loc: ");
            int currX = userInput.nextInt();
            System.out.print("Y loc: ");
            int currY = userInput.nextInt();

            ChessPiece playerPiece = this.chessBoard.chessPieceAt(currX, currY);

            int newX;
            int newY;
            ChessPiece pieceAtXY = null;
            if(playerPiece == null) {
                System.out.println("No piece at given location!");
                continue;
            } else if(playerPiece.getColor() != currentPlayer) {
                System.out.println("You picked other player piece!");
                continue;
            } else {
                System.out.println("Where to place?");
                System.out.print("X loc: ");
                newX = userInput.nextInt();
                System.out.print("Y loc: ");
                newY = userInput.nextInt();

                pieceAtXY = this.chessBoard.chessPieceAt(newX, newY);

                // check if a player can move playerPiece to new location
                if(!playerPiece.moveTo(newX, newY)) {
                    System.out.println("Invalid move!");
                    continue;
                }
            }

            // after the player's move the king should not have any check
            if(isKingInCheck(currentPlayer)) {
                reverseMove(playerPiece, pieceAtXY, currX, currY, newX, newY);
                System.out.println("Check on the king, please play any other move!");
                continue;
            }

            // change the player turn if move is valid
            if(currentPlayer == COLOR.WHITE)
                currentPlayer = COLOR.BLACK;
            else if(currentPlayer ==  COLOR.BLACK)
                currentPlayer = COLOR.WHITE;
        }

    }

    // reverse the move played by a player
    private void reverseMove(ChessPiece playerPiece, ChessPiece pieceAtXY, int startX, int startY, int endX, int endY) {
        // reverse the move
        playerPiece.moveTo(startX, startY);
        if(pieceAtXY != null) {
            this.chessBoard.putPieceOnBoard(pieceAtXY, endX, endY);

            // put back the removed piece in the arraylist
            if(pieceAtXY.getColor() == COLOR.WHITE) {
                this.whitePieces.add(pieceAtXY);
            } else {
                this.blackPieces.add(pieceAtXY);
            }
        }
    }

    // check if other player had given any check to current player
    private boolean isKingInCheck(COLOR currentPlayer) {
        King currentPlayerKing;
        ArrayList<ChessPiece> otherPlayerPieces;

        currentPlayerKing = currentPlayer == COLOR.WHITE ? this.whiteKing : this.blackKing;
        otherPlayerPieces = currentPlayer == COLOR.WHITE ? this.blackPieces : this.whitePieces;


        int kingX = currentPlayerKing.getX();
        int kingY = currentPlayerKing.getY();

        // if any of other player piece attack king then it is checked, else not check
        for(ChessPiece piece: otherPlayerPieces) {
            if(piece.canMoveTo(kingX, kingY)) {
                return true;
            }
        }
        return false;
    }

    // if check and no move to play then checkmate
    public boolean isCheckMate(COLOR currentPlayer) {
        if(isKingInCheck(currentPlayer) && !doesPlayerHaveAnyMove(currentPlayer)) {
            return true;
        }
        return false;
    }

    private boolean isGameOver() {
        if(isCheckMate(this.currentPlayer)) {
            if(this.currentPlayer == COLOR.WHITE) {
                System.out.println("CHECKMATE: Black Player Own");
            } else {
                System.out.println("CHECKMATE: White Player Own");
            }
            return true;
        } else if(!doesPlayerHaveAnyMove(this.currentPlayer)) {
            System.out.println("STALEMATE!");
            return true;
        }
        return false;
    }

    // check if a player have any moves to play
    // if not then either it is checkmate or stalemate
    private boolean doesPlayerHaveAnyMove(COLOR currentPlayer) {
        ArrayList<ChessPiece> currentPlayerPieces = currentPlayer == COLOR.WHITE ? this.whitePieces : this.blackPieces;

        for(int x = 0; x < ChessUtil.SIZE; x++) {
            for(int y = 0; y < ChessUtil.SIZE; y++) {
                for(ChessPiece piece: currentPlayerPieces) {

                    // check if piece has a move to x, y
                    if(piece.canMoveTo(x, y)) {
                        ChessPiece pieceAtXY = this.chessBoard.chessPieceAt(x, y);
                        int currX = piece.getX();
                        int currY = piece.getY();

                        piece.moveTo(x, y);

                        // after the move check if king is still in check
                        if(!isKingInCheck(currentPlayer)) {
                            // reverse the play
                            reverseMove(piece, pieceAtXY, currX, currY, x, y);
                            // if king is not in check after this move we will return true
                            return true;
                        } else {
                            // reverse the play
                            reverseMove(piece, pieceAtXY, currX, currY, x, y);
                        }
                    }
                }
            }
        }
        return false;
    }
}
