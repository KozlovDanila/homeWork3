public class VectorUtils {

    public static double multiplyVector(double[] left, double[] right) {
        int multiply = 0;
        for (int rowNumber = 0; rowNumber < left.length; ++rowNumber) {
            multiply += left[rowNumber] * right[rowNumber];
        }
        return multiply;
    }


    public static double[] getColumnFromMatrix(Matrix matrix, int columnNumber) {
        double[] result = new double[matrix.getRowSize()];
        for (int rowNumber = 0; rowNumber < matrix.getRowSize(); ++rowNumber) {
            result[rowNumber] = matrix.getElement(rowNumber, columnNumber);
        }
        return result;
    }

    public static double[] getRowFromMatrix(Matrix matrix, int rowNumber) {
        double[] result = new double[matrix.getRowSize()];
        for (int columnNumber = 0; columnNumber < matrix.getRowSize(); ++columnNumber) {
            result[columnNumber] = matrix.getElement(rowNumber, columnNumber);
        }
        return result;
    }

}
