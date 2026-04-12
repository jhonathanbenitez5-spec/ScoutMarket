import java.util.ArrayList;

public class ScoutController {
    private ScoutView view;
    private AVLTree tree;
    private MaxHeap heap;

    public ScoutController(ScoutView view, AVLTree tree, MaxHeap heap) {
        this.view = view;
        this.tree = tree;
        this.heap = heap;
    }

    public void start() {
        boolean running = true;

        while (running) {
            view.displayMainMenu();
            int choice = view.getIntInput("");

            switch (choice) {
                case 1:
                    handleAddPlayer();
                    break;
                case 2:
                    handleSearchPlayer();
                    break;
                case 3:
                    handleViewLeaderboard();
                    break;
                case 4:
                    view.printMessage("Exiting ScoutMarket... Goodbye!");
                    running = false;
                    break;
                default:
                    view.printMessage("❌ Invalid option. Please select 1-4.");
            }
        }
    }

    private void handleAddPlayer() {
        view.printMessage("--- ADD NEW PLAYER ---");
        int id = view.getIntInput("Enter Player ID: ");
        String name = view.getStringInput("Enter Player Name: ");
        String team = view.getStringInput("Enter Team Name: ");
        long value = view.getLongInput("Enter Market Value (€): ");
        
        Player newPlayer = new Player(id, name, team, value);

        // Ask for stats right away so the ROI math triggers!
        int goals = view.getIntInput("Enter current goals: ");
        int assists = view.getIntInput("Enter current assists: ");
        
        // Matches the exact spelling from your Player.java file
        newPlayer.updatePerfomance(goals, assists);

        // Save to the Models
        tree.insert(newPlayer);
        heap.insert(newPlayer);

        view.printMessage("✅ " + name + " successfully added!");
    }

    private void handleSearchPlayer() {
        int idToSearch = view.getIntInput("\nEnter ID to search: ");
        Player found = tree.search(idToSearch);

        if (found != null) {
            view.printMessage("✅ Player Found: " + found.toString());
        } else {
            view.printMessage("❌ Player with ID " + idToSearch + " not found.");
        }
    }

    private void handleViewLeaderboard() {
        ArrayList<Player> topPlayers = heap.getTopPlayers(5);
        view.displayLeaderboard(topPlayers);
    }
}