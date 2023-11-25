public class Queen extends ChessPiece{
    public Queen(int x, int y, COLOR color, ChessBoard chessBoard) {
        super(x, y, color, chessBoard);
    }

    @Override
    public boolean canMoveTo(int toNewX, int toNewY){
        if(isCorrectMove(toNewX,toNewY)){
            return checkQueenMovement(toNewX, toNewY);
        }
        return false;
    }

    private boolean checkQueenMovement(int toNewX, int toNewY){
        return checkStraightMovement(toNewX, toNewY) || checkDiagonalMovement(toNewX, toNewY);
    }
}
