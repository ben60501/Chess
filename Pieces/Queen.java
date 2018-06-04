package Pieces;

import Chess.Chess;

import java.util.ArrayList;

public class Queen extends GamePiece {
    private ArrayList<int[]> possibleMoves;

    public Queen(int row, int column, Color pieceColor, boolean isSelected) {
        super(row, column, pieceColor, isSelected);

        possibleMoves = new ArrayList<>();
    }


    public ArrayList<int[]> getPossibleMoves() {
        possibleMoves = new ArrayList<>();

        //Gets all of the row and column moves
        ArrayList<int[]> rowMoves = findMovesInRow();
        ArrayList<int[]> colMoves = findMovesInCol();
        ArrayList<int[]> diagonalMoves = findDiagonalMoves();

        //Adds all of the moves to possible moves
        possibleMoves.addAll(rowMoves);
        possibleMoves.addAll(colMoves);
        possibleMoves.addAll(diagonalMoves);

        return possibleMoves;
    }


    private ArrayList<int[]> findDiagonalMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        int irow = row;
        int icolumn = column;
        // bottom right diagonal
        while(irow <= 6 && icolumn <= 6){
            irow++;
            icolumn++;
            if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == -1){
                icolumn = 7;
            }else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == 1){
                icolumn = 7;
            }else if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == 1){
                moves.add(new int[]{irow, icolumn});
                icolumn = 7;
            }else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == -1) {
                moves.add(new int[]{irow, icolumn});
                icolumn = 7;
            }else
                moves.add(new int[]{irow, icolumn});
        }
        // top left diagonal
        irow = row;
        icolumn = column;
        while(irow >= 1 && icolumn >= 1){
            irow--;
            icolumn--;
            if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == -1){
                icolumn = 0;
            }else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == 1){
                icolumn = 0;
            }else if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == 1){
                moves.add(new int[]{irow, icolumn});
                icolumn = 0;
            }else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == -1) {
                moves.add(new int[]{irow, icolumn});
                icolumn = 0;
            }else
                moves.add(new int[]{irow, icolumn});
        }
        // bottom left diagonal
        irow = row;
        icolumn = column;
        while(irow <= 6 && icolumn >= 1){
            irow++;
            icolumn--;
            if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == -1){
                icolumn = 0;
            }
            else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == 1){
                icolumn = 0;
            }else if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == 1){
                moves.add(new int[]{irow, icolumn});
                icolumn = 0;
            }else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == -1) {
                moves.add(new int[]{irow, icolumn});
                icolumn = 0;
            }else
                moves.add(new int[]{irow, icolumn});
        }
        // top right diagonal
        irow = row;
        icolumn = column;
        while(irow >= 1 && icolumn <= 6){
            irow--;
            icolumn++;
            if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == -1){
                icolumn = 7;
            }
            else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == 1){
                icolumn = 7;
            }else if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == 1){
                moves.add(new int[]{irow, icolumn});
                icolumn = 7;
            }else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == -1) {
                moves.add(new int[]{irow, icolumn});
                icolumn = 7;
            }else
                moves.add(new int[]{irow, icolumn});
        }
        return moves;
    }


    private ArrayList<int[]> findMovesInRow() {
        //Stores all of the moves in the row
        ArrayList<int[]> moves = new ArrayList<>();
        boolean shouldLoop = true;

        //Finds moves to the left of the queen
        for (int column = this.column - 1; column >= 0; column--) {
            if (shouldLoop) {
                if (this.pieceColor == GamePiece.Color.Black) {
                    if (Chess.chessBoard[this.row][column] == 0) {
                        moves.add(new int[] {this.row, column});
                    } else if (Chess.chessBoard[this.row][column] == -1) {
                        moves.add(new int[] {this.row, column});
                        shouldLoop = false;
                    } else {
                        shouldLoop = false;
                    }

                } else if (this.pieceColor == GamePiece.Color.Red) {
                    if (Chess.chessBoard[this.row][column] == 0) {
                        moves.add(new int[] {this.row, column});
                    } else if (Chess.chessBoard[this.row][column] == 1) {
                        moves.add(new int[] {this.row, column});
                        shouldLoop = false;
                    } else {
                        shouldLoop = false;
                    }
                }
            }
        }

        shouldLoop = true;

        //Finds moves to the right of the queen
        for (int column = this.column + 1; column < 8; column++) {
            if (shouldLoop) {
                if (this.pieceColor == GamePiece.Color.Black) {
                    if (Chess.chessBoard[this.row][column] == 0) {
                        moves.add(new int[] {this.row, column});
                    } else if (Chess.chessBoard[this.row][column] == -1) {
                        moves.add(new int[] {this.row, column});
                        shouldLoop = false;
                    } else {
                        shouldLoop = false;
                    }

                } else if (this.pieceColor == GamePiece.Color.Red) {
                    if (Chess.chessBoard[this.row][column] == 0) {
                        moves.add(new int[] {this.row, column});
                    } else if (Chess.chessBoard[this.row][column] == 1) {
                        moves.add(new int[] {this.row, column});
                        shouldLoop = false;
                    } else {
                        shouldLoop = false;
                    }
                }
            }
        }


        return moves;
    }


    private ArrayList<int[]> findMovesInCol() {
        //Stores all of the moves in the column
        ArrayList<int[]> moves = new ArrayList<>();
        boolean shouldLoop = true;

        //Find moves above the queen
        for (int row = this.row - 1; row >= 0; row--) {
            if (shouldLoop) {
                if (this.pieceColor == GamePiece.Color.Black) {
                    if (Chess.chessBoard[row][this.column] == 0) {
                        moves.add(new int[] {row, this.column});
                    } else if (Chess.chessBoard[row][this.column] == -1) {
                        moves.add(new int[] {row, this.column});
                        shouldLoop = false;
                    } else {
                        shouldLoop = false;
                    }

                } else if (this.pieceColor == GamePiece.Color.Red) {
                    if (Chess.chessBoard[row][this.column] == 0) {
                        moves.add(new int[] {row, this.column});
                    } else if (Chess.chessBoard[row][this.column] == 1) {
                        moves.add(new int[] {row, this.column});
                        shouldLoop = false;
                    } else {
                        shouldLoop = false;
                    }
                }
            }
        }

        shouldLoop = true;

        //Finds moves below the queen
        for (int row = this.row + 1; row < 8; row++) {
            if (shouldLoop) {
                if (this.pieceColor == GamePiece.Color.Black) {
                    if (Chess.chessBoard[row][this.column] == 0) {
                        moves.add(new int[]{row, this.column});
                    } else if (Chess.chessBoard[row][this.column] == -1) {
                        moves.add(new int[]{row, this.column});
                        shouldLoop = false;
                    } else {
                        shouldLoop = false;
                    }

                } else if (this.pieceColor == GamePiece.Color.Red) {
                    if (Chess.chessBoard[row][this.column] == 0) {
                        moves.add(new int[]{row, this.column});
                    } else if (Chess.chessBoard[row][this.column] == 1) {
                        moves.add(new int[]{row, this.column});
                        shouldLoop = false;
                    } else {
                        shouldLoop = false;
                    }
                }
            }
        }

        return moves;
    }

    public void moveToCoordinate(int row, int column) {
        Chess.chessBoard[this.row][this.column] = 0;
        ArrayList<GamePiece> temp = new ArrayList<>();

        for (GamePiece piece: Chess.pieces) {
            if (!(piece.getRow() == row && piece.getColumn() == column)) {
                temp.add(piece);
            }
        }

        Chess.pieces = temp;

        if (this.pieceColor == GamePiece.Color.Black) {
            Chess.chessBoard[row][column] = 1;
        } else {
            Chess.chessBoard[row][column] = -1;
        }

        this.row = row;
        this.column = column;
    }
}