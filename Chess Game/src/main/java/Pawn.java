public class Pawn extends ChessPiece{

    public Pawn(int x, int y, COLOR color, ChessBoard chessBoard) {
        super(x, y, color, chessBoard);
    }

    @Override
    public boolean canMoveTo(int toNewX, int toNewY){
        if(isCorrectMove(toNewX,toNewY)){
            return checkPawnMovement(toNewX, toNewY);
        }
        return false;
    }

    private boolean checkPawnMovement(int toNewX, int toNewY){
        int moveOneStep;
        int moveTwoStep;
        ChessPiece pieceAtNewXY = chessBoard.chessPieceAt(toNewX, toNewY);

        if (this.getColor() == COLOR.BLACK){
            moveOneStep = 1;
            moveTwoStep = 2;
        }
        else{
            moveOneStep = -1;
            moveTwoStep = -2;
        }

        // Conditions for moving the pawn one step forward
        if(toNewX - this.getX() == moveOneStep) {
            // to move one step straight forward check if there is no piece at the new location
            if(toNewY == this.getY() && pieceAtNewXY == null) {
                return true;
            }

            // to move one step diagonally check if there is a piece already at that new location
            if(Math.abs(this.getY() - toNewY) == 1 && pieceAtNewXY != null) {
                return true;
            }
        }
        // Conditions for moving the pawn two steps forward
        // this is only possible if the pawn has never moved before in the entire game play
        else if(!hasMoved) {
            if (toNewX - this.getX() == moveTwoStep){
                if (toNewY == this.getY() && pieceAtNewXY == null){
                    return true;
                }
            }
        }

        return false;
    }
}
