// Problem Link: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/?envType=problem-list-v2&envId=divide-and-conquer

/**
 * Definition for a binary tree node.
 * Provided by LeetCode for Tree problems.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    // Default constructor
    TreeNode() {}

    // Constructor with a value
    TreeNode(int val) { this.val = val; }

    // Constructor with a value and child nodes
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * Solution class to convert a strictly increasing sorted array into a
 * height-balanced binary search tree (BST).
 * * A height-balanced binary tree is a binary tree in which the depth of the
 * two subtrees of every node never differs by more than one.
 */
public class ConvertSortedArrayToBinarySearchTree {

    /**
     * Public method to initiate the conversion.
     * * @param nums The sorted integer array.
     * @return The root of the constructed height-balanced BST.
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        // Handle edge case where the array is empty or null
        if (nums == null || nums.length == 0) {
            return null;
        }

        // Call the recursive helper function with the full array bounds
        return createBST(nums, 0, nums.length - 1);
    }

    /**
     * Recursive helper method that uses divide-and-conquer to build the BST.
     * By choosing the middle element of the current range as the root,
     * we ensure the tree remains height-balanced.
     * * @param nums The sorted integer array.
     * @param l The left boundary index of the current range.
     * @param r The right boundary index of the current range.
     * @return The root node of the BST constructed from the given range.
     */
    private TreeNode createBST(int[] nums, int l, int r) {
        // Base Case: If the left index exceeds the right index, the range is invalid
        // This means there are no elements left to form a subtree, so return null.
        if (l > r) {
            return null;
        }

        // Calculate the middle index safely to prevent integer overflow.
        // The middle element guarantees the left and right subtrees will have
        // roughly the same number of nodes.
        int mid = l + (r - l) / 2;

        // Create the root node for the current range using the middle element.
        TreeNode root = new TreeNode(nums[mid]);

        // Recursively build the left subtree using elements to the left of 'mid'
        root.left = createBST(nums, l, mid - 1);

        // Recursively build the right subtree using elements to the right of 'mid'
        root.right = createBST(nums, mid + 1, r);

        // Return the constructed subtree root back to the caller
        return root;
    }
}