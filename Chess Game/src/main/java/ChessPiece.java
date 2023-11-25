public class ChessPiece {

    private int x; // x coordinate
    private int y; // y coordinate

    private COLOR color;
    protected ChessBoard chessBoard;

    protected boolean hasMoved; // to check if the piece has moved atleast once in the game


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

    public boolean canMoveTo(int toNewX, int toNewY){

        return isCorrectMove(toNewX, toNewY);
    }

    protected boolean isCorrectMove(int toNewX, int toNewY){
        if (chessBoard.boundCheck(toNewX, toNewY)){
            ChessPiece pieceAtNewXY = chessBoard.chessPieceAt(toNewX, toNewY);

            if (pieceAtNewXY == null) return true;
            if (pieceAtNewXY.getColor() != this.color) return true;
        }
        return false;
    }

    protected boolean checkStraightMovement(int toNewX, int toNewY) {
        int currX = this.getX();
        int currY = this.getY();

        int xStart;
        int yStart;
        int xFinish;
        int yFinish;

        // Fixing positon of X
        if (currX == toNewX){
            if (currY > toNewY){
                yStart = toNewY;
                yFinish = currY;
            }
            else if (toNewY > currY){
                yStart = currY;
                yFinish = toNewY;
            }
            else return false;

            // Loop to determine if any piece is between piece at newXY location and current location of the piece to be moved .
            yStart++;
            for(; yStart < yFinish; yStart++){
                if (chessBoard.chessPieceAt(currX, yStart) != null){
                    return false;
                }
            }
            return true;
        }

        // Fixing position of Y
        if (currY == toNewY){
            if (currX > toNewX){
                xStart = toNewX;
                xFinish = currX;
            }
            else if (toNewX > currX){
                xStart = currX;
                xFinish = toNewX;
            }
            else return false;

            // Loop to determine if any piece is between piece at newXY location and current location of the piece to be moved .

            xStart++;
            for(; xStart < xFinish; xStart++){
                if (chessBoard.chessPieceAt(xStart, currY) != null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    protected boolean checkDiagonalMovement(int toNewX, int toNewY) {
        int currX = this.getX();
        int currY = this.getY();

        int orientation;

        int xStart;
        int yStart;
        int xFinish;

        int xTotal = Math.abs(toNewX - currX);
        int yTotal = Math.abs(toNewY - currY);

        //Check if new position is diagonal
        if (xTotal == yTotal){

            // check the orientation of move
            if(((currX > toNewX) && (currY < toNewY)) || ((currX < toNewX) && (currY > toNewY))) {
                orientation = -1; // orientation is in positive direction
            } else if (((currX < toNewX) && (currY < toNewY)) || ((currX > toNewX) && (currY > toNewY))) {
                orientation = 1; // orientation is in negative direction
            } else {
                return false;
            }


            if (toNewX < currX){
                xStart = toNewX;
                xFinish = currX;
            }
            else if (toNewX > currX) {
                xStart = currX;
                xFinish = toNewX;
            } else {
                return false;
            }

            if (toNewY < currY){
                yStart = (orientation == -1) ? toNewY : currY;
            }
            else if (toNewY > currY){
                yStart = (orientation == -1) ? currY : toNewY;
            } else {
                return false;
            }

            xStart++;

            // y movement of a piece depends on the orientation of the move
            yStart+=orientation;

            // Loop to see if any piece is in between
            for(;xStart < xFinish; xStart++, yStart+=orientation){
                if (chessBoard.chessPieceAt(xStart, yStart) != null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
