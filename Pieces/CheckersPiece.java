package Pieces;

import Checkers.Checkers;
import Pieces.GamePiece;

import java.util.ArrayList;


public class CheckersPiece extends GamePiece {

    private boolean isQueen;
    private ArrayList<int[]> possibleMoves; 
    
    public CheckersPiece(int row, int column, GamePiece.Color pieceColor, boolean isSelected, boolean isQueen) {
        super(row, column, pieceColor, isSelected);
        this.isQueen = isQueen;
        possibleMoves = new ArrayList<>();
    }

    public boolean isQueen() {
        return isQueen;
    }

    public void changeToQueen() {
        this.isQueen = true;
    }
    
    public ArrayList<int[]> getPossibleMoves() {
        possibleMoves = new ArrayList<>();

        int leftUp = -10;
        int rightUp = -10;
        int leftDown = -10;
        int rightDown = -10;

        if (row - 1 >= 0 && column - 1 >= 0) {
            leftUp = Checkers.board[row - 1][column - 1];
        }

        if (row - 1 >= 0 && column + 1 <= 7) {
            rightUp = Checkers.board[row - 1][column + 1];
        }

        if (row + 1 <= 7 && column - 1 >= 0) {
            leftDown = Checkers.board[row + 1][column - 1];
        }

        if (row + 1 <= 7 && column + 1 <= 7) {
            rightDown = Checkers.board[row + 1][column + 1];
        }

        if (getPieceColor() == GamePiece.Color.Black) {
            if (leftUp == 0) {
                possibleMoves.add(new int[] {row - 1, column - 1});
            } else if (leftUp == -1) {
                if (row - 2 >= 0 && column - 2 >= 0) {
                    if (Checkers.board[row - 2][column - 2] == 0) {
                        possibleMoves.add(new int[] {row - 2, column - 2});
                    }
                }
            }

            if (rightUp == 0) {
                possibleMoves.add(new int[] {row - 1, column + 1});
            } else if (rightUp == -1) {
                if (row - 2 >= 0 && column + 2 <= 7) {
                    if (Checkers.board[row - 2][column + 2] == 0) {
                        possibleMoves.add(new int[] {row - 2, column + 2});
                    }
                }
            }

            if (isQueen) {
                if (leftDown == 0) {
                    possibleMoves.add(new int[] {row + 1, column - 1});
                } else if (leftDown == -1) {
                    if (row + 2 <= 7 && column - 2 >= 0) {
                        if (Checkers.board[row + 2][column - 2] == 0) {
                            possibleMoves.add(new int[] {row + 2, column - 2});
                        }
                    }
                }

                if (rightDown == 0) {
                    possibleMoves.add(new int[] {row + 1, column + 1});
                } else if (rightDown == -1) {
                    if (row + 2 <= 7 && column + 2 <= 7) {
                        if (Checkers.board[row + 2][column + 2] == 0) {
                            possibleMoves.add(new int[] {row + 2, column + 2});
                        }
                    }
                }

            }
        } else if (getPieceColor() == GamePiece.Color.Red) {
            if (leftDown == 0) {
                possibleMoves.add(new int[] {row + 1, column - 1});
            } else if (leftDown == 1) {
                if (row + 2 <= 7 && column - 2 >= 0) {
                    if (Checkers.board[row + 2][column - 2] == 0) {
                        possibleMoves.add(new int[] {row + 2, column - 2});
                    }
                }
            }

            if (rightDown == 0) {
                possibleMoves.add(new int[] {row + 1, column + 1});
            } else if (rightDown == 1) {
                if (row + 2 <= 7 && column + 2 <= 7) {
                    if (Checkers.board[row + 2][column + 2] == 0) {
                        possibleMoves.add(new int[] {row + 2, column + 2});
                    }
                }
            }

            if (isQueen) {
                if (leftUp == 0) {
                    possibleMoves.add(new int[] {row - 1, column - 1});
                } else if (leftUp == 1) {
                    if (row - 2 >= 0 && column - 2 >= 0) {
                        if (Checkers.board[row - 2][column - 2] == 0) {
                            possibleMoves.add(new int[] {row - 2, column - 2});
                        }
                    }
                }

                if (rightUp == 0) {
                    possibleMoves.add(new int[] {row - 1, column + 1});
                } else if (rightUp == 1) {
                    if (row - 2 >= 0 && column + 2 <= 7) {
                        if (Checkers.board[row - 2][column + 2] == 0) {
                            possibleMoves.add(new int[] {row - 2, column + 2});
                        }
                    }
                }
            }
        }
        
        return possibleMoves;
    }
}
