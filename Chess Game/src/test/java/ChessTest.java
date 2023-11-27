import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChessTest {
    private static ChessGame chessGame;
    private static ChessBoard chessBoard;
    @BeforeEach
    public void setupTestGame() {
        chessGame = new ChessGame();
        chessBoard = chessGame.getChessBoard();
    }

    @Test
    public void boundCheckTest() {
        // Test Path: [1, 2] : within bound
        assertEquals(true, chessBoard.boundCheck(6, 7));

        // Test Path: [1, 3] : out of bound
        assertEquals(false, chessBoard.boundCheck(8, 7));
    }

    @Test
    public void checkKnightMovementTest() {
        Knight knight = chessGame.addKnight(4, 4, COLOR.WHITE);

        // Test Path: [1, 2] : knight is moving 2 step in x direction and 1 step in y direction
        assertEquals(true, knight.checkKnightMovement(6, 3));
        assertEquals(true, knight.checkKnightMovement(6, 5));
        assertEquals(true, knight.checkKnightMovement(2, 3));
        assertEquals(true, knight.checkKnightMovement(2, 5));

        // Test Path: [1, 3, 4] : knight is moving 2 step in y direction and 1 step in x direction
        assertEquals(true, knight.checkKnightMovement(3, 2));
        assertEquals(true, knight.checkKnightMovement(5, 2));
        assertEquals(true, knight.checkKnightMovement(3, 6));
        assertEquals(true, knight.checkKnightMovement(3, 6));

        // Test Path: [1, 3, 5] : invalid move for a knight piece
        assertEquals(false, knight.checkKnightMovement(5, 5));
    }

    @Test
    public void checkPawnMovementTest() {
        Pawn whitePawn1 = chessGame.addPawn(0, 6, COLOR.WHITE);
        Pawn blackPawn1 = chessGame.addPawn(0, 1, COLOR.BLACK);

        // Test Path: [1, 2, 4, 5, 7] : this is for black pawn moving 1 step forward and no piece at target location
        assertEquals(true, blackPawn1.checkPawnMovement(0, 2));

        // Test Path: [1, 3, 4, 5, 7] : this is for white pawn moving 1 step forward and no piece at target location
        assertEquals(true, whitePawn1.checkPawnMovement(0, 5));

        // Test Path: [1, 2, 4, 6, 10] : this is the black pawn with hasMoved == true, and it is trying to move 2 step forward
        blackPawn1.moveTo(0, 2); // moved to 0,2
        assertEquals(false, blackPawn1.checkPawnMovement(0, 4)); // check movement from 0,2 to 0,4

        // Test Path: [1, 3, 4, 6, 10] : this is the white pawn with hasMoved == true, and it is trying to move 2 step forward
        whitePawn1.moveTo(0, 5); // moved to 0,5
        assertEquals(false, whitePawn1.checkPawnMovement(0, 3)); // check movement from 0,5 to 0,3


        // Test Path: [1, 2, 4, 5, 8, 9] // if there is any opponent's piece diagonal to black pawn
        Queen whiteQueen = chessGame.addQueen(1, 3, COLOR.WHITE);
        assertEquals(true, blackPawn1.checkPawnMovement(1, 3));
        chessBoard.capturePiece(whiteQueen);

        // Test Path: [1, 3, 4, 5, 8, 9] // if there is any opponent's piece diagonal to white pawn
        Queen blackQueen = chessGame.addQueen(1, 4, COLOR.BLACK);
        assertEquals(true, whitePawn1.checkPawnMovement(1, 4));
        chessBoard.capturePiece(blackQueen);

        // Test Path: [1, 2, 4, 5, 8, 10]
        // black pawn wants to move diagonal but there is no piece in that location
        assertEquals(false, blackPawn1.checkPawnMovement(1, 3));
        // black pawn moves more than 1 step in horizontal direction
        assertEquals(false, blackPawn1.checkPawnMovement(2, 3));


        // Test Path: [1, 3, 4, 5, 8, 10]
        // white pawn wants to move diagonal but there is no piece in that location
        assertEquals(false, whitePawn1.checkPawnMovement(1, 4));
        // white pawn moves more than 1 step in horizontal direction
        assertEquals(false, whitePawn1.checkPawnMovement(2, 4));


        // Test Path: [1, 2, 4, 6, 11, 10] // black pawn wants to move its first move with more than two steps
        Pawn blackPawn2 = chessGame.addPawn(5, 1, COLOR.BLACK);
        assertEquals(false, blackPawn2.checkPawnMovement(5, 4));

        // Test Path: [1, 3, 4, 6, 11, 10] // white pawn wants to move its first move with more than two steps
        Pawn whitePawn2 = chessGame.addPawn(7, 6, COLOR.WHITE);
        assertEquals(false, whitePawn2.checkPawnMovement(7, 3));

        // Test Path: [1, 2, 4, 6, 11, 13, 10]
        // black pawn moves diagonal 2 steps
        assertEquals(false, blackPawn2.checkPawnMovement(4, 3));
        // black pawn moves 2 steps with an opponent piece in between
        Rook whiteRook = chessGame.addRook(5, 2, COLOR.WHITE);
        assertEquals(false, blackPawn2.checkPawnMovement(5, 3));
        // black pawn moves 2 steps with opponent piece in that location
        whiteRook.moveTo(5, 3);
        assertEquals(false, blackPawn2.checkPawnMovement(5, 3));

        // Test Path: [1, 3, 4, 6, 11, 13, 10]
        // white pawn moves diagonal 2 steps
        assertEquals(false, whitePawn2.checkPawnMovement(6, 4));
        // white pawn moves 2 steps with an opponent piece in between
        Rook blackRook = chessGame.addRook(7, 5, COLOR.BLACK);
        assertEquals(false, whitePawn2.checkPawnMovement(7, 4));
        // white pawn moves 2 steps with opponent piece in that location
        whiteRook.moveTo(7, 4);
        assertEquals(false, whitePawn2.checkPawnMovement(7, 4));
        chessBoard.capturePiece(blackRook);
        chessBoard.capturePiece(whiteRook);


        // Test Path: [1, 2, 4, 6, 11, 13, 15] : black pawn moves two-step forward
        assertEquals(true, blackPawn2.checkPawnMovement(5, 3));

        // Test Path: [1, 3, 4, 6, 11, 13, 15]: white pawn moves two-step forward
        assertEquals(true, whitePawn2.checkPawnMovement(7, 4));
    }

    @Test
    public void checkStraightMovementTest() {
        Rook blackRook = chessGame.addRook(6,2,COLOR.BLACK);
        Rook whiteRook = chessGame.addRook(2,6,COLOR.WHITE);

        // Test Path: [1, 3, 15] : Rook does not move straight
        assertEquals(false, blackRook.checkStraightMovement(7,4));

        // Test Path: [1, 2, 5, 7] : Rook moves to same location it is already at
        assertEquals(false, blackRook.checkStraightMovement(6,2));

        // Test Path: [1,2,4,8,9,11] : Rook moves one position vertically straight above
        assertEquals(true, whiteRook.checkStraightMovement(2,5));

        // Test Path: [1,2,5,6,8,9,11] : Rook moves one position vertically straight down
        assertEquals(true, blackRook.checkStraightMovement(6,6));

        // Test Path: [1,2,4,8,9,10,12] : There is opponent piece right below the rook
        Queen whiteQueen = chessGame.addQueen(6,3,COLOR.WHITE);
        assertEquals(false, blackRook.checkStraightMovement(6,6));
        chessBoard.capturePiece(whiteQueen);

        // Test Path: [1,2,5,6,8,9,10,12] : There is opponent piece right above the rook
        Queen blackQueen = chessGame.addQueen(2,5,COLOR.BLACK);
        assertEquals(false, whiteRook.checkStraightMovement(2,4));
        chessBoard.capturePiece(blackQueen);

        // Test Path: [1,2,5,6,8,9,10,13,9,11] : black Rook moves two positions straight down
        assertEquals(true, blackRook.checkStraightMovement(6, 4));

        // Test Path: [1,2,4,8,9,10,13,9,11]  : white Rook moves two positions straight up
        assertEquals(true, whiteRook.checkStraightMovement(2, 4));

        // Test Path: [1,2,4,8,9,10,13,9,10,12] : white rook moves up more than two position but finds opponents piece in its way
        blackQueen = chessGame.addQueen(2,4,COLOR.BLACK);
        assertEquals(false, whiteRook.checkStraightMovement(2, 3));
        chessBoard.capturePiece(blackQueen);

        // Test Path: [1,2,4,8,9,10,13,9,10,13,9,11] : whiteRook moves more than two position straight up
        assertEquals(true, whiteRook.checkStraightMovement(2, 3));

        // moving the white and black to test for horizontal moves
        blackRook.moveTo(4, 2);
        whiteRook.moveTo(4, 5);

        // Test Path: [1,3,14,16,20,21,25] : Rook moves one position horizontally straight right
        assertEquals(true, blackRook.checkStraightMovement(5, 2));


        // Test Path: [1,3,14,17,18,20,21,25] : Rook moves one position horizontally straight left
        assertEquals(true, blackRook.checkStraightMovement(3, 2));

        // Test Path: [1,3,14,16,20,21,22,23] : there is opponent piece just right side
        whiteQueen = chessGame.addQueen(5, 2, COLOR.WHITE);
        assertEquals(false, blackRook.checkStraightMovement(6, 2));

        // Test Path: 1,3,14,17,18,20,21,22,23] : there is opponent piece just left side
        whiteQueen.moveTo(3, 2);
        assertEquals(false, blackRook.checkStraightMovement(2, 2));

        // Test Path: [1,3,14,16,20,21,22,24,21,25]: black rook moves right more than two position but finds opponents piece in its way
        whiteQueen.moveTo(6, 2);
        assertEquals(false, blackRook.checkStraightMovement(7, 2));

        // Test Path: [1,3,14,17,18,20,21,22,24,21,25]: black rook moves left more than two position but finds opponents piece in its way
        whiteQueen.moveTo(2, 2);
        assertEquals(false, blackRook.checkStraightMovement(1, 2));
        chessBoard.capturePiece(whiteQueen);

        // Test Path: [1,3,14,16,20,21,22,24,21,22,24,21,25] : whiteRook moves more than two position straight up
        assertEquals(true, blackRook.checkStraightMovement(0, 2));
    }

    @Test
    public void checkDiagonalMovementTest() {
        Queen blackQueen = chessGame.addQueen(4, 4, COLOR.BLACK);

        // TestPath: [1, 3]: queen moves non-diagonal
        assertEquals(false, blackQueen.checkDiagonalMovement(4, 6));

        // Test Path: [1, 2, 4, 8, 9, 13, 14, 18, 19, 21, 23, 19, 20] : queen moves diagonally from 4, 4 to 1, 1, with no opponent's piece on the path
        assertEquals(true, blackQueen.checkDiagonalMovement(1, 1));

        // Test Path: [1, 2, 4, 8, 9, 13, 14, 18, 19, 21, 23, 19, 21, 22] : queen moves diagonally from 4, 4 to 1, 1, with opponent's piece on the path
        Pawn whitePawn = chessGame.addPawn(2, 2, COLOR.WHITE);
        assertEquals(false, blackQueen.checkDiagonalMovement(1, 1));

        // Test Path: [1, 2, 4, 8, 9, 13, 14, 18, 19, 21, 22] : queen moves diagonally from 4, 4 to 1, 1, with opponent's piece is at 3, 3
        whitePawn.moveTo(3, 3);
        assertEquals(false, blackQueen.checkDiagonalMovement(1, 1));
    }

    @Test
    public void isKingInCheckTest() {
        King blacKing = chessGame.addKing(3, 2, COLOR.BLACK);

        // TestPath: [1, 2, 4]: no opponent's piece to give on the king
        assertEquals(false, chessGame.isKingInCheck(COLOR.BLACK));


        // TestPath: [1, 2, 3, 5]: white queen gives check to the black king
        Queen whiteQueen = chessGame.addQueen(5, 4, COLOR.WHITE);
        assertEquals(true, chessGame.isKingInCheck(COLOR.BLACK));
        chessBoard.capturePiece(whiteQueen);

        // TestPath: [1, 2, 3, 6, 2, 4]: checking for check with more than two opponent piece, no check
        Pawn whitePawn1 = chessGame.addPawn(6, 6, COLOR.WHITE);
        Pawn whitePawn2 = chessGame.addPawn(5, 6, COLOR.WHITE);
        assertEquals(false, chessGame.isKingInCheck(COLOR.BLACK));
    }

    @Test
    public void isCheckMateTest() {
        King blackKing = chessGame.addKing(7, 7, COLOR.BLACK);
        Queen whiteQueen = chessGame.addQueen(5, 7, COLOR.WHITE);
        Queen whiteRook = chessGame.addQueen(7, 5, COLOR.WHITE);

        // TestPath: [1, 2]: checkmate
        assertEquals(true, chessGame.isCheckMate(COLOR.BLACK));

        // TestPath: [1, 3]: no checkmate
        chessBoard.capturePiece(whiteRook); // removing whiteRook to remove the checkmate on the king
        assertEquals(false, chessGame.isCheckMate(COLOR.BLACK));
    }

    @Test
    public void isGameOverTest() {
        // TestPath: [1 3 8] : no checkmate, no game over
        chessGame.setCurrentPlayer(COLOR.BLACK);
        King blackKing = chessGame.addKing(7, 7, COLOR.BLACK);
        Queen whiteQueen = chessGame.addQueen(5, 7, COLOR.WHITE);
        assertEquals(false, chessGame.isGameOver());

        // TestPath: [1 3 7] : stalemate
        Rook whiteRook = chessGame.addRook(6, 6, COLOR.WHITE);
        assertEquals(true, chessGame.isGameOver());
    }


}
