// Pulkit Verma
// Class (CECS 274-05)
// Project Name (Program 6 - Binary Search Tree)
// Due Date (Dec 11, 2018)

import java.util.Scanner;

public class BST {
    static Node root;
    static int count = 0;
    static char menuOption;
    int[] myArray = {0, 52, 33, 75, 21, 40, 64, 86, 15, 27, 37, 46, 58, 70, 81, 89, 12, 18, 24, 30, 35, 39, 43, 49, 55, 61, 67, 73, 79, 83, 87, 92};

    public BST() {
        root = null;
    }

    // This method prints the menu on the screen
    public static void printMenu() {
        System.out.print("\n(A)dd Item     (R)emove Item     (F)ind Item     (I)nitialize Tree     (N)ew Tree     (Q)uit\n");
    }

    // This method gets the menu option from the user
    public static int getMenuOption() {
        Scanner input = new Scanner(System.in);

        menuOption = input.next().toLowerCase().charAt(0);

        while (menuOption != 'a' && menuOption != 'r' && menuOption != 'f' && menuOption != 'i' && menuOption != 'n' && menuOption != 'q') {
            System.out.print("Enter a valid choice: ");
            menuOption = input.next().toLowerCase().charAt(0);
        }

        if (menuOption == 'a') {
            return 1;
        } else if (menuOption == 'r') {
            return 2;
        } else if (menuOption == 'f') {
            return 3;
        } else if (menuOption == 'i') {
            return 4;
        } else if (menuOption == 'n') {
            return 5;
        } else {
            return 6;
        }
    }

    // This method replaces the value that the user wants to remove with zero
    public static int[] replaceZero(int num, int[] numArray) {
        for (int i = 1; i < numArray.length; i++) {
            if (num == numArray[i]) {
                numArray[i] = 0;
                break;
            }
        }
        return numArray;
    }

    public static void main(String[] args) {
        BST tree = new BST();
        int[] emptyArray = new int[32];
        tree.printBST(emptyArray);
        printMenu();
        System.out.print("\nEnter Choice: ");
        Scanner input = new Scanner(System.in);
        int menuOption = getMenuOption();

        while (menuOption != 6) {
            switch (menuOption) {
                case 1:
                    count = 0;
                    System.out.print("Enter a number: ");
                    int aValue = input.nextInt();
                    tree.addItem(aValue);
                    tree.printBST(emptyArray);
                    System.out.print("\n" + aValue + " was added to the binary search tree.\n");
                    System.out.print("\nSorted List: ");
                    tree.printSortedList();
                    break;
                case 2:
                    count = 0;
                    System.out.print("Enter a number: ");
                    int bValue = input.nextInt();
                    if (tree.removeItem(bValue) == false) {
                        System.out.println("\nThe number does not exist.");
                    } else {
                        tree.removeItem(bValue);
                        System.out.print("\n" + bValue + " was removed from the binary search tree.\n");
                    }
                    replaceZero(bValue, emptyArray);
                    tree.printBST(emptyArray);
                    System.out.print("\nSorted List: ");
                    tree.printSortedList();
                    break;
                case 3:
                    System.out.print("Enter a number: ");
                    int cValue = input.nextInt();
                    int level = tree.findItem(cValue);
                    if (level == 0) {
                        System.out.print("\nThe number does not exist.\n");
                    } else {
                        System.out.print("\nLevel: " + level + "\n");
                    }
                    break;
                case 4:
                    count = 0;
                    tree.initializeTree();
                    tree.printBST(emptyArray);
                    System.out.print("\nSorted List: ");
                    tree.printSortedList();
                    break;
                case 5:
                    tree = new BST();
                    emptyArray = new int[32];
                    tree.printBST(emptyArray);
                    break;
                case 6:
            }

            System.out.printf("\nTree Height: %d\n", tree.treeHeight());
            System.out.printf("Root Value: %d\n", tree.getRootValue());
            System.out.printf("Item Count: %d\n", tree.getItemCount());

            printMenu();
            System.out.print("\nEnter Choice: ");
            menuOption = getMenuOption();
        }
    }

    // This method initializes the array with 31 elements
    public int[] initializeTree() {
        BST myTree = new BST();
        int[] myArray = {0, 52, 33, 75, 21, 40, 64, 86, 15, 27, 37, 46, 58, 70, 81, 89, 12, 18, 24, 30, 35, 39, 43, 49, 55, 61, 67, 73, 79, 83, 87, 92};
        for (int i = 1; i < myArray.length; i++) {
            myTree.addItem(myArray[i]);
        }
        return myArray;
    }

