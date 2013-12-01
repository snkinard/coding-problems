package com.snkinard.coding;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * In a past week we covered The Big Three tree traversals: pre-order, in-order and post-order. But this week's traversal is fairly common, too—and often shows up in interviews.
 *
 * This week's question is to print a binary tree of integers in level order.
 *
 * Level-order printing means printing each row of the tree from left to right, from root row to the deepest row. After each row, print a newline.
 *
 * Bonus: Can you make one version that supports binary trees, and another that can handle any K-ary tree?
 *
 * NOTE: I also did the "big three" traversals
 *
 * // TODO: big three traversals for knary trees
 * // TODO: level order printing in reverse
 */
public class TreeTraversal {

    public static String preorderRecursive(BinaryTree tree) {
        if (tree == null) {
            return "";
        }
        return tree.getData() + preorderRecursive(tree.getLeftChild()) + preorderRecursive(tree.getRightChild());
    }

    public static String preorderIterative(BinaryTree tree) {
        Stack<BinaryTree> stack = new Stack<>();
        stack.push(tree);
        StringBuffer result = new StringBuffer();
        while(!stack.isEmpty()) {
            BinaryTree currentNode = stack.pop();
            result.append(currentNode.getData() + " ");
            if (currentNode.getRightChild() != null) {
                stack.push(currentNode.getRightChild());
            }
            if (currentNode.getLeftChild() != null) {
                stack.push(currentNode.getLeftChild());
            }
        }
        return result.toString().trim();
    }

    public static String inorder(BinaryTree tree) {
        Stack<BinaryTree> stack = new Stack<>();
        StringBuffer result = new StringBuffer();
        BinaryTree currentNode = tree;
        boolean done = false;
        while(!done) {
            if(currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            }
            else {
                if(!stack.isEmpty()) {
                    currentNode = stack.pop();
                    result.append(currentNode.getData() + " ");
                    currentNode = currentNode.getRightChild();
                }
                else {
                    done = true;
                }
            }
        }
        return result.toString().trim();
    }

    public static String postorder(BinaryTree tree) {
        Stack<BinaryTree> stack = new Stack<>();
        StringBuffer result = new StringBuffer();
        stack.push(tree);
        BinaryTree currentNode = tree;
        while (!stack.isEmpty()) {
            BinaryTree nextNode = stack.peek();
            if (nextNode.getRightChild() == currentNode || nextNode.getLeftChild() == currentNode || (nextNode.getRightChild() == null && nextNode.getLeftChild() == null)) {
                stack.pop();
                result.append(nextNode.getData() + " ");
                currentNode = nextNode;
            }
            else {
                if (nextNode.getRightChild() != null) {
                    stack.push(nextNode.getRightChild());
                }
                if (nextNode.getLeftChild() != null) {
                    stack.push(nextNode.getLeftChild());
                }
            }
        }
        return result.toString().trim();
    }

    public static String levelorderNewline(BinaryTree tree) {
        BinaryTree sentinel = new BinaryTree('S');
        Queue<BinaryTree> queue = new ArrayDeque<>();
        List<BinaryTree> currentLevel = new ArrayList<>();
        StringBuffer result = new StringBuffer();
        Collections.addAll(queue, tree, sentinel);

        while (true) {
            BinaryTree currentNode = queue.remove();
            if (currentNode == sentinel) {
                for (BinaryTree node : currentLevel) {
                    result.append(String.format("%s ", node.getData()));
                }
                result.append('\n');
                if (queue.size() == 0) {
                    break;
                }
                queue.add(sentinel);
                currentLevel.clear();
            } else {
                currentLevel.add(currentNode);
                if (currentNode.getLeftChild() != null) {
                    queue.add(currentNode.getLeftChild());
                }
                if (currentNode.getRightChild() != null) {
                    queue.add(currentNode.getRightChild());
                }
            }
        }
        return result.toString().trim();
    }

    public static String levelorderNewline(KnaryTree tree) {
        KnaryTree sentinel = new KnaryTree('S');
        Queue<KnaryTree> queue = new ArrayDeque<>();
        List<KnaryTree> currentLevel = new ArrayList<>();
        StringBuffer result = new StringBuffer();
        Collections.addAll(queue, tree, sentinel);

        while (true) {
            KnaryTree currentNode = queue.remove();
            if (currentNode == sentinel) {
                for (KnaryTree node : currentLevel) {
                    result.append(String.format("%s ", node.getData()));
                }
                result.append('\n');
                if (queue.size() == 0) {
                    break;
                }
                queue.add(sentinel);
                currentLevel.clear();
            } else {
                currentLevel.add(currentNode);
                queue.addAll(currentNode.getChildren());
            }
        }
        return result.toString().trim();
    }
}

class BinaryTree {

    private char data;
    private BinaryTree leftChild;
    private BinaryTree rightChild;

    public BinaryTree(char data) {
        this(data, null, null);
    }

    public BinaryTree(char data, BinaryTree leftChild, BinaryTree rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }


    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public BinaryTree getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTree leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTree getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTree rightChild) {
        this.rightChild = rightChild;
    }
}

class KnaryTree {

    private char data;
    private List<KnaryTree> children;

    public KnaryTree(char data) {
        this.data = data;
        children = new ArrayList<>();
    }

    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public List<KnaryTree> getChildren() {
        return children;
    }

    public void addChild(KnaryTree child) {
        children.add(child);
    }
}