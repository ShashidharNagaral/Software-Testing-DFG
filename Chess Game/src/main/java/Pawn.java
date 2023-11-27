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

        int currX = this.getX();
        int currY = this.getY();

        COLOR currColor = this.getColor();

        ChessPiece pieceAtNewXY = chessBoard.chessPieceAt(toNewX, toNewY);

        if (currColor == COLOR.BLACK){
            moveOneStep = 1;
            moveTwoStep = 2;
        }
        else{
            moveOneStep = -1;
            moveTwoStep = -2;
        }

        // Conditions for moving the pawn one step forward
        if((toNewY - currY) == moveOneStep) {
            // to move one step straight forward check if there is no piece at the new location
            if(toNewX == currX && pieceAtNewXY == null) {
                return true;
            }

            // to move one step diagonally check if there is a piece already at that new location
            if(Math.abs(currX - toNewX) == 1 && pieceAtNewXY != null) {
                return true;
            }
        }
        // Conditions for moving the pawn two steps forward
        // this is only possible if the pawn has never moved before in the entire game play
        else if(!this.hasMoved) {
            if ((toNewY - currY) == moveTwoStep){
                ChessPiece pieceInBetween = chessBoard.chessPieceAt(currX, moveOneStep + currY);
                if (toNewX == currX && pieceAtNewXY == null && pieceInBetween == null){
                    return true;
                }
            }
        }

        return false;
    }
}
