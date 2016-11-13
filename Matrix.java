public class Matrix {
       private double[][] matrix;

    Matrix() {
        this.matrix = new double[0][];
    }

    Matrix(int height, int width) {
        if (height <= 0) {
            height = 1;
        }
        if (width <= 0) {
            width = 1;
        }
        this.matrix = new double[height][width];
        for (int rowNumber = 0; rowNumber < height; ++rowNumber) {
            for (int columnNumber = 0; columnNumber < width; ++columnNumber) {
                this.matrix[rowNumber][columnNumber] = 0.0;
            }
        }

    }

    Matrix(double[][] matrix) {
        this.matrix = new double[matrix.length][this.getMaxSizeRow(matrix)];
        for (int rowNumber = 0; rowNumber < this.getRowSize(); ++rowNumber) {
            for (int columnNumber = 0; columnNumber < this.getColumnSize(); ++columnNumber) {
                if (columnNumber < matrix[rowNumber].length) {
                    this.matrix[rowNumber][columnNumber] = matrix[rowNumber][columnNumber];
                } else {
                    this.matrix[rowNumber][columnNumber] = 0.0;
                }
            }
        }

    }

    Matrix(Matrix matrix) {
        this.matrix = new double[matrix.getRowSize()][matrix.getColumnSize()];
        for (int rowNumber = 0; rowNumber < matrix.getRowSize(); ++rowNumber) {
            for (int columnNumber = 0; columnNumber < matrix.getColumnSize(); ++columnNumber) {
                this.matrix[rowNumber][columnNumber] = matrix.getElement(rowNumber, columnNumber);
            }
        }

    }

    private int getMaxSizeRow(double[][] matrix) {
        int max = -1;
        for (int columnNumber = 0; columnNumber < matrix.length; ++columnNumber) {
            if (matrix[columnNumber].length > max) {
                max = matrix[columnNumber].length;
            }
        }
        return max;
    }

    public double getElement(int rowNumber, int columnNumber) {
        if (!checkIlligalValues(rowNumber, columnNumber)) {
            throw new IllegalArgumentException("N or M is invaled");
        }
        return this.matrix[rowNumber][columnNumber];
    }

    private boolean checkIlligalValues(int rowNumber, int columnNumber) {
        if (rowNumber > getRowSize() || columnNumber > getColumnSize()) {
            return false;
        }
        if (rowNumber < 0 && columnNumber < 0) {
            return false;
        }
        return true;
    }


    public void setElement(double element, int rowNumber, int columnNumber) {
        if (!checkIlligalValues(rowNumber, columnNumber)) {
            throw new IllegalArgumentException("N or M is invaled");
        }
        this.matrix[rowNumber][columnNumber] = element;
    }

    private int getRowSize() {
        return this.matrix.length;
    }

    private int getColumnSize() {
        return this.matrix[0].length;
    }

    private boolean isNull() {
        return this.getRowSize() == 0 || this.getColumnSize() == 0;
    }

    public Matrix multiply(Matrix right) {
        if (this.isNull() || right.isNull()) {
            throw new IllegalArgumentException("Row of left matrix not equal column of right matrix");
        }
        if (this.getColumnSize() != right.getRowSize()) {
            throw new IllegalArgumentException("Row of left matrix not equal column of right matrix");
        }

        Matrix result = new Matrix(this.getRowSize(), right.getColumnSize());
        for (int rowNumber = 0; rowNumber < this.getRowSize(); ++rowNumber) {
            for (int columnNumber = 0; columnNumber < right.getColumnSize(); ++columnNumber) {
                result.setElement(this.multiplyVector(this.getRow(rowNumber), this.getColumn(right, columnNumber)),
                                     rowNumber, columnNumber);
            }
        }
        return result;
    }

    private double multiplyVector(double[] left, double[] right) {
        int multiply = 0;
        for (int rowNumber = 0; rowNumber < left.length; ++rowNumber) {
            multiply += left[rowNumber] * right[rowNumber];
        }
        return multiply;
    }

    private double[] getColumn(Matrix matrix, int columnNumber) {
        double[] result = new double[this.getColumnSize()];
        for (int rowNumber = 0; rowNumber < matrix.getRowSize(); ++rowNumber) {
            result[rowNumber] = matrix.getElement(rowNumber, columnNumber);
        }
        return result;
    }

    private double[] getRow(int rowNumber) {
        return this.matrix[rowNumber];
    }

    public Matrix sum(Matrix matrix) {
        if (!this.checkEqualSize(matrix)) {
            throw new IllegalArgumentException("matrices have not equal size");
        }
        Matrix result = new Matrix(this.getRowSize(), this.getColumnSize());
        for (int rowNumber = 0; rowNumber < this.getRowSize(); ++rowNumber) {
            for (int columNumber = 0; columNumber < this.getColumnSize(); ++columNumber) {
                result.setElement(this.getElement(rowNumber, columNumber)
                        + matrix.getElement(rowNumber, columNumber), rowNumber, columNumber);
            }
        }
        return result;
    }

    private boolean checkEqualSize(Matrix matrix) {
        return this.getRowSize() == matrix.getRowSize() && this.getColumnSize() == matrix.getColumnSize();
    }

    public double getDeterminant() {
        if (this.isNull()) {
            throw new IllegalArgumentException("Is not a matrix");
        }
        if (!this.checkSquar()) {
            throw new IllegalArgumentException("N not equal M");
        }
        Matrix tempMatrix = new Matrix(this);
        tempMatrix.convertElementsInZeroUnderDiagonal();
        return tempMatrix.sumDiagonal();

    }

    private double sumDiagonal() {
        double sum = 1;
        for (int i = 0; i < this.getRowSize(); ++i) {
            sum *= this.getElement(i, i);
        }
        return sum;
    }

    private void convertElementsInZeroUnderDiagonal() {
        for (int columnNumber = 0; columnNumber < this.getColumnSize() - 1; ++columnNumber) {
            if (this.getElement(columnNumber, columnNumber) == 0.0) {
                this.changeRow(columnNumber, columnNumber);
            }
            for (int rowNumber = columnNumber + 1; rowNumber < this.getRowSize(); ++rowNumber) {
                double deduction;
                if (this.getElement(rowNumber, columnNumber) == 0.0) {
                    deduction = 0.0;
                } else {
                    deduction = degreeTwoElementsOfMatrix(getElement(rowNumber, columnNumber),
                            getElement(columnNumber, columnNumber));
                }
                this.convertVector(rowNumber, columnNumber, deduction);
            }
        }

    }

    private double degreeTwoElementsOfMatrix(double first, double second) {
        return first / second;
    }

    private void changeRow(int rowNumber, int currentNumber) {
        int newNumber = this.searchRowForSwap(currentNumber);
        if (newNumber != currentNumber) {
            this.swapRow(rowNumber, newNumber, currentNumber);
        }

    }

    private void swapRow(int rowNumber, int newNumber, int currentNumber) {
        for (int i = currentNumber; i < this.getRowSize(); ++i) {
            this.swap(rowNumber, newNumber, currentNumber);
        }

    }

    private void swap(int rowNumber, int firstChange, int secondChange) {
        double temp = this.matrix[rowNumber][firstChange];
        this.matrix[rowNumber][firstChange] = this.matrix[rowNumber][secondChange];
        this.matrix[rowNumber][secondChange] = temp;
    }

    private int searchRowForSwap(int currentNumber) {
        for (int columnNumber = currentNumber + 1; columnNumber < this.getColumnSize(); ++columnNumber) {
            if (this.getElement(columnNumber, currentNumber) != 0.0) {
                return columnNumber;
            }
        }
        return currentNumber;
    }

    private void convertVector(int rowNumber, int columnNumber, double element) {
        for (int i = columnNumber; i < this.getColumnSize(); ++i) {
            this.matrix[rowNumber][i] -= element * this.matrix[columnNumber][i];
        }

    }

    private boolean checkSquar() {
        return this.getColumnSize() == this.getRowSize();
    }

    public void print() {
        for (int rowNumber = 0; rowNumber < this.getRowSize(); ++rowNumber) {
            for (int columnNumber = 0; columnNumber < this.getColumnSize(); columnNumber++) {
                System.out.print(this.getElement(rowNumber, columnNumber) + " ");
            }
            System.out.println();
        }

    }

    public boolean equal(Matrix matrix) {
        if (!checkEqualSize(matrix)) {
            return false;
        }
        for (int rowNumber = 0; rowNumber < this.getRowSize(); ++rowNumber) {
            for (int columnNumber = 0; columnNumber < this.getColumnSize(); ++columnNumber) {
                if (matrix.getElement(rowNumber, columnNumber) != this.getElement(rowNumber, columnNumber)) {
                    return false;
                }
            }
        }
        return true;
    }

}