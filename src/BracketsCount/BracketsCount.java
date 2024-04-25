package BracketsCount;

public class BracketsCount {
    public static int combinationCount = 0;
    public static boolean checkBrackets(int[] array) {
        int sum = 0;
        int close = 0;
        int open = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            if (array[i] == 0) {
                close++;
            } else {
                open++;
            }
            if (close > open) {
                return false;
            }
        }
        if (sum == array.length / 2) {
            return true;
        } else {
            return false;
        }
    }

    public static void findCombination(int[] array, int index) {
        if (index == array.length) {
            if (checkBrackets(array)) {
                combinationCount++;
            }
            return;
        }
        array[index] = 0;
        findCombination(array, index + 1);
        array[index] = 1;
        findCombination(array, index + 1);
    }

    public static void main(String[] args) {
        int BRACKET_COUNT = 5;
        int[] bracketArray = new int[BRACKET_COUNT * 2];
        findCombination(bracketArray, 0);
        System.out.println(combinationCount);

    }
}
