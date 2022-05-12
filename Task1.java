class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class BinaryTree {
    Node root;

    void updateTree(Node node) {
        Node old_left;
        if (node == null)
            return;
        updateTree(node.left);
        updateTree(node.right);  
        old_left = node.left;
        node.left = new Node(node.data);
        node.left.left = old_left;
    }

    public void displayTree() {
        java.util.Stack<Node> globalStack = new java.util.Stack<Node>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("......................................................");
        while (isRowEmpty == false) {
            java.util.Stack<Node> localStack = new java.util.Stack<Node>();
            isRowEmpty = true;
            for (int j = 0; j < nBlanks; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) {
                Node temp = globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.data);
                    localStack.push(temp.left);
                    localStack.push(temp.right);
                    if (temp.left != null || temp.right != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++)
                    System.out.print(' ');
            } // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        } // end while isRowEmpty is false
        System.out.println("......................................................");
    }
}

public class Task1 {
    public static void main(String args[]) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(2);
        tree.root.left = new Node(1);
        tree.root.right = new Node(3);
        tree.root.left.right = new Node(4);
        tree.displayTree();
        tree.updateTree(tree.root);
        tree.displayTree();
    }
}