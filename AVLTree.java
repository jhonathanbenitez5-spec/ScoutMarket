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
}