    // This method adds an item to the binary search tree
    public void addItem(int value) {
        if (root == null) {
            root = new Node(value);
        } else {
            addToNode(root, value);
        }
    }

    private void addToNode(Node node, int value) {
        if (value < node.getValue()) {
            if (node.getLeftChild() == null) {
                node.leftChild = new Node(value);
            } else {
                addToNode(node.leftChild, value);
            }
        } else if (value > node.getValue()) {
            if (node.getRightChild() == null) {
                node.rightChild = new Node(value);
            } else {
                addToNode(node.rightChild, value);
            }
        } else {
            System.out.print("\nValue already exists.\n");
        }
    }

    // This method prints sorted list on the screen
    public void printSortedList() {
        if (root == null) {
            System.out.print("Empty Tree.");
            return;
        } else {
            LNRTraversal(root);
        }
    }

    private void LNRTraversal(Node node) {

        if (node == null) {
            return;
        } else {
            LNRTraversal(node.getLeftChild());
            if (count < getItemCount() - 1) {
                System.out.print(node.getValue() + ", ");
                count++;
            } else {
                System.out.print(node.getValue() + " ");
            }
            LNRTraversal(node.getRightChild());
        }
    }

    // This method returns the height of the tree
    public int treeHeight() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        } else {
            int lChild = height(node.leftChild);
            int rChild = height(node.rightChild);
            int max = (lChild > rChild) ? lChild : rChild;
            return 1 + max;
        }
    }


    // This method returns the root value of the binary search tree
    public int getRootValue() {
        if (root == null) {
            return 0;
        } else {
            return root.getValue();
        }
    }

    // This method returns the number of items in the binary search tree
    public int getItemCount() {
        return itemCount(root);
    }

    private int itemCount(Node node) {
        if (node == null) {
            return 0;
        } else {
            return itemCount(node.getLeftChild()) + itemCount(node.getRightChild()) + 1;
        }
    }


    // This method returns the level on which the item exists in the binary search tree
    public int findItem(int value) {
        return find(value, root);
    }

    private int find(int value, Node node) {
        if (node == null) {
            return 0;
        }

        if (node.getValue() == value) {
            return height(root) - height(node) + 1;
        } else {
            if (value <= node.getValue()) {
                return find(value, node.leftChild);
            } else {
                return find(value, node.rightChild);
            }
        }
    }

    // This method removes the item from the binary search tree
    public boolean removeItem(int value) {
        if (root == null) {
            return false;
        } else {
            return remove(value, root, null);
        }
    }

    private boolean remove(int value, Node currentNode, Node previousNode) {
        if (value < currentNode.getValue()) {
            if (currentNode.getLeftChild() != null) {
                return remove(value, currentNode.getLeftChild(), currentNode);
            } else {
                return false;
            }
        } else if (value > currentNode.getValue()) {
            if (currentNode.getRightChild() != null) {
                return remove(value, currentNode.getRightChild(), currentNode);
            } else {
                return false;
            }
        } else {
            if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {
                if (currentNode == root) {
                    root = null;
                } else if (value < previousNode.getValue()) {
                    previousNode.setLeftChild(null);
                } else {
                    previousNode.setRightChild(null);
                }
            } else if (currentNode.getLeftChild() != null && currentNode.getRightChild() == null) {
                if (currentNode == root) {
                    root = currentNode.getLeftChild();
                } else if (previousNode.value > currentNode.value) {
                    previousNode.setLeftChild(currentNode.getLeftChild());
                } else {
                    previousNode.setRightChild(currentNode.getLeftChild());
                }
                swapArrayElement(value, currentNode.getLeftChild().value);
            } else if (currentNode.getLeftChild() == null && currentNode.getRightChild() != null) {
                if (currentNode == root) {
                    root = currentNode.getRightChild();
                } else if (previousNode.value > currentNode.value) {
                    previousNode.setLeftChild(currentNode.getRightChild());
                } else {
                    previousNode.setRightChild(currentNode.getRightChild());
                }
                swapArrayElement(value, currentNode.getRightChild().value);
            } else {
                Node successor = getSuccessorNode(currentNode);
                if (currentNode == root) {
                    root = successor;
                } else {
                    if (successor.value > previousNode.value) {
                        previousNode.setRightChild(successor);
                    } else {
                        previousNode.setLeftChild(successor);
                    }
                }
                successor.setLeftChild(currentNode.getLeftChild());
                swapArrayElement(value, successor.value);
            }
            return true;
        }
    }

    // This method returns the successor node
    public Node getSuccessorNode(Node node) {
        Node successor = null;
        Node successorParent = null;
        Node currentNode = node.getRightChild();
        while (currentNode != null) {
            successorParent = successor;
            successor = currentNode;
            currentNode = currentNode.getLeftChild();
        }
        if (successor != node.getRightChild()) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(node.getRightChild());
        }
        return successor;
    }

    // This method add elements from the binary search tree to the array
    public void createArray(Node node, int index, int[] array) {
        if (node == null || index >= 32) {
            if (menuOption == 'r') {
                for (int i = index; i < myArray.length; i++) {
                    array[index] = 0;
                }
            }
            return;
        }
        array[index] = node.getValue();
        createArray(node.getLeftChild(), index * 2, array);
        createArray(node.getRightChild(), index * 2 + 1, array);
    }

    // This method prints the binary search tree on the screen
    public void printBST(int[] myArray) {
        createArray(root, 1, myArray);

        System.out.println("                                                                                " + checkString(myArray[1]));
        System.out.println("                                                                                " + " |");
        System.out.println("                                      " + checkString(myArray[2]) + "----------------------------------------" + " ^ " + "----------------------------------------" + checkString(myArray[3]));
        System.out.println("                                      " + " |" + "                                                                                   " + " |");
        System.out.println("                    " + checkString(myArray[4]) + "----------------" + " ^ " + "----------------" + checkString(myArray[5]) + "                                              " + checkString(myArray[6]) + "----------------" + " ^ " + "----------------" + checkString(myArray[7]));
        System.out.println("                    " + " |" + "                                   " + " |" + "                                              " + " |" + "                                   " + " |");
        System.out.println("         " + checkString(myArray[8]) + "---------" + " ^ " + "---------" + checkString(myArray[9]) + "           " + checkString(myArray[10]) + "----------" + " ^ " + "----------" + checkString(myArray[11]) + "                      " + checkString(myArray[12]) + "---------" + " ^ " + "---------" + checkString(myArray[13]) + "           " + checkString(myArray[14]) + "----------" + " ^ " + "----------" + checkString(myArray[15]));
        System.out.println("         " + "|" + "                       " + "|" + "            " + "|" + "                        " + "|" + "                      " + "|" + "                       " + "|" + "            " + "|" + "                        " + "|");
        System.out.println("     " + checkString(myArray[16]) + "--" + "^" + "--" + checkString(myArray[17]) + "               " + checkString(myArray[18]) + "--" + "^" + "--" + checkString(myArray[19]) + "    " + checkString(myArray[20]) + "--" + "^" + "--" + checkString(myArray[21]) + "                " + checkString(myArray[22]) + "--" + "^" + "--" + checkString(myArray[23]) + "              " + checkString(myArray[24]) + "--" + "^" + "--" + checkString(myArray[25]) + "               " + checkString(myArray[26]) + "--" + "^" + "--" + checkString(myArray[27]) + "    " + checkString(myArray[28]) + "--" + "^" + "--" + checkString(myArray[29]) + "                " + checkString(myArray[30]) + "--" + "^" + "--" + checkString(myArray[31]));
    }

    // This method checks if the element is a " ." or a integer value
    private String checkString(int n) {
        if (n == 0) {
            return " .";
        } else {
            return String.valueOf(n);
        }
    }

    // This method swap elements in the array
    public void swapArrayElement(int elem1, int elem2) {
        int index1 = 0, index2 = 0, temp;
        for (int i = 1; i < myArray.length; i++) {
            if (myArray[i] == elem1)
                index1 = i;
            if (myArray[i] == elem2)
                index2 = i;
        }
        temp = myArray[index1];
        myArray[index1] = myArray[index2];
        myArray[index2] = temp;
    }

    // This is the node class with has a value, a left child, and a right child
    public class Node {
        private int value;
        private Node leftChild;
        private Node rightChild;

        public Node(int v) {
            value = v;
            leftChild = null;
            rightChild = null;
        }

        public int getValue() {
            return value;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node node) {
            leftChild = node;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node node) {
            rightChild = node;
        }
    }
}
