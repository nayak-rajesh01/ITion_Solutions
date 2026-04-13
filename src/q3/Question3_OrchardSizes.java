package q3;

/**
 * Question 3: Find sizes of all orchards in a character matrix.
 *
 * An orchard is a group of 'T' (Tree) cells connected vertically,
 * horizontally, OR diagonally — i.e., 8-directional connectivity.
 *
 * Approach (Flood Fill / DFS):
 * - Iterate over every cell in the matrix.
 * - When an unvisited 'T' cell is found, launch a DFS from that cell.
 * - The DFS visits all 8 neighbours recursively, marking cells visited
 *   (by changing 'T' → 'V') to avoid revisiting.
 * - The DFS returns the count of connected trees (orchard size).
 * - Collect all sizes, sort descending, and print them.
 *
 * Time Complexity : O(R × C) — each cell visited at most once.
 * Space Complexity: O(R × C) — recursion stack in worst case (all trees).
 *
 * Example:
 *   Input:
 *     O T O O
 *     O T O T
 *     T T O T
 *     O T O T
 *
 *   Orchard 1 (connected via column 1 and diagonals):
 *     (0,1),(1,1),(2,0),(2,1),(3,1) → size 5
 *   Orchard 2 (right side):
 *     (1,3),(2,3),(3,3) → size 3
 *
 *   Output: 5, 3
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question3_OrchardSizes {

    // All 8 directions: N, NE, E, SE, S, SW, W, NW
    static final int[] DR = {-1, -1,  0,  1, 1,  1,  0, -1};
    static final int[] DC = { 0,  1,  1,  1, 0, -1, -1, -1};

    /**
     * DFS from cell (r, c), marks visited cells, returns count of trees in this orchard.
     */
    static int dfs(char[][] grid, int r, int c) {
        // Out of bounds or not a tree → stop
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return 0;
        if (grid[r][c] != 'T') return 0;

        // Mark as visited
        grid[r][c] = 'V';
        int count = 1; // count this tree

        // Explore all 8 neighbours
        for (int d = 0; d < 8; d++) {
            count += dfs(grid, r + DR[d], c + DC[d]);
        }
        return count;
    }

    /**
     * Computes sizes of all orchards in the given matrix.
     */
    static List<Integer> orchardSizes(char[][] grid) {
        List<Integer> sizes = new ArrayList<>();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 'T') {
                    int size = dfs(grid, r, c);
                    sizes.add(size);
                }
            }
        }

        // Sort descending so largest orchard is first
        sizes.sort(Collections.reverseOrder());
        return sizes;
    }

    /** Helper to print the matrix visually. */
    static void printMatrix(char[][] grid, String label) {
        System.out.println(label);
        for (char[] row : grid) {
            for (int c = 0; c < row.length; c++) {
                System.out.print(row[c] + (c < row.length - 1 ? " " : ""));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== Question 3: Orchard Sizes ===\n");

        // --- Example from the problem ---
        char[][] grid1 = {
                {'O', 'T', 'O', 'O'},
                {'O', 'T', 'O', 'T'},
                {'T', 'T', 'O', 'T'},
                {'O', 'T', 'O', 'T'}
        };

        // Print original (make a copy for display since DFS mutates)
        char[][] display1 = {
                {'O', 'T', 'O', 'O'},
                {'O', 'T', 'O', 'T'},
                {'T', 'T', 'O', 'T'},
                {'O', 'T', 'O', 'T'}
        };
        printMatrix(display1, "Input Matrix:");

        List<Integer> sizes1 = orchardSizes(grid1);

        System.out.print("Orchard Sizes (descending): ");
        for (int i = 0; i < sizes1.size(); i++) {
            System.out.print(sizes1.get(i) + (i < sizes1.size() - 1 ? ", " : ""));
        }
        System.out.println("\n");

        System.out.println("Explanation:");
        System.out.println("  Orchard 1 → cells (0,1),(1,1),(2,0),(2,1),(3,1) — connected via adjacency/diagonal → size 5");
        System.out.println("  Orchard 2 → cells (1,3),(2,3),(3,3)             — connected vertically           → size 3");
        System.out.println();

        // --- Additional test ---
        char[][] grid2 = {
                {'T', 'O', 'T'},
                {'O', 'T', 'O'},
                {'T', 'O', 'T'}
        };
        char[][] display2 = {
                {'T', 'O', 'T'},
                {'O', 'T', 'O'},
                {'T', 'O', 'T'}
        };
        printMatrix(display2, "Additional Test (all diagonally connected):");
        List<Integer> sizes2 = orchardSizes(grid2);
        System.out.print("Orchard Sizes: ");
        for (int i = 0; i < sizes2.size(); i++) {
            System.out.print(sizes2.get(i) + (i < sizes2.size() - 1 ? ", " : ""));
        }
        System.out.println(" (all 5 trees form one orchard via diagonal connections)\n");
    }
}

