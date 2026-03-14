public class Node {
    Player player;
    
    
    Node left;
    Node right;
    
    // This is a special number for the AVL Tree to stay balanced
    int height;

    // The Constructor: to create a new "Box"
    public Node(Player player) {
        this.player = player;
        this.left = null;   // New nodes start with no children
        this.right = null;
        this.height = 1;    // A new node has a height of 1
    }
}