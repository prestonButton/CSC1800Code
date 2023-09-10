import java.util.Set;

public class BingoVerifier {
    private int[][] pattern;
    private Set<Integer> calledNumbers;
    private BingoCard bingoCard;

    public BingoVerifier(int[][] pattern, Set<Integer> calledNumbers, BingoCard bingoCard) {
        this.pattern = pattern;
        this.calledNumbers = calledNumbers;
        this.bingoCard = bingoCard;
    }

    public boolean isValidBingo() {
        String[][] outputPattern = new String[5][5];
        boolean isValid = true;

        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[i].length; j++) {
                int numberOnCard = bingoCard.getNumber(i, j);
                if (pattern[i][j] != 0) {
                    if (bingoCard.isNumberMarked(numberOnCard)) {
                        outputPattern[i][j] = String.valueOf(numberOnCard);
                    } else {
                        outputPattern[i][j] = "XX";
                        isValid = false;
                    }
                } else {
                    outputPattern[i][j] = "00";
                }
            }
        }

        // Print the output pattern
        for (int i = 0; i < outputPattern.length; i++) {
            for (int j = 0; j < outputPattern[i].length; j++) {
                System.out.print(outputPattern[i][j] + " ");
            }
            System.out.println();
        }

        return isValid && checkPattern();
    }

    private boolean checkPattern() {
        int lastCalledNumber = getLastCalledNumber();
        boolean isLastNumberInPattern = false;

        // Check if all the non-zero spots in the pattern are filled
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (pattern[i][j] != 0) {
                    int numberInCard = bingoCard.getNumber(i, j);
                    if (!calledNumbers.contains(numberInCard)) {
                        return false;
                    }
                    if (numberInCard == lastCalledNumber) {
                        isLastNumberInPattern = true;
                    }
                }
            }
        }

        return isLastNumberInPattern;
    }

    private int getLastCalledNumber() {
        int lastNumber = 0;
        for (int number : calledNumbers) {
            lastNumber = number;
        }
        return lastNumber;
    }
}