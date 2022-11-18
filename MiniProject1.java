import java.util.Random;
import java.util.Scanner;
// one player vs computer
public class MiniProject1 {
    public static void main(String[] args) {
        char board[][] = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };
        Scanner sc = new Scanner(System.in);
        while (true) {
            // PlayerMove
            PlayerMove(board, sc);
            // Logic to check winner or isGameOver
            if (isGameFinished(board, 'X')) {
                break;
            }
            // ComputerMove
            // ComputerMove(board);
            ComputerMove(board);
            if (isGameFinished(board, '0')) {
                break;
            }
            printBoard(board);
        }
        printBoard(board);
    }

    private static boolean checkWinner(char[][] board, char symbol) {
        // Check each row,each col and diagonals
        if (board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol ||
                board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol ||
                board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol ||

                board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol ||
                board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol ||
                board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol ||

                board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol ||
                board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true;
        }
        return false;
    }

    private static boolean isGameFinished(char[][] board, char symbol) {
        // Check each row,each col and diagonals
        if (checkWinner(board, symbol)) {
            if (symbol == 'X') {
                System.out.println("--------------");
                System.out.println("Player Wins");
                System.out.println("--------------");
                return true;
            } else if (symbol == '0') {
                System.out.println("--------------");
                System.out.println("Computer Wins");
                System.out.println("--------------");
                return true;
            }
        }
        // check if the board is full
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        System.out.println("The Game is ended in tie");
        return true;
    }

    private static boolean rowWise(char[][] board, int[][] place, char symbol) {
        int count = 0;
        int Move = 0;
        for (int i = 0; i < board.length; i++) {
            count = 0;
            Move = 0;
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == symbol) {
                    count++;
                } else if (board[i][j] == ' ') {
                    Move = place[i][j];
                }
            }
            if (count == 2 && Move != 0) {
                break;
            }
        }
        if (count == 2 && Move != 0) {
            // System.out.println("Row Wise");
            // System.out.println("Computer Chooses " + Move);
            placeMove(board, Integer.toString(Move), '0');
            return true;
        }
        return false;
    }

    private static boolean colWise(char[][] board, int[][] place, char symbol) {
        int count = 0;
        int Move = 0;
        for (int i = 0; i < board.length; i++) {
            count = 0;
            Move = 0;
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == symbol) {
                    count++;
                } else if (board[j][i] == ' ') {
                    Move = place[j][i];
                }
            }
            if (count == 2 && Move != 0) {
                break;
            }
        }
        if (count == 2 && Move != 0) {
            // System.out.println("Col Wise");
            // System.out.println("Computer Chooses " + Move);
            placeMove(board, Integer.toString(Move), '0');
            return true;
        }
        return false;
    }

    private static boolean isLeftDiagonal(char[][] board, int[][] place, char symbol) {
        // left Daigonal
        int count = 0;
        int Move = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][i] == symbol) {
                count++;
            } else if (board[i][i] == ' ') {
                Move = place[i][i];
            }
        }
        if (count == 2 && Move != 0) {
            // System.out.println("LD Wise");
            // System.out.println("Computer Chooses " + Move);
            placeMove(board, Integer.toString(Move), '0');
            return true;
        }
        return false;
    }

    private static boolean rightDiagonal(char[][] board, int[][] place, char symbol) {
        int count = 0;
        int Move = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (i + j == board.length - 1) {
                    if (board[i][j] == symbol) {
                        count++;
                    } else if (board[i][j] == ' ') {
                        Move = place[i][j];
                    }
                }
                if (count == 2 && Move != 0) {
                    break;
                }
            }
        }
        if (count == 2 && Move != 0) {
            // System.out.println("RD Wise");
            // System.out.println("Computer Chooses " + Move);
            placeMove(board, Integer.toString(Move), '0');
            return true;
        }
        return false;
    }

    private static boolean isDefend(char[][] board, int[][] place) {
        if (isLeftDiagonal(board, place, 'X')) {
            return true;
        } else if (rightDiagonal(board, place, 'X')) {
            return true;
        } else if (rowWise(board, place, 'X')) {
            return true;
        } else if (colWise(board, place, 'X')) {
            return true;
        }
        return false;
    }

    private static boolean isAttack(char[][] board, int[][] place) {
        if (isLeftDiagonal(board, place, '0')) {
            return true;
        } else if (rightDiagonal(board, place, '0')) {
            return true;
        } else if (rowWise(board, place, '0')) {
            return true;
        } else if (colWise(board, place, '0')) {
            return true;
        }
        return false;
    }

    private static void ComputerMove(char[][] board) {
        int place[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        if (isAttack(board, place)) {
            // System.out.println("Attack");
            return;
        } else if (isDefend(board, place)) {
            // System.out.println("Defend");
            return;
        }

        ComputerRandMove(board);
    }

    private static void ComputerRandMove(char[][] board) {
        Random rand = new Random();
        int computerMove;
        while (true) {
            computerMove = rand.nextInt(9) + 1;
            if (isValidMove(board, Integer.toString(computerMove))) {
                break;
            }
        }
        System.out.println("Random Move");
        System.out.println("Computer chooses " + computerMove);
        placeMove(board, Integer.toString(computerMove), '0');
    }

    private static void placeMove(char[][] board, String position, char symbol) {
        switch (position) {
            case "1":
                board[0][0] = symbol;
                break;
            case "2":
                board[0][1] = symbol;
                break;
            case "3":
                board[0][2] = symbol;
                break;
            case "4":
                board[1][0] = symbol;
                break;
            case "5":
                board[1][1] = symbol;
                break;
            case "6":
                board[1][2] = symbol;
                break;
            case "7":
                board[2][0] = symbol;
                break;
            case "8":
                board[2][1] = symbol;
                break;
            case "9":
                board[2][2] = symbol;
                break;
            default:
                System.out.println("Not a Valid Move");
        }
    }

    private static boolean isValidMove(char[][] board, String Move) {
        switch (Move) {
            case "1":
                return board[0][0] == ' ';
            case "2":
                return board[0][1] == ' ';
            case "3":
                return board[0][2] == ' ';
            case "4":
                return board[1][0] == ' ';
            case "5":
                return board[1][1] == ' ';
            case "6":
                return board[1][2] == ' ';
            case "7":
                return board[2][0] == ' ';
            case "8":
                return board[2][1] == ' ';
            case "9":
                return board[2][2] == ' ';
            default:
                return false;
        }
    }

    private static void PlayerMove(char[][] board, Scanner sc) {
        String playerMove;
        while (true) {
            System.out.println();
            System.out.println("What's your move(1-9)");
            playerMove = sc.nextLine();
            if (isValidMove(board, playerMove)) {
                break;
            } else {
                System.out.println("Not a Valid Move,Try again");
            }
        }
        placeMove(board, playerMove, 'X');
    }

    private static void printBoard(char[][] board) {
        System.out.print(board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println();
        System.out.println("--+---+--");
        System.out.print(board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println();
        System.out.println("--+---+--");
        System.out.print(board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
    }
}