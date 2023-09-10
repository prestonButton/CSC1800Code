import java.util.*;

public class BingoVerifier {
    public static boolean verifyBingo(int[][] bingoCard, ArrayList<Integer> rowIndices, ArrayList<Integer> columnIndices, ArrayList<Integer> calledNumbers) {
        boolean isValidBingo = true;
        boolean isLastNumberCalled = false;
        for (int i = 0; i < rowIndices.size(); i++) {
            int currentNumber = bingoCard[rowIndices.get(i)][columnIndices.get(i)];
            if (!calledNumbers.contains(currentNumber) && currentNumber != 0) {
                isValidBingo = false;
            }
            if (currentNumber == calledNumbers.get(calledNumbers.size()-1)) {
                isLastNumberCalled = true;
            }
        }
        if (!isLastNumberCalled) {
            isValidBingo = false;
        }
        return isValidBingo;
    }

    public static void rotatePattern(ArrayList<Integer> rowIndices, ArrayList<Integer> columnIndices) {
        for (int j = 0; j < rowIndices.size(); j++) {
            int temp = columnIndices.get(j);
            columnIndices.set(j, (4 - rowIndices.get(j)));
            rowIndices.set(j, temp);
        }
    }
}