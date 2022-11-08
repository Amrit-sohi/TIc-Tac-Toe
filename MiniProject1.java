import java.util.Random;
import java.util.Scanner;
// one player vs computer
public class MiniProject1 {
    public static void main(String[] args) {
        char board[][] = { { ' ', ' ', ' ' },{ ' ', ' ', ' ' },{ ' ', ' ', ' ' } };
        Scanner sc = new Scanner(System.in);
        while(true){
            // PlayerMove
            PlayerMove(board, sc);
            // Logic to check winner or isGameOver
            if(isGameFinished(board,'X')){
                break;
            } 
            // ComputerMove
            ComputerMove(board);
            if(isGameFinished(board,'0')){
                break;
            } 
            printBoard(board);
        }
        printBoard(board);
    }
    private static boolean checkWinner(char[][] board,char symbol){
        // Check  each row,each col and diagonals
        if(board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol ||
           board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol ||
           board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol ||

           board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol ||
           board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol ||
           board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol ||
           
           board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol ||
           board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol){
            return true;
        }
        return false;
    }
    private static boolean isGameFinished(char[][] board,char symbol){
        // Check  each row,each col and diagonals
        if(checkWinner(board,symbol)){
            if(symbol == 'X'){
                System.out.println("Player Wins");
                return true;
            }
            else if(symbol == '0'){
                System.out.println("Computr Wins");
                return true;
            }
        }
        // check if the board is full
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == ' '){
                    return false;
                }
            }
        }
        System.out.println("The Game is ended in tie");
        return true;
    }
    private static void ComputerMove(char[][] board) {
        Random rand = new Random();
        int computerMove;
        while (true) {
            computerMove = rand.nextInt(9) + 1;
            if (isValidMove(board, Integer.toString(computerMove))) {
                break;
            }
        }
        System.out.println("Computer chooses " + computerMove);
        placeMove(board,Integer.toString(computerMove),'0');
    }
    private static void placeMove(char[][] board,String position,char symbol){
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
        while(true){
            System.out.println();
            System.out.println("What's your move(1-9)");
            playerMove = sc.nextLine();
            if(isValidMove(board, playerMove)){
                break;
            }else{
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