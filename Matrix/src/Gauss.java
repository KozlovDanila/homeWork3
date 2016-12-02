

public class Gauss implements MethodSearchDeterminate {

    private Matrix tempMatrix;

    @Override
    public double search(Matrix matrix) {
        tempMatrix = matrix.clone();
        convertElementsInZeroUnderDiagonal();
        return sumDiagonal();
    }

    private void convertElementsInZeroUnderDiagonal() {
        for (int columnNumber = 0; columnNumber < tempMatrix.getColumnSize(0) - 1; ++columnNumber) {
            if (tempMatrix.getElement(columnNumber, columnNumber) == 0.0) {
                this.changeRow(columnNumber, columnNumber);
            }
            for (int rowNumber = columnNumber + 1; rowNumber < tempMatrix.getRowSize(); ++rowNumber) {
                double deduction;
                if (tempMatrix.getElement(rowNumber, columnNumber) == 0.0) {
                    deduction = 0.0;
                } else {
                    deduction = degreeTwoElementsOfMatrix(tempMatrix.getElement(rowNumber, columnNumber),
                            tempMatrix.getElement(columnNumber, columnNumber));
                }
                this.convertVector(rowNumber, columnNumber, deduction);
            }
        }
    }

    private double degreeTwoElementsOfMatrix(double first, double second) {
        return first / second;
    }

    private void changeRow(int rowNumber, int currentNumber) {
        int newNumber = searchRowForSwap(currentNumber);
        if (newNumber != currentNumber) {
            this.swapRow(rowNumber, newNumber, currentNumber);
        }

    }

    private void swapRow(int rowNumber, int newNumber, int currentNumber) {
        for (int i = currentNumber; i < tempMatrix.getRowSize(); ++i) {
            this.swap(rowNumber, newNumber, currentNumber);
        }

    }

    private void swap(int rowNumber, int firstChange, int secondChange) {
        double temp = tempMatrix.getElement(rowNumber, firstChange);
        tempMatrix.setElement(tempMatrix.getElement(rowNumber, secondChange), rowNumber, firstChange);
        tempMatrix.setElement(temp, rowNumber, secondChange);
    }

    private int searchRowForSwap(int currentNumber) {
        for (int columnNumber = currentNumber + 1; columnNumber < tempMatrix.getColumnSize(0); ++columnNumber) {
            if (tempMatrix.getElement(columnNumber, currentNumber) != 0.0) {
                return columnNumber;
            }
        }
        return currentNumber;
    }

    private void convertVector(int rowNumber, int columnNumber, double element) {

        for (int i = columnNumber; i < tempMatrix.getColumnSize(0); ++i) {
            double sub = tempMatrix.getElement(rowNumber, i);
            sub -= element * tempMatrix.getElement(columnNumber, i);
            tempMatrix.setElement(sub, rowNumber, i);
        }
    }

    private double sumDiagonal() {
        double sum = 1;
        for (int i = 0; i < tempMatrix.getRowSize(); ++i) {
            sum *= tempMatrix.getElement(i, i);
        }
        return sum;
    }

}
