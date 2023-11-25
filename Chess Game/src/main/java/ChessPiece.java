public class ChessPiece {

    private int x; // x coordinate
    private int y; // y coordinate

    private COLOR color;
    private ChessBoard chessBoard;


    public ChessPiece(int x, int y, COLOR color, ChessBoard chessBoard) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.chessBoard = chessBoard;
    }
}
