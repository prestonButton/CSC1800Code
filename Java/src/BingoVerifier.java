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
        // If the non-zero number in the pattern is 4, rotate the card and check for a match
        if (pattern[0][0] == 4) {
            for (int i = 0; i < 4; i++) {
                if (isPatternMatch()) {
                    return true;
                }
                bingoCard.rotateClockwise();
            }
            return false;
        }

        // If the non-zero number in the pattern is 1, check for a match without rotating the card
        return isPatternMatch();
    }

    private boolean isPatternMatch() {
        // Check if all the non-zero spots in the pattern are filled
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (pattern[i][j] != 0 && !bingoCard.isNumberMarked(pattern[i][j])) {
                    return false;
                }
            }
        }

        // Check if the last number called is on one of the non-zero spots in the pattern
        int lastNumberCalled = calledNumbers.stream().reduce((first, second) -> second).orElse(null);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (pattern[i][j] != 0 && bingoCard.getCard()[i][j] == lastNumberCalled) {
                    return true;
                }
            }
        }

        return false;
    }
}