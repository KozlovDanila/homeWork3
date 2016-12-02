import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;
import java.util.Random;

class ArrayUtil {

    public static int[] copyOf(int[] array, int newLenght) {
        if (newLenght < 0) {
            throw new IllegalArgumentException();
        }
        if (array == null) {
            throw new NullPointerException();
        }
        return Arrays.copyOf(array, newLenght);
    }


    public static boolean equal(int[] first, int[] second) {
        if (first == null || second == null) {
            return false;
        }
        if (first.length != second.length) {
            return false;
        }

        return Arrays.equals(first, second);
    }

    public static void mix(int[] array) {
        if (array == null) {
            throw new NullPointerException();
        }
        Random random = new Random();
        for (int i = 0; i < array.length; ++i) {
            swap(array, i, random.nextInt(array.length));
        }
    }

    public static String print(int[] array) {
        if (array == null) {
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

    public static int[] select(int[] array, Predicate predicate) {
        if (array == null) {
            return null;
        }

        int[] ret = null;
        for (int i : array) {
            if (predicate.apply(array[i])) {
                ret = ArrayUtils.add(ret, array[i]);
            }
        }
        return ret;
    }
}
