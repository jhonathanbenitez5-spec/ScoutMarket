package com.scoutmarket;

import java.util.ArrayList;


public class MaxHeap {
    private ArrayList<Player> heap;
    private int capacity;

    public MaxHeap(int capacity) {
        this.heap = new ArrayList<>(capacity);
        this.capacity = capacity;
    }

    public void insert(Player player) {
        if (heap.size() >= capacity) {
            System.out.println("Heap is at max capacity!");
            return;
        }
        heap.add(player); 
        bubbleUp(heap.size() - 1); 
    }

    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            
            // Uses the compareTo from Player.java
            if (heap.get(index).compareTo(heap.get(parentIndex)) > 0) {
                swap(index, parentIndex); 
                index = parentIndex; 
            } else {
                break; 
            }
        }
    }

    private void swap(int i, int j) {
        Player temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // 2.Sor the Top 5 players
    public Player extractMax() {
        if (heap.isEmpty()) return null;
        if (heap.size() == 1) return heap.remove(0);

        Player root = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1)); 
        bubbleDown(0);

        return root;
    }

    // Pushes the smaller elements back down the tree
    private void bubbleDown(int index) {
        int largest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < heap.size() && heap.get(leftChild).compareTo(heap.get(largest)) > 0) {
            largest = leftChild;
        }
        
        if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(largest)) > 0) {
            largest = rightChild;
        }

        if (largest != index) {
            swap(index, largest);
            bubbleDown(largest);
        }
    }

    public int getSize() {
        return heap.size();
    }

    public Player[] getHeap() {
        return heap.toArray(new Player[0]);
    }
}