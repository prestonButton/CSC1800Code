import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            BingoGame game = new BingoGame(scanner);
            game.play();
        }
        scanner.close();
    }
}