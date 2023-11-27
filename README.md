
# Dataflow Graph based Testing

Data-flow graph (DFG) is a graph that represents control flow of a function in which every node is labeled with definitions(def) and uses(use) of variables in that basic block.

# Chess Game

## About our Project Code:
We have created a chess game in java language. It can be played by two members. After every move the game will check for gameover condition, which checks for checkmate or stalemate.

The codebase we have used in for a command-line chess application written in 
Java.

**Chess game files:**

 Bishop.java

 ChessBoard.java

 ChessGame.java

 ChessPiece.java

 ChessUtil.java

 COLOR.Java

 King.java

 Knight.java

 Main.java

 Pawn.java

 Queen.java

 Rook.java

## Testing

The ChessTest.java contains the test cases for all the functions we have tested. It contains functions with nested ifs and conditional loops. We have tested the methods for the piece movement and also checked for the game conditions like CHECKMATE, STALEMATE.


We have also tested the movement methods of the pieces like straight movement and diagonal movements.


We have mentioned All DU Path Coverage along with All Def Coverage. Since All-Def is subsumed by All-DU we have considered  All DU Path Coverage to create test cases.


## Contributions

- **Jayaa Shree Laxmi**: https://github.com/jayashree-1998/Software-Testing-DFG.

    Drew DFG for checkStraightMovement, checkDiagonalMovement, isGameOver, checkPawnMovement.
    
    Designed JUnit tests for the above functions.
    
    Cross-verified other functions


- **Shashidhar Nagaral**: https://github.com/ShashidharNagaral/Software-Testing-DFG

    
    Drew CFG for boundCheck, checkKnightMovement, isKingInCheck, isCheckMate, 
    
    Designed JUnit tests for the above functions.
    
    Cross-verified other functions

## Project Members

- [Jayaa Shree Laxmi K (MT2022053)](https://github.com/jayashree-1998)
- [Shashidhar Nagaral (MT2022108)](https://github.com/ShashidharNagaral)

