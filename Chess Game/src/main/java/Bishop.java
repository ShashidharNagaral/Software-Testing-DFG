public class Bishop extends ChessPiece{
    public Bishop(int x, int y, COLOR color, ChessBoard chessBoard) {
        super(x, y, color, chessBoard);
    }

    @Override
    public boolean canMoveTo(int toNewX, int toNewY){
        if(isCorrectMove(toNewX,toNewY)){
            return checkBishopMovement(toNewX, toNewY);
        }
        return false;
    }

    private boolean checkBishopMovement(int toNewX, int toNewY) {
        return checkDiagonalMovement(toNewX, toNewY);
    }
}
