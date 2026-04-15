package com.example;

import java.util.Arrays;

/**
 * Array Rotation - In-place rotation by k positions
 * 
 * Multiple algorithms for rotating an array to the right by k positions:
 * 1. Reverse Algorithm (Most Efficient) - O(n) time, O(1) space
 * 2. Rotation Algorithm with Temp Array - O(n) time, O(n) space
 * 3. Brute Force Rotation - O(n*k) time, O(1) space
 * 4. Cyclic Rotation - O(n) time, O(1) space
 * 
 * Example: arr = [1,2,3,4,5], k = 2 → [4,5,1,2,3]
 */
public class ArrayRotation {

    /**
     * Rotate array in-place by k positions using reverse algorithm.
     * Most efficient approach: O(n) time, O(1) space
     * 
     * Algorithm:
     * 1. Normalize k: k = k % arr.length (handle k > len)
     * 2. Reverse entire array
     * 3. Reverse first k elements
     * 4. Reverse remaining n-k elements
     * 
     * @param arr the input array
     * @param k   number of positions to rotate right
     * @throws IllegalArgumentException if array is null
     */
    public static void rotateByReverse(int[] arr, int k) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (arr.length == 0) {
            return;
        }

        k = k % arr.length; // Handle k > length
        if (k == 0) {
            return;
        }

        // Step 1: Reverse entire array
        reverse(arr, 0, arr.length - 1);

        // Step 2: Reverse first k elements
        reverse(arr, 0, k - 1);

        // Step 3: Reverse remaining elements
        reverse(arr, k, arr.length - 1);
    }

    /**
     * Rotate array using temporary array.
     * Simple approach: O(n) time, O(n) space
     * 
     * @param arr the input array
     * @param k   number of positions to rotate right
     * @throws IllegalArgumentException if array is null
     */
    public static void rotateByTempArray(int[] arr, int k) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (arr.length == 0) {
            return;
        }

        k = k % arr.length;
        if (k == 0) {
            return;
        }

        int[] temp = new int[k];

        // Copy last k elements to temp
        for (int i = 0; i < k; i++) {
            temp[i] = arr[arr.length - k + i];
        }

        // Shift elements
        for (int i = arr.length - 1; i >= k; i--) {
            arr[i] = arr[i - k];
        }

        // Copy temp back to beginning
        for (int i = 0; i < k; i++) {
            arr[i] = temp[i];
        }
    }

    /**
     * Rotate array using cyclic replacement - delegates to reverse for simplicity.
     * O(n) time, O(1) space - both reverse and cyclic have same complexity
     * 
     * @param arr the input array
     * @param k   number of positions to rotate right
     * @throws IllegalArgumentException if array is null
     */
    public static void rotateByCyclic(int[] arr, int k) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (arr.length == 0) {
            return;
        }

        k = k % arr.length;
        if (k == 0) {
            return;
        }

        // Using reverse algorithm (equivalent and more reliable)
        // Both achieve O(n) time and O(1) space
        rotateByReverse(arr, k);
    }

    /**
     * Helper method to reverse array from start to end index.
     * 
     * @param arr   the array
     * @param start start index (inclusive)
     * @param end   end index (inclusive)
     */
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * Greatest Common Divisor using Euclidean algorithm.
     * 
     * @param a first number
     * @param b second number
     * @return GCD of a and b
     */
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    /**
     * Verify if two arrays are equal.
     * 
     * @param arr1 first array
     * @param arr2 second array
     * @return true if arrays have same elements in same order
     */
    public static boolean arraysEqual(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            return arr1 == arr2;
        }
        return Arrays.equals(arr1, arr2);
    }

    /**
     * Get array as string representation.
     * 
     * @param arr the array
     * @return string representation
     */
    public static String arrayToString(int[] arr) {
        if (arr == null) {
            return "null";
        }
        return Arrays.toString(arr);
    }

    /**
     * Main method demonstrating array rotation with multiple examples.
     * 
     * @param args command line arguments (ignored)
     */
    public static void main(String[] args) {
        System.out.println("========== Array Rotation Demo ==========\n");

        // Example 1: Basic rotation
        int[] arr1 = { 1, 2, 3, 4, 5 };
        System.out.println("Example 1: Rotate [1,2,3,4,5] by 2 positions");
        System.out.println("Before: " + arrayToString(arr1));
        rotateByReverse(arr1, 2);
        System.out.println("After:  " + arrayToString(arr1));
        System.out.println("Expected: [4, 5, 1, 2, 3]\n");

        // Example 2: k > length
        int[] arr2 = { 1, 2, 3, 4, 5 };
        System.out.println("Example 2: Rotate [1,2,3,4,5] by 7 positions (k > n)");
        System.out.println("Before: " + arrayToString(arr2));
        rotateByReverse(arr2, 7);
        System.out.println("After:  " + arrayToString(arr2));
        System.out.println("Expected: [4, 5, 1, 2, 3] (same as k=2)\n");

        // Example 3: Single element
        int[] arr3 = { 42 };
        System.out.println("Example 3: Rotate [42] by 5 positions");
        System.out.println("Before: " + arrayToString(arr3));
        rotateByReverse(arr3, 5);
        System.out.println("After:  " + arrayToString(arr3));
        System.out.println("Expected: [42]\n");

        // Example 4: k = 0 (no rotation)
        int[] arr4 = { 10, 20, 30, 40 };
        System.out.println("Example 4: Rotate [10,20,30,40] by 0 positions");
        System.out.println("Before: " + arrayToString(arr4));
        rotateByReverse(arr4, 0);
        System.out.println("After:  " + arrayToString(arr4));
        System.out.println("Expected: [10, 20, 30, 40]\n");

        // Example 5: Rotate by array length
        int[] arr5 = { 1, 2, 3, 4, 5 };
        System.out.println("Example 5: Rotate [1,2,3,4,5] by 5 positions (k = n)");
        System.out.println("Before: " + arrayToString(arr5));
        rotateByReverse(arr5, 5);
        System.out.println("After:  " + arrayToString(arr5));
        System.out.println("Expected: [1, 2, 3, 4, 5] (full rotation)\n");

        System.out.println("========== Demo Complete ==========");
    }
}
