public class BingoCard {
    private int[][] card;

    public BingoCard(int[][] cardNumbers) {
        card = new int[5][5];
        // Populate the card with the provided numbers
        for (int i = 0; i < 5; i++) {
            System.arraycopy(cardNumbers[i], 0, card[i], 0, 5);
        }
    }

    public void markNumber(int number) {
        // Find the position of the number on the card and mark it
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (card[i][j] == number) {
                    card[i][j] = 0;  // Mark the number
                }
            }
        }
    }

    public boolean isNumberMarked(int number) {
        // Check if a number is marked on the card
        for (int[] row : card) {
            for (int cell : row) {
                if (cell == number) {
                    return false;  // The number is not marked
                }
            }
        }
        return true;  // The number is marked
    }

    public void rotateClockwise() {
        int[][] rotatedCard = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                rotatedCard[j][4 - i] = card[i][j];
            }
        }
        card = rotatedCard;
    }

    public int getNumber(int row, int column) {
        return card[row][column];
    }

    public int[][] getCard() {
        return card;
    }
}