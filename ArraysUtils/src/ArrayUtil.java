import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;
import java.util.Random;

class ArrayUtil {

    public static int[] copyOf(int[] array, int newLenght) {
        if (checkNegativeValue(newLenght)) {
            throw new IllegalArgumentException();
        }
        if (isNull(array)) {
            throw new NullPointerException();
        }
        return Arrays.copyOf(array, newLenght);
    }


    public static boolean compare(int[] first, int[] second) {
        if (isNull(first) || isNull(second)) {
            return false;
        }

        if (first.length != second.length) {
            return false;
        }

        int[] tempFirst = Arrays.copyOf(first, first.length);
        int[] tempSecond = Arrays.copyOf(second, second.length);
        Arrays.sort(tempFirst);
        Arrays.sort(tempSecond);
        for (int i = 0; i < tempFirst.length; ++i) {
            if (tempFirst[i] != tempSecond[i]) {
                return false;
            }
        }
        return true;
    }

    public static void mix(int[] array) {
        if (isNull(array)) {
            throw new NullPointerException();
        }
        Random random = new Random();
        for (int i = 0; i < array.length; ++i) {
            swap(array, i, random.nextInt(array.length - 1));
        }
    }

    public static String print(int[] array) {
        if (isNull(array)) {
            throw new NullPointerException();
        }
        String str = "";
        for (int i = 0; i < array.length; ++i) {
            str += array[i] + " ";
        }
        return str;
    }

    private static void swap(int[] array, int firstChange, int secondChange) {
        int temp = array[firstChange];
        array[firstChange] = array[secondChange];
        array[secondChange] = temp;
    }

    private static boolean isNull(int[] array) {
        return array == null;
    }

    private static boolean checkNegativeValue(int value) {
        return value < 0;
    }

    public static int[] select(int[] array, Predicate predicate) {
        if (isNull(array)) {
            throw new NullPointerException("array is null");
        }

        int[] ret = null;
        for (int i = 0; i < array.length; i++) {
            if (predicate.method(array[i])) {
                ret = ArrayUtils.add(ret, array[i]);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(print(select(new int[]{1,2,-4,5,-1,3}, new EasyNumber())));
    }
}
