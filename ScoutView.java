import java.util.Scanner;
import java.util.ArrayList;

public class ScoutView {
    private Scanner scanner;

    public ScoutView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        System.out.println("\n=========================================");
        System.out.println("   ⚽ SCOUTMARKET TERMINAL (SPRINT 2) ⚽   ");
        System.out.println("=========================================");
        System.out.println("1. Add a New Player & Stats");
        System.out.println("2. Search for a Player (by ID)");
        System.out.println("3. View Top Value Leaderboard");
        System.out.println("4. Exit System");
        System.out.print("Select an option (1-4): ");
    }

    public int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next(); 
        }
        int input = scanner.nextInt();
        scanner.nextLine(); 
        return input;
    }

    // Handles the 'long' data type for market values
    public long getLongInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextLong()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next(); 
        }
        long input = scanner.nextLong();
        scanner.nextLine(); 
        return input;
    }

    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void printMessage(String message) {
        System.out.println("\n" + message);
    }

    public void displayLeaderboard(ArrayList<Player> topPlayers) {
        System.out.println("\n--- 🏆 TOP VALUE LEADERBOARD 🏆 ---");
        if (topPlayers.isEmpty()) {
            System.out.println("No players in the system yet.");
            return;
        }
        for (int i = 0; i < topPlayers.size(); i++) {
            // Uses your custom toString() format!
            System.out.println((i + 1) + ". " + topPlayers.get(i).toString());
        }
    }
}