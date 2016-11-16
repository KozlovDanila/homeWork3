
class ArrayUtils {
 // просто проверка

    public static int[] copyOf(int[] array, int newLenght) {
        if (newLenght < 0) {
            return new int[0];
        }
        if (newLenght <= array.length) {
            return copyToIndex(array, newLenght);
        } else {
            return addTail(array, newLenght);
        }
    }

    private static int[] addTail(int[] array, int newLenght) {
        int[] tempArray = new int[newLenght];
        for (int i = 0; i < newLenght; ++i) {
            if (i < array.length) {
                tempArray[i] = array[i];
            } else {
                tempArray[i] = 0;
            }
        }
        return tempArray;
    }

    private static int[] copyToIndex(int[] array, int lenght) {
        int[] tempArray = new int[lenght];
        for (int i = 0; i < lenght; ++i) {
            tempArray[i] = array[i];
        }
        return tempArray;
    }

    public static boolean compare(int[] first, int[] second) {
        if (first.length != second.length) {
            return false;
        }

        int[] tempFirst = copyToIndex(first, first.length);
        int[] tempSecond = copyToIndex(second, second.length);
        sort(tempFirst);
        sort(tempSecond);
        for (int i = 0; i < tempFirst.length; ++i) {
            if (tempFirst[i] != tempSecond[i]) {
                return false;
            }
        }
        return true;
    }

    private static void sort(int[] array) {
        for (int compareNumber = 0; compareNumber < array.length - 1; ++compareNumber) {
            for (int currentNumber = compareNumber + 1; currentNumber < array.length; ++currentNumber) {
                if (array[compareNumber] > array[currentNumber]) {
                    swap(array, compareNumber, currentNumber);
                }
            }
        }

    }

    public static void mix(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            int rand = (int) Math.random() * array.length;
            swap(array, i, rand);
        }

    }

    private static void swap(int[] array, int firstChange, int secondChange) {
        int temp = array[firstChange];
        array[firstChange] = array[secondChange];
        array[secondChange] = temp;
    }

    public static String print(int[] array) {
        String str = "";
        for (int i = 0; i < array.length; ++i) {
            str = str + array[i] + " ";
        }
        return str;
    }

}
