import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("In this game you have to put 4 blocks in a row(horizontally, vertically, diagonally) to win");
        char[][] gameField = new char[9][9];
        Scanner key = new Scanner(System.in);
        showCharArray(gameField);
        boolean player1Move = true;
        while (winCondition(gameField) < 4) {
            System.out.println("\nChoose X coordinate");
            int blockX = key.nextInt() - 1;
            System.out.println("Choose Y coordinate");
            int blockY = key.nextInt();
            if (gameField[gameField.length - blockY][blockX] == '\u0000' && (blockY == 1 || gameField[gameField.length - blockY + 1][blockX] != '\u0000')) {
                if (player1Move) {
                    gameField[gameField.length - blockY][blockX] = 'X';
                } else {
                    gameField[gameField.length - blockY][blockX] = '0';
                }
                player1Move = !player1Move;
                showCharArray(gameField);
            } else {
                showCharArray(gameField);
                System.out.println("\nYou can't place block here");
            }
        }
        System.out.println("\nGame Over!");
        key.close();
    }

    public static int winCondition(char[][] arr) {
        int hCheckerX = 0;
        int vCheckerX = 0;
        int dCheckerX = 0;
        int hChecker0 = 0;
        int vChecker0 = 0;
        int dChecker0 = 0;
        int maxInRow = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int k = 0; k < arr[0].length; k++) {
                if (arr[i][k] == 'X') {
                    hCheckerX++;
                    if (hCheckerX > maxInRow) {
                        maxInRow = hCheckerX;
                    }
                } else if (arr[i][k] == '0') {
                    hChecker0++;
                    if (hChecker0 > maxInRow) {
                        maxInRow = hChecker0;
                    }
                } else {
                    hCheckerX = 0;
                    hChecker0 = 0;
                }
                if (arr[k][i] == 'X') {
                    vCheckerX++;
                    if (vCheckerX > maxInRow) {
                        maxInRow = vCheckerX;
                    }
                } else if (arr[k][i] == '0') {
                    vChecker0++;
                    if (vChecker0 > maxInRow) {
                        maxInRow = vChecker0;
                    }
                } else {
                    vCheckerX = 0;
                    vChecker0 = 0;
                }
            }
        }
        int tempI;
        //Тут я провел специфическую проверку, так как есть смысл проверять диагонали только там где
        //можно собрать 4 клетки
        for (int i = arr.length - 4; i >= 0; i--) {
            tempI = i;
            for (int k = 0; k < arr[0].length; k++) {
                if (arr[i][k] == 'X' || arr[k][i] == 'X') {
                    dCheckerX++;
                    if (dCheckerX > maxInRow) {
                        maxInRow = dCheckerX;
                    }
                } else if (arr[i][k] == '0' || arr[k][i] == '0') {
                    dChecker0++;
                    if (dChecker0 > maxInRow) {
                        maxInRow = dChecker0;
                    }
                } else {
                    dCheckerX = 0;
                    dChecker0 = 0;
                }
                if (i < arr.length - 1) {
                    i++;
                } else {
                    break;
                }
            }
            i = tempI;
        }
        return maxInRow;
    }

    public static void showCharArray(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print((arr.length - i) + " ");
            for (int k = 0; k < arr[0].length; k++) {
                System.out.print(arr[i][k] + " ");
            }
            System.out.println();
        }
        System.out.print(" ");
        for (int i = 0; i < arr[0].length; i++) {
            System.out.print(" " + (i + 1));
        }
    }
}