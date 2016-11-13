
import org.junit.Assert;
import org.junit.Test;

public class MatrixTest {
    Matrix matrix = new Matrix(new double[][]{{1.0D, 2.0D}, {3.0D, 4.0D}});

    public MatrixTest() {
    }

    @Test
    public void checkGetElementWithNegativeValue() throws Exception {
    }

    @Test
    public void checkGetElementWithCorrecrtValue() throws Exception {
        Assert.assertTrue(Math.abs(this.matrix.getElement(1, 1) - 4.0D) < 1.0E-5D);
    }

    @Test
    public void checkSetElement() throws Exception {
        this.matrix.setElement(12.0D, 1, 1);
        Assert.assertTrue(Math.abs(this.matrix.getElement(1, 1) - 12.0D) < 1.0E-5D);
    }

    @Test
    public void checkMultiply() throws Exception {
        Matrix temp = this.matrix.multiply(new Matrix(new double[][]{{1.0D}, {2.0D}}));
        Assert.assertTrue(temp.equal(new Matrix(new double[][]{{5.0D}, {11.0D}})));
    }

    @Test
    public void checkSum() throws Exception {
        Matrix temp = this.matrix.sum(new Matrix(new double[][]{{1.0D, 1.0D}, {2.0D, 2.0D}}));
        Assert.assertTrue(temp.equal(new Matrix(new double[][]{{2.0D, 3.0D}, {5.0D, 6.0D}})));
    }

    @Test
    public void getDeterminant() throws Exception {
        Assert.assertEquals((long)this.matrix.getDeterminant(), -2L);
    }
}
