public class Knight extends ChessPiece{
    public Knight(int x, int y, COLOR color, ChessBoard chessBoard) {
        super(x, y, color, chessBoard);
    }

    public boolean canMoveTo(int toNewX, int toNewY){
        if(isCorrectMove(toNewX,toNewY)){
            return checkKnightMovement(toNewX, toNewY);
        }
        return false;
    }

    private boolean checkKnightMovement(int toNewX, int toNewY) {

        int currX = this.getX();
        int currY = this.getY();

        int absX = Math.abs(toNewX - currX); // absolute difference between new X location and current X location
        int absY = Math.abs(toNewY - currY); // absolute difference between new Y location and current Y location

        if (absX == 2 && absY == 1) {
            return true;
        }
        if (absX == 1 && absY == 2) {
            return true;
        }

        return false;
    }
}
