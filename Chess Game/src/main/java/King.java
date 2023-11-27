public class King extends ChessPiece{
    public King(int x, int y, COLOR color, ChessBoard chessBoard) {
        super(x, y, color, chessBoard);
    }

    @Override
    public boolean canMoveTo(int toNewX, int toNewY) {
        if(isCorrectMove(toNewX,toNewY)){
            return checkKingMovement(toNewX, toNewY);
        }
        return false;
    }


    public boolean checkKingMovement(int toNewX, int toNewY) {
        int absX = Math.abs(toNewX - this.getX()); // absolute difference between new X location and current X location
        int absY = Math.abs(toNewY - this.getY()); // absolute difference between new Y location and current Y location

        if (absX <= 1 && absY <= 1) {
            return absX != 0 || absY != 0;
        }
        return false;
    }
}
