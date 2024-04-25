package ClosestPalindromicNumber;

public class ClosestPalindromicNumber {

    public static boolean isPalindromicNumber(int number) {
        String strNumber = Integer.toString(number);
        String strReverseNumber = "";
        int reverseNumber = 0;
        for (int i = strNumber.length() - 1; i >= 0; i--) {
            strReverseNumber += strNumber.charAt(i);
        }
        reverseNumber = Integer.parseInt(strReverseNumber);
        if (reverseNumber == number) {
            return true;
        }
        return false;
    }

    public static int getLowerPalindromic(int number) {
        if (isPalindromicNumber(number)) {
            return number;
        } else {
            return getLowerPalindromic(number - 1);
        }
    }

    public static int getUpperPalindromic(int number) {
        if (isPalindromicNumber(number)) {
            return number;
        } else {
            return getUpperPalindromic(number + 1);
        }
    }

    public static int getClosestPalindromic(int number) {
        int lowerPalindromic = getLowerPalindromic(number);
        int upperPalindromic = getUpperPalindromic(number);

        int distanceUpper = upperPalindromic - number;
        int distanceLower = number - lowerPalindromic;

        if (distanceLower > distanceUpper) {
            return upperPalindromic;
        } else {
            return lowerPalindromic;
        }
    }

    public static void main(String[] args){
        System.out.println(getClosestPalindromic(133));
    }

}
