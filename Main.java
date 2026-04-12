public class Main {
    public static void main(String[] args) {
        AVLTree myTree = new AVLTree();
        MaxHeap myHeap = new MaxHeap();
        ScoutView myView = new ScoutView();
        
        ScoutController myController = new ScoutController(myView, myTree, myHeap);
        myController.start();
    }
}