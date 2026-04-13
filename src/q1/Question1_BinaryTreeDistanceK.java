package q1;

/**
 * Question 1: Print all nodes at distance K from the root of a binary tree.
 *
 * Approach:
 * - Use recursion. At each call, reduce k by 1.
 * - When k == 0, print the current node's data.
 * - Recurse left and right with k-1.
 * - Base case: if node is null, return.
 *
 * Time Complexity: O(N) — visits every node once.
 * Space Complexity: O(H) — recursion stack, H = height of tree.
 */
public class Question1_BinaryTreeDistanceK {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Recursively prints all nodes at distance k from the given root.
     *
     * @param root The current node being examined.
     * @param k    The remaining distance to travel.
     */
    static void printNodesAtDistanceK(Node root, int k) {
        // Base case: empty node
        if (root == null) return;

        // If we've reached the target distance, print this node
        if (k == 0) {
            System.out.print(root.data + " ");
            return;
        }

        // Recurse deeper into left and right subtrees, reducing distance by 1
        printNodesAtDistanceK(root.left,  k - 1);
        printNodesAtDistanceK(root.right, k - 1);
    }

    public static void main(String[] args) {
        /*
         * Constructing the following binary tree:
         *
         *            1
         *          /   \
         *         2     3
         *        / \   / \
         *       4   5 6   7
         *      /
         *     8
         */
        Node root = new Node(1);
        root.left        = new Node(2);
        root.right       = new Node(3);
        root.left.left   = new Node(4);
        root.left.right  = new Node(5);
        root.right.left  = new Node(6);
        root.right.right = new Node(7);
        root.left.left.left = new Node(8);

        System.out.println("=== Question 1: Nodes at Distance K from Root ===\n");
        System.out.println("Tree structure:");
        System.out.println("            1          ");
        System.out.println("          /   \\        ");
        System.out.println("         2     3        ");
        System.out.println("        / \\   / \\      ");
        System.out.println("       4   5 6   7      ");
        System.out.println("      /                 ");
        System.out.println("     8                  \n");

        for (int k = 0; k <= 4; k++) {
            System.out.print("Nodes at distance " + k + ": ");
            printNodesAtDistanceK(root, k);
            System.out.println();
        }
    }
}
