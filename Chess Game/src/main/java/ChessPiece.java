public class ChessPiece {

    private int x; // x coordinate
    private int y; // y coordinate

    private COLOR color;
    private ChessBoard chessBoard;

    private boolean hasMoved; // to check if the piece has moved atleast once in the game


    public ChessPiece(int x, int y, COLOR color, ChessBoard chessBoard) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.chessBoard = chessBoard;
        this.hasMoved = false;
    }

    public int getX() {
        return x;
    }

    public COLOR getColor() {
        return color;
    }

    public int getY() {
        return y;
    }

}
