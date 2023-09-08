import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world! Bingo Verifier");

        // 2D array to represent the pattern
        int[][] pattern = new int[5][5];

        // List to store the called numbers
        List<Integer> calledNumbers = new ArrayList<>();

        // Create a Scanner object for reading the input
        Scanner scanner = new Scanner(System.in);

        try {
            // Read the pattern
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    pattern[i][j] = scanner.nextInt();
                }
            }

            // Print the pattern
            System.out.println("Pattern:");
            for (int[] row : pattern) {
                System.out.println(Arrays.toString(row));
            }

            // Skip the blank line
            scanner.nextLine();            
            scanner.nextLine();


            // Read the called numbers
            String[] numbers = scanner.nextLine().split(" ");
            for (String number : numbers) {
                int num = Integer.parseInt(number);
                calledNumbers.add(num);
            }

            // Print the called numbers
            System.out.println("Called numbers: " + calledNumbers);

            // Convert calledNumbers to a Set for faster lookups
            Set<Integer> calledNumbersSet = new HashSet<>(calledNumbers);

            // Skip the blank line
            scanner.nextLine();

            // 2D array to represent the bingo card
            int[][] bingoCardNumbers = new int[5][5];

            // Read the bingo card
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    bingoCardNumbers[i][j] = scanner.nextInt();
                }
            }

            // Print the bingo card
            System.out.println("Bingo Card:");
            for (int[] row : bingoCardNumbers) {
                System.out.println(Arrays.toString(row));
            }

            BingoCard card = new BingoCard(bingoCardNumbers);

            // Create a BingoVerifier object
            BingoVerifier verifier = new BingoVerifier(pattern, calledNumbersSet, card);

            // Print the result
            if (verifier.isValidBingo()) {
                System.out.println("VALID BINGO");
            } else {
                System.out.println("NO BINGO");
            }
        } finally {
            scanner.close();
        }
    }
}