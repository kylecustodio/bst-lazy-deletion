// Kyle Custodio | kyc180000
// Data Structures and Introduction to Algorithmic Analysis | CS 3345
// Section: 001
// Semester: Fall 2019
// Project 3: Implements a BST with lazy deletion

/**
 * the LazyBinarySearchTree class stores integers in a BST and performs lazy deletion
 */
public class LazyBinarySearchTree {
    /**
     * the TreeNode inner class, which stores integers
     */
    static class TreeNode {
        /**
         * the key of the tree node
         */
        int key;

        /**
         * the left child of the tree node which has a key of a lesser value
         */
        TreeNode leftChild;

        /**
         * the right child of the tree node which has a key of a greater value
         */
        TreeNode rightChild;

        /**
         * if the tree node is marked as deleted
         */
        boolean deleted;

        /**
         * creates a new tree node
         * @param key the key of the tree node
         */
        public TreeNode(int key) {
            this.key = key;
            leftChild = null;
            rightChild = null;
            deleted = false;
        }
    }

    /**
     * the root of the tree
     */
    private TreeNode root;

    /**
     * the number of the nodes in the tree that are not marked as deleted
     */
    private int size;

    /**
     * the number of all of the nodes in the tree
     */
    private int actualSize;

    /**
     * creates a new lazy binary search tree
     */
    public LazyBinarySearchTree() {
        root = null;
        size = 0;
    }

    /**
     * Inserts the given key into the tree
     * @param key the key to be inserted into the tree
     * @return if the key was inserted logically
     * @throws IllegalArgumentException if key is not within the range [1, 99]
     */
    public boolean insert(int key) throws IllegalArgumentException {
        if (key < 1 || key > 99) {
            throw new IllegalArgumentException("Error in insert: IllegalArgumentException raised");
        }
        int oldSize = size;
        root = insert(root, key);

        // if oldSize is the same as size, no logical insertion happened
        return oldSize != size;
    }

    /**
     * Helper method for insert(int key), inserts a node with the given value into the tree recursively
     * @param root the root of the tree
     * @param key the key to be inserted into the tree
     * @return the root of the tree where the node was inserted
     */
    private TreeNode insert(TreeNode root, int key) {
        if (root == null) {
            size++;
            actualSize++;
            return new TreeNode(key);
        }

        if (key < root.key) {
            if (root.leftChild != null) {
                if (root.leftChild.key == key && root.leftChild.deleted) {
                    size++;
                    actualSize++;
                    root.leftChild.deleted = false;
                }
            }
            root.leftChild = insert(root.leftChild, key);
        } else if (key > root.key) {
            if (root.rightChild != null) {
                if (root.rightChild.key == key && root.rightChild.deleted) {
                    size++;
                    actualSize++;
                    root.rightChild.deleted = false;
                }
            }
            root.rightChild = insert(root.rightChild, key);
        } else if (root != null) {
            if (root.key == key && root.deleted) {
                size++;
                root.deleted = false;
            }
        }
        return root;
    }

    /**
     * Marks the node with the given value as deleted
     * @param key the key to be deleted from the tree
     * @return if the node containing key was logically deleted
     * @throws IllegalArgumentException if key is not within the range [1, 99]
     */
    public boolean delete(int key) throws IllegalArgumentException {
        if (key < 1 || key > 99) {
            throw new IllegalArgumentException("Error in delete: IllegalArgumentExceptionRaised");
        }

        if (root == null) {
            return false;
        }

        TreeNode node = find(root, key);

        if (node != null && !node.deleted) {
            size--;
            node.deleted = true;
            return true;
        }

        return false;
    }

    /**
     * Finds the value of the left most node in the tree
     * @return the minimum value in the tree
     */
    public int findMin() {
        TreeNode node = findMin(root);
        return node != null ? node.key : -1;
    }

    /**
     * Helper method for findMin(), traverses the tree recursively to find the minimum value
     * @param root the root of the tree
     * @return the left most tree node
     */
    private TreeNode findMin(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode node = findMin(root.leftChild);

        if (node != null) {
            return node;
        }

        if (!root.deleted) {
            return root;
        }

        return findMin(root.rightChild);
    }

    /**
     * Finds the value of the right most node in the tree
     * @return the maximum value in the tree
     */
    public int findMax() {
        TreeNode node = findMax(root);
        return node != null ? node.key : -1;
    }

    /**
     * Helper method for findMax(), traverses the tree recursively to find maximum value
     * @param root the root of the tree
     * @return The right most tree node
     */
    private TreeNode findMax(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode node = findMax(root.rightChild);

        if (node != null) {
            return node;
        }

        if (!root.deleted) {
            return root;
        }

        return findMax(root.leftChild);
    }

    /**
     * Checks if the tree contains a given value
     * @param key the key to look for in the tree
     * @return if the tree node with the given key is in the tree and is not marked as deleted
     * @throws IllegalArgumentException if key is not within the range [1, 99]
     */
    public boolean contains(int key) throws IllegalArgumentException {
        if (key < 1 || key > 99) {
            throw new IllegalArgumentException("Error in contains: IllegalArgumentException was raised");
        }

        TreeNode node = find(root, key);
        return node != null && !node.deleted;
    }

    /**
     * Finds the tree node with a given value in the tree
     * @param root the root of the tree
     * @param key the key to look for in the tree
     * @return the tree node with the given key, null if not in the tree
     */
    private TreeNode find(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.key) {
            return find(root.leftChild, key);
        } else if (key > root.key) {
            return find(root.rightChild, key);
        }

        return root;
    }

    /**
     * Gives a string representation of the tree with '*' before all elements that are marked deleted
     * @return a string with all values of the tree
     */
    @Override
    public String toString() {
        return toString(root);
    }

    /**
     * Helper method for toString(), uses pre-order traversal
     * @param root the root of the tree
     * @return a string with all values of the tree
     */
    private String toString(TreeNode root) {
        String result = "";
        if (root == null) {
            return result;
        }

        if (root.deleted) {
            result += "*";
        }

        result += root.key + " ";
        result += toString(root.leftChild);
        result += toString(root.rightChild);
        return result;
    }

    /**
     * Gives the height of the tree
     * @return the height of the tree
     */
    public int height() {
        return height(root);
    }

    /**
     * Helper method for height(), calculates the height of the tree recursively
     * @param root the root of the tree
     * @return the height of the tree
     */
    private int height(TreeNode root) {
        if (root == null) {
            return -1;
        }

        int left = height(root.leftChild);
        int right = height(root.rightChild);

        if (left > right) {
            return left + 1;
        } else {
            return right + 1;
        }
    }

    /**
     * Gives the size of the tree
     * @return the number of nodes, both deleted and not, in the tree
     */
    public int size() {
        return actualSize;
    }
}
