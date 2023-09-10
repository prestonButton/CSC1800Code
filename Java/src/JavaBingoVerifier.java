import java.util.*;

public class JavaBingoVerifier {
    public static void main(String[] args) {
        //declare and initialize variables to be used
        int currRow;
        Scanner scan = new Scanner(System.in);
        int[][] card = new int[5][5];
        ArrayList<Integer> rowList = new ArrayList<>();
        ArrayList<Integer> columnList = new ArrayList<>();
        ArrayList<Integer> numsCalled = new ArrayList<>();
        int patternType = 0;
        String patternString;

        //fill array lists with row and column of pairs in winning pattern needed
        currRow = 0;
        while (currRow < 5) {
            patternString = scan.nextLine();
            String[] patTokens = patternString.split(" ");
            int currCol = 0;
            int currNum;
            for (String token : patTokens) {
                currNum = Integer.parseInt(token);
                if (currNum != 0) {
                    rowList.add(currRow);
                    columnList.add(currCol);
                    patternType = currNum;
                }
                currCol++;
            }
            currRow++;
        }

        scan.nextLine();

        //read in line of called bingo numbers
        String numCalledString;
        numCalledString = scan.nextLine();
        String[] numCalledTokens = numCalledString.split(" ");
        for (String token : numCalledTokens) {
            numsCalled.add(Integer.parseInt(token));
        }

        scan.nextLine();

        //fill 2d array of given bingo card
        currRow = 0;
        String cardString;
        while (currRow < 5) {
            cardString = scan.nextLine();
            String[] cardTokens = cardString.split(" ");
            int currCol = 0;
            int currNum;
            for (String token : cardTokens) {
                currNum = Integer.parseInt(token);
                card[currRow][currCol] = currNum;
                currCol++;
            }
            currRow++;
        }

        //check if pattern is straight, crazy, or nonexistent and test if the bingo card matches
        if (patternType == 1) {
            if (bingoTest(card, rowList, columnList, numsCalled)) {
                System.out.println("VALID BINGO");
            } else {
                System.out.println("NO BINGO");
            }
        } else if (patternType == 4) {
            boolean validCrazy = false;
            for (int i = 0; i < 4; i++) {
                if (bingoTest(card, rowList, columnList, numsCalled)) {
                    validCrazy = true;
                } else {
                    rotation(rowList, columnList);
                }
            }
            if (validCrazy) {
                System.out.println("VALID BINGO");
            } else {
                System.out.println("NO BINGO");
            }
        } else {
            System.out.println("NO BINGO");
        }
        scan.close();
    }

    //check to see if the bingo card meets the given pattern requirements
    static boolean bingoTest(int[][] card, ArrayList<Integer> rowList, ArrayList<Integer> columnList, ArrayList<Integer> numsCalled) {
        boolean validBingo = true;
        int currNum;
        boolean lastNumCalled = false;
        for (int i = 0; i < rowList.size(); i++) {
            currNum = card[rowList.get(i)][columnList.get(i)];
            if (!numsCalled.contains(currNum) && currNum != 0) {
                validBingo = false;
            }
            if (currNum == numsCalled.get(numsCalled.size()-1)) {
                lastNumCalled = true;
            }
            //else lastNumCalled = false;
        }
        if (!lastNumCalled) {
            validBingo = false;
        }
        return validBingo;
    }

    //rotates the winning pattern pairs for a crazy pattern
    static void rotation(ArrayList<Integer> rowList, ArrayList<Integer> columnList) {
        for (int j = 0; j < rowList.size(); j++) {
            int temp = columnList.get(j);
            columnList.set(j, (4 - rowList.get(j)));
            rowList.set(j, temp);
        }
    }
}