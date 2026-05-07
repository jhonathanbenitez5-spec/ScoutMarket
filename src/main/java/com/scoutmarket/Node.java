package com.scoutmarket;
public class Node {
    Player player;
    
    
    Node left;
    Node right;
    int height;

    // The Constructor: to create a new "Box"
    public Node(Player player) {
        this.player = player;
        this.left = null;   // New nodes start with no children
        this.right = null;
        this.height = 1;    // A new node has a height of 1
    }
}