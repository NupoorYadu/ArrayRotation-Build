package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Array Rotation - Comprehensive Automation Tests")
public class ArrayRotationTest {

    // ==================== Basic Rotation Tests ====================

    @Test
    @DisplayName("Basic: Rotate array [1,2,3,4,5] by 2 positions")
    public void testBasicRotation() {
        int[] arr = { 1, 2, 3, 4, 5 };
        ArrayRotation.rotateByReverse(arr, 2);
        assertArrayEquals(new int[] { 4, 5, 1, 2, 3 }, arr);
    }

    @Test
    @DisplayName("Basic: Rotate single element array")
    public void testSingleElement() {
        int[] arr = { 42 };
        ArrayRotation.rotateByReverse(arr, 5);
        assertArrayEquals(new int[] { 42 }, arr);
    }

    @Test
    @DisplayName("Basic: Rotate two element array")
    public void testTwoElements() {
        int[] arr = { 1, 2 };
        ArrayRotation.rotateByReverse(arr, 1);
        assertArrayEquals(new int[] { 2, 1 }, arr);
    }

    // ==================== K Greater Than Length Tests ====================

    @Test
    @DisplayName("Edge case: k > array length")
    public void testKGreaterThanLength() {
        int[] arr = { 1, 2, 3, 4, 5 };
        ArrayRotation.rotateByReverse(arr, 7); // 7 % 5 = 2
        assertArrayEquals(new int[] { 4, 5, 1, 2, 3 }, arr);
    }

    @Test
    @DisplayName("Edge case: k = 2 * length")
    public void testKEqualsDoubleLength() {
        int[] arr = { 1, 2, 3, 4 };
        ArrayRotation.rotateByReverse(arr, 8); // 8 % 4 = 0 (full rotation)
        assertArrayEquals(new int[] { 1, 2, 3, 4 }, arr);
    }

    @Test
    @DisplayName("Edge case: k = 0 (no rotation)")
    public void testZeroRotation() {
        int[] arr = { 10, 20, 30, 40 };
        ArrayRotation.rotateByReverse(arr, 0);
        assertArrayEquals(new int[] { 10, 20, 30, 40 }, arr);
    }

    // ==================== Full Rotation Tests ====================

