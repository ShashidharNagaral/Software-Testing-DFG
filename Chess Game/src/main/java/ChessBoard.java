public class ChessBoard {
    private ChessPiece[][] board; // board 2d array contains chess pieces

    public ChessBoard() {
        board = new ChessPiece[ChessUtil.SIZE][ChessUtil.SIZE]; // 8x8 chess board
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
}
