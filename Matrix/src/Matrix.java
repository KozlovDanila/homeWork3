public class Matrix implements Cloneable {
    private double[][] matrix;

    Matrix() {
        matrix = null;
    }

    Matrix(int height, int width) {
        if (height < 0 || width < 0) {
            matrix = null;
        } else {
            this.matrix = new double[height][width];
        }
    }

    Matrix(double[][] matrix) {
        if (matrix == null) {
            this.matrix = null;
        } else {
            this.matrix = new double[matrix.length][];
            for (int rowNumber = 0; rowNumber < matrix.length; ++rowNumber) {
                this.matrix[rowNumber] = new double[matrix[rowNumber].length];
                for (int columnNumber = 0; columnNumber < matrix[rowNumber].length; ++columnNumber) {
                    this.matrix[rowNumber][columnNumber] = matrix[rowNumber][columnNumber];
                }
            }
        }
    }

    public double getElement(int rowNumber, int columnNumber) {
        if (!checkIlligalCoordinates(rowNumber, columnNumber)) {
            throw new IllegalArgumentException("coordinates are not right");
        }
        return this.matrix[rowNumber][columnNumber];
    }

    public void setElement(double element, int rowNumber, int columnNumber) {
        if (!checkIlligalCoordinates(rowNumber, columnNumber)) {
            throw new IllegalArgumentException("coordinates are not right");
        }
        this.matrix[rowNumber][columnNumber] = element;
    }

    public int getRowSize() {
        return this.matrix.length;
    }

    public int getColumnSize(int rowSize) {
        return this.matrix[rowSize].length;
    }

    public Matrix multiply(Matrix right) {
        if (this == null || right == null) {
            throw new IllegalArgumentException("Row of left matrix is null");
        }
        if (this.getColumnSize(0) != right.getRowSize()) {
            throw new IllegalArgumentException("Row of left matrix not equal column of right matrix");
        }
        if (!this.checkCorrectMatrix() || !right.checkCorrectMatrix()) {
            throw new IllegalArgumentException("Matrices have not correct size");
        }

        Matrix result = new Matrix(this.getRowSize(), right.getColumnSize(0));
        for (int rowNumber = 0; rowNumber < this.getRowSize(); ++rowNumber) {
            for (int columnNumber = 0; columnNumber < right.getColumnSize(0); ++columnNumber) {
                result.setElement(calculateMultiplyRet(right, rowNumber, columnNumber), rowNumber, columnNumber);
            }
        }
        return result;
    }

    private double calculateMultiplyRet(Matrix right, int rowNumber, int columnNumber) {
        double[] rowVector = VectorUtils.getRowFromMatrix(this, rowNumber);
        double[] columnVector = VectorUtils.getColumnFromMatrix(right, columnNumber);
        return VectorUtils.multiplyVector(rowVector, columnVector);
    }

    public Matrix sum(Matrix matrix) {
        if (!this.checkEqualSize(matrix)) {
            throw new IllegalArgumentException("matrices have not equal size");
        }
        Matrix result = this.clone();
        for (int rowNumber = 0; rowNumber < this.getRowSize(); ++rowNumber) {
            for (int columNumber = 0; columNumber < this.getColumnSize(rowNumber); ++columNumber) {
                result.setElement(this.getElement(rowNumber, columNumber)
                        + matrix.getElement(rowNumber, columNumber), rowNumber, columNumber);
            }
        }
        return result;
    }

    @Override
    public Matrix clone() {
        Matrix clone = new Matrix();
        clone.matrix = new double[getRowSize()][];
        for (int rowNumber = 0; rowNumber < getRowSize(); rowNumber++) {
            clone.matrix[rowNumber] = new double[getColumnSize(rowNumber)];
            for (int columnNumber = 0; columnNumber < getColumnSize(rowNumber); columnNumber++) {
                clone.matrix[rowNumber][columnNumber] = getElement(rowNumber, columnNumber);
            }
        }
        return clone;
    }

    public void print() {
        for (int rowNumber = 0; rowNumber < this.getRowSize(); ++rowNumber) {
            for (int columnNumber = 0; columnNumber < this.getColumnSize(rowNumber); columnNumber++) {
                System.out.print(this.getElement(rowNumber, columnNumber) + " ");
            }
            System.out.println();
        }

    }

    private boolean checkEqualSize(Matrix matrix) {
        for (int rowNumber = 0; rowNumber < this.getRowSize(); ++rowNumber) {
            if (getColumnSize(rowNumber) != matrix.getColumnSize(rowNumber)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkSquar() {
        if (this.checkCorrectMatrix()) {
            return false;
        }

        if (this.getColumnSize(0) != this.getRowSize()) {
            return false;
        }
        return true;
    }


    public boolean checkEqual(Matrix matrix) {
        if (!checkEqualSize(matrix)) {
            return false;
        }
        for (int rowNumber = 0; rowNumber < this.getRowSize(); ++rowNumber) {
            for (int columnNumber = 0; columnNumber < this.getColumnSize(rowNumber); ++columnNumber) {
                if (matrix.getElement(rowNumber, columnNumber) != this.getElement(rowNumber, columnNumber)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkIlligalCoordinates(int rowNumber, int columnNumber) {
        if (rowNumber > getRowSize() || columnNumber > getColumnSize(0)) {
            return false;
        }
        if (rowNumber < 0 && columnNumber < 0) {
            return false;
        }
        return true;
    }

    private boolean checkCorrectMatrix() {
        for (int rowNumber = 0; rowNumber < this.getRowSize(); ++rowNumber) {
            if (this.getColumnSize(rowNumber) != getColumnSize(0)) {
                System.out.println(rowNumber);
                return false;
            }
        }
        return true;
    }
}