    @Test
    @DisplayName("Full rotation: k = array length (circle back)")
    public void testFullRotation() {
        int[] arr = { 1, 2, 3, 4, 5 };
        ArrayRotation.rotateByReverse(arr, 5);
        assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, arr);
    }

    @Test
    @DisplayName("Half rotation: k = length / 2")
    public void testHalfRotation() {
        int[] arr = { 1, 2, 3, 4 };
        ArrayRotation.rotateByReverse(arr, 2);
        assertArrayEquals(new int[] { 3, 4, 1, 2 }, arr);
    }

    // ==================== Temp Array Method Tests ====================

    @Test
    @DisplayName("Temp array: Rotate [1,2,3,4,5] by 2")
    public void testRotateByTempArray() {
        int[] arr = { 1, 2, 3, 4, 5 };
        ArrayRotation.rotateByTempArray(arr, 2);
        assertArrayEquals(new int[] { 4, 5, 1, 2, 3 }, arr);
    }

    @Test
    @DisplayName("Temp array: Compare with reverse algorithm")
    public void testTempArrayVsReverse() {
        int[] arr1 = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int[] arr2 = { 1, 2, 3, 4, 5, 6, 7, 8 };

        ArrayRotation.rotateByReverse(arr1, 3);
        ArrayRotation.rotateByTempArray(arr2, 3);

        assertArrayEquals(arr1, arr2, "Both algorithms should produce same result");
    }

    // ==================== Cyclic Algorithm Tests ====================

    @Test
    @DisplayName("Cyclic: Rotate [1,2,3,4,5] by 2")
    public void testRotateByCyclic() {
        int[] arr = { 1, 2, 3, 4, 5 };
        ArrayRotation.rotateByCyclic(arr, 2);
        assertArrayEquals(new int[] { 4, 5, 1, 2, 3 }, arr);
    }

    @Test
    @DisplayName("Cyclic: Compare with reverse algorithm")
    public void testCyclicVsReverse() {
        int[] arr1 = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int[] arr2 = { 1, 2, 3, 4, 5, 6, 7, 8 };

        ArrayRotation.rotateByReverse(arr1, 3);
        ArrayRotation.rotateByCyclic(arr2, 3);

        assertArrayEquals(arr1, arr2, "Cyclic algorithm should match reverse");
    }

    // ==================== Negative and Zero Values Tests ====================

    @Test
    @DisplayName("Negative values: Rotate array with negative integers")
    public void testNegativeValues() {
        int[] arr = { -1, -2, -3, -4, -5 };
        ArrayRotation.rotateByReverse(arr, 2);
        assertArrayEquals(new int[] { -4, -5, -1, -2, -3 }, arr);
    }

    @Test
    @DisplayName("Mixed values: Positive, negative, and zero")
    public void testMixedValues() {
        int[] arr = { -5, 0, 5, -3, 10 };
        ArrayRotation.rotateByReverse(arr, 2);
        assertArrayEquals(new int[] { -3, 10, -5, 0, 5 }, arr);
    }

    @Test
    @DisplayName("Zero values: Array of all zeros")
    public void testAllZeros() {
        int[] arr = { 0, 0, 0, 0 };
        ArrayRotation.rotateByReverse(arr, 2);
        assertArrayEquals(new int[] { 0, 0, 0, 0 }, arr);
    }

    // ==================== Large Array Tests ====================

    @Test
    @DisplayName("Large array: 1000 elements, rotate by 250")
    public void testLargeArray() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = i;
        }

        ArrayRotation.rotateByReverse(arr, 250);

        // Check specific elements
        assertEquals(750, arr[0], "First element should be 750");
        assertEquals(999, arr[249], "Element at 249 should be 999");
        assertEquals(0, arr[250], "Element at 250 should be 0");
    }

    @Test
    @DisplayName("Performance: 10000 elements rotation")
    public void testPerformanceLargeRotation() {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) {
            arr[i] = i;
        }

        long startTime = System.nanoTime();
        ArrayRotation.rotateByReverse(arr, 1234);
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;
        assertTrue(durationMs < 100, "Rotation should complete within 100ms for 10000 elements");
    }

    // ==================== Exception Tests ====================

    @Test
    @DisplayName("Exception: Null array in rotateByReverse")
    public void testNullArrayReverse() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ArrayRotation.rotateByReverse(null, 2));
        assertEquals("Array cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Exception: Null array in rotateByTempArray")
    public void testNullArrayTempArray() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ArrayRotation.rotateByTempArray(null, 2));
        assertEquals("Array cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Exception: Null array in rotateByCyclic")
    public void testNullArrayCyclic() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ArrayRotation.rotateByCyclic(null, 2));
        assertEquals("Array cannot be null", exception.getMessage());
    }

    // ==================== Empty Array Tests ====================

    @Test
    @DisplayName("Empty array: Rotation on empty array")
    public void testEmptyArray() {
        int[] arr = {};
        ArrayRotation.rotateByReverse(arr, 5);
        assertArrayEquals(new int[] {}, arr);
    }

    // ==================== Helper Method Tests ====================

    @Test
    @DisplayName("Helper: arraysEqual with same arrays")
    public void testArraysEqualSame() {
        int[] arr1 = { 1, 2, 3 };
        int[] arr2 = { 1, 2, 3 };
        assertTrue(ArrayRotation.arraysEqual(arr1, arr2));
    }

    @Test
    @DisplayName("Helper: arraysEqual with different arrays")
    public void testArraysEqualDifferent() {
        int[] arr1 = { 1, 2, 3 };
        int[] arr2 = { 1, 2, 4 };
        assertFalse(ArrayRotation.arraysEqual(arr1, arr2));
    }

    @Test
    @DisplayName("Helper: arraysEqual with null arrays")
    public void testArraysEqualNull() {
        assertTrue(ArrayRotation.arraysEqual(null, null));
        assertFalse(ArrayRotation.arraysEqual(new int[] { 1 }, null));
        assertFalse(ArrayRotation.arraysEqual(null, new int[] { 1 }));
    }

    @Test
    @DisplayName("Helper: arrayToString representation")
    public void testArrayToString() {
        int[] arr = { 1, 2, 3, 4, 5 };
        String result = ArrayRotation.arrayToString(arr);
        assertEquals("[1, 2, 3, 4, 5]", result);
    }

    @Test
    @DisplayName("Helper: arrayToString with null")
    public void testArrayToStringNull() {
        String result = ArrayRotation.arrayToString(null);
        assertEquals("null", result);
    }

    // ==================== Complex Rotation Scenarios ====================

    @Test
    @DisplayName("Scenario: Palindromic array rotation")
    public void testPalindromicRotation() {
        int[] arr = { 1, 2, 3, 2, 1 };
        ArrayRotation.rotateByReverse(arr, 1);
        assertArrayEquals(new int[] { 1, 1, 2, 3, 2 }, arr);
    }

    @Test
    @DisplayName("Scenario: All same element rotation")
    public void testSameElementRotation() {
        int[] arr = { 5, 5, 5, 5, 5 };
        ArrayRotation.rotateByReverse(arr, 3);
        assertArrayEquals(new int[] { 5, 5, 5, 5, 5 }, arr);
    }

    @Test
    @DisplayName("Scenario: Ascending array rotation")
    public void testAscendingArrayRotation() {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        ArrayRotation.rotateByReverse(arr, 3);
        assertArrayEquals(new int[] { 8, 9, 10, 1, 2, 3, 4, 5, 6, 7 }, arr);
    }

    @Test
    @DisplayName("Algorithm comparison: All three methods consistency")
    public void testAlgorithmConsistency() {
        int[] arr1 = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int[] arr2 = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int[] arr3 = { 1, 2, 3, 4, 5, 6, 7, 8 };

        int k = 3;
        ArrayRotation.rotateByReverse(arr1, k);
        ArrayRotation.rotateByTempArray(arr2, k);
        ArrayRotation.rotateByCyclic(arr3, k);

        assertArrayEquals(arr1, arr2, "Reverse and Temp Array should match");
        assertArrayEquals(arr2, arr3, "Temp Array and Cyclic should match");
        assertArrayEquals(arr1, arr3, "All algorithms should produce same result");
    }

    // ==================== Boundary Tests ====================

    @Test
    @DisplayName("Boundary: Integer.MAX_VALUE rotation")
    public void testMaxIntegerRotation() {
        int[] arr = { 1, 2, 3, 4, 5 };
        // Should handle very large k values gracefully
        ArrayRotation.rotateByReverse(arr, Integer.MAX_VALUE);
        // Result depends on (MAX_VALUE % 5)
        assertNotNull(arr);
        assertEquals(5, arr.length);
    }

    @Test
    @DisplayName("Multi-step: Multiple rotations on same array")
    public void testMultipleRotations() {
        int[] arr = { 1, 2, 3, 4, 5 };

        // Rotate by 2
        ArrayRotation.rotateByReverse(arr, 2);
        assertArrayEquals(new int[] { 4, 5, 1, 2, 3 }, arr);

        // Rotate by 1 more
        ArrayRotation.rotateByReverse(arr, 1);
        assertArrayEquals(new int[] { 3, 4, 5, 1, 2 }, arr);

        // Total rotation is 3, verify
        int[] expected = { 1, 2, 3, 4, 5 };
        ArrayRotation.rotateByReverse(expected, 3);
        assertArrayEquals(expected, arr);
    }
}
