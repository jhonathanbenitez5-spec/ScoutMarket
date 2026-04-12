public class AVLTree {
    private Node root;

    // HELPER 1: Get the height of a node.
    private int height(Node n) {
        if (n == null) return 0;
        return n.height;
    }

    // HELPER 2: Check the balance.
    private int getBalance(Node n) {
        if (n == null) return 0;
        return height(n.left) - height(n.right);
    }

    // ROTATION 1: Right Rotate to Fix a Left-Heavy tree)
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // ROTATION 2: Left Rotate to Fix a Right-Heavy tree
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // 1. The Public Entry Point
public void insert(Player player) {
    root = insert(root, player);
}

private Node insert(Node node, Player player) {

    if (node == null) {
        return new Node(player); 
    }

    if (player.getPlayerID() < node.player.getPlayerID()) {
        node.left = insert(node.left, player); 
    } else if (player.getPlayerID() > node.player.getPlayerID()) {
        node.right = insert(node.right, player); 
    } else {
        return node; // ID already exists, do nothing
    }

    //  Update Height
    node.height = 1 + Math.max(height(node.left), height(node.right));
    int balance = getBalance(node);
    
    // Case 1: Left-Left (LL)
    if (balance > 1 && player.getPlayerID() < node.left.player.getPlayerID()) {
        return rightRotate(node);
    }

    // Case 2: Right-Right (RR)
    if (balance < -1 && player.getPlayerID() > node.right.player.getPlayerID()) {
        return leftRotate(node);
    }

    // Case 3: Left-Right (LR)
    if (balance > 1 && player.getPlayerID() > node.left.player.getPlayerID()) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    // Case 4: Right-Left (RL)
    if (balance < -1 && player.getPlayerID() < node.right.player.getPlayerID()) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    return node; 
}
// Public search
public Player search(int playerID) {
    Node result = search(root, playerID);
    if (result == null) return null;
    return result.player;
}

// Private recursive search
private Node search(Node node, int playerID) {
    if (node == null || node.player.getPlayerID() == playerID) {
        return node;
    }
    if (node.player.getPlayerID() < playerID) {
        return search(node.right, playerID);
    }
    return search(node.left, playerID);
}
}