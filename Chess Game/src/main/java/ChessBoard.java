import java.nio.channels.Pipe;
import java.util.ArrayList;

public class ChessBoard {
    private ChessPiece[][] board; // board 2d array contains chess pieces

    private ChessGame game;

    public ChessBoard(ChessGame game) {
        board = new ChessPiece[ChessUtil.SIZE][ChessUtil.SIZE]; // 8x8 chess board
        this.game = game;
    }

    public boolean boundCheck(int x, int y) {
        if(x >= 0 && y >= 0 && x < ChessUtil.SIZE && y < ChessUtil.SIZE)
            return true;
        return false;
    }

    // if x and y are within bound returns the pieces at that location
    public ChessPiece chessPieceAt(int x, int y) {
        if(boundCheck(x, y))
            return board[x][y];
        return null;
    }

    public ChessPiece[][] getBoard() {
        return board;
    }

    public void putPieceOnBoard(ChessPiece piece, int x, int y) {
        if(this.boundCheck(x, y)) {
            this.board[x][y]= piece;
        }
    }

    // this will make the piece location to null
    public void removePieceFromBoard(ChessPiece piece) {
        board[piece.getX()][piece.getY()] = null;
    }

    public void capturePiece(ChessPiece piece) {
        this.removePieceFromBoard(piece);
        if(piece.getColor() == COLOR.WHITE) {
            this.game.getWhitePieces().remove(piece);
        } else {
            this.game.getBlackPieces().remove(piece);
        }
    }

    public void display() {
        for(int y = 0; y<ChessUtil.SIZE; y++) {
            for(int x= 0; x<ChessUtil.SIZE; x++) {
                if(this.board[x][y] == null) {
                    System.out.print("_ ");
                } else {
                    ChessPiece piece = this.chessPieceAt(x, y);
                    if(piece instanceof Pawn) {
                        System.out.print(piece.getColor() == COLOR.WHITE ? ChessUtil.WHITE_PAWN + " " : ChessUtil.BLACK_PAWN + " ");
                    } else if (piece instanceof King) {
                        System.out.print(piece.getColor() == COLOR.WHITE ? ChessUtil.WHITE_KING + " " : ChessUtil.BLACK_KING + " ");
                    } else if (piece instanceof Queen) {
                        System.out.print(piece.getColor() == COLOR.WHITE ? ChessUtil.WHITE_QUEEN + " " : ChessUtil.BLACK_QUEEN + " ");
                    } else if (piece instanceof Knight) {
                        System.out.print(piece.getColor() == COLOR.WHITE ? ChessUtil.WHITE_KNIGHT + " " : ChessUtil.BLACK_KNIGHT + " ");
                    } else if (piece instanceof Rook) {
                        System.out.print(piece.getColor() == COLOR.WHITE ? ChessUtil.WHITE_ROOK + " " : ChessUtil.BLACK_ROOK + " ");
                    } else if (piece instanceof Bishop) {
                        System.out.print(piece.getColor() == COLOR.WHITE ? ChessUtil.WHITE_BISHOP + " " : ChessUtil.BLACK_BISHOP + " ");
                    }
                }
            }
            System.out.println();
        }
    }

}
