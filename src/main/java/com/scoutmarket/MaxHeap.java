package com.scoutmarket;

import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class MaxHeap {
    private ArrayList<Player> heap;

    public MaxHeap() {
        this.heap = new ArrayList<>();
    }

    public void insert(Player player) {
        heap.add(player); 
        bubbleUp(heap.size() - 1); 
    }

    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            
            // Uses the compareTo from  Player.java
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

    public ArrayList<Player> getTopPlayers(int count) {
        ArrayList<Player> topPlayers = new ArrayList<>();
        int limit = Math.min(heap.size(), count);
        for (int i = 0; i < limit; i++) {
            topPlayers.add(heap.get(i));
        }
        return topPlayers;
    }
}