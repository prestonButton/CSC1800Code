import java.util.List;

public class BingoVerifier {
    private int[][] pattern;
    private List<Integer> calledNumbers;
    private BingoCard bingoCard;

    public BingoVerifier(int[][] pattern, List<Integer> calledNumbers, BingoCard bingoCard) {
        this.pattern = pattern;
        this.calledNumbers = calledNumbers;
        this.bingoCard = bingoCard;
    }

    public boolean isValidBingo() {
        // Mark the called numbers on the bingo card
        for (int number : calledNumbers) {
            bingoCard.markNumber(number);
        }

        // Check for a winning pattern
        boolean isBingo = true;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (pattern[i][j] == 1 && !bingoCard.isNumberMarked(pattern[i][j])) {
                    isBingo = false;
                    break;
                }
            }
            if (!isBingo) {
                break;
            }
        }

        // Check the last number called
        if (isBingo) {
            int lastNumberCalled = calledNumbers.get(calledNumbers.size() - 1);
            isBingo = bingoCard.isNumberMarked(lastNumberCalled);
        }

        return isBingo;
    }
}