public class MatrixUtils {
    public double getDeterminant(Matrix matrix, MethodSearchDeterminate method) {
        if (this == null) {
            throw new IllegalArgumentException("Is not a matrix");
        }
        if (matrix.checkSquar()) {
            throw new IllegalArgumentException("Row not equal Column");
        }
        return method.search(matrix);
    }
}
