public class Rook extends ChessPiece{
    public Rook(int x, int y, COLOR color, ChessBoard chessBoard) {
        super(x, y, color, chessBoard);
    }

    @Override
    public boolean canMoveTo(int toNewX, int toNewY){
        if(isCorrectMove(toNewX,toNewY)){
            return checkRookMovement(toNewX, toNewY);
        }
        return false;
    }

    private boolean checkRookMovement(int toNewX, int toNewY) {
        return checkStraightMovement(toNewX, toNewY);
    }
}
