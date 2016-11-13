import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilsTest {
    public ArrayUtilsTest() {
    }

    @Test
    public void checkCopyOfWithZeroElements() throws Exception {
        Assert.assertArrayEquals(ArrayUtils.copyOf(new int[0], 2), new int[]{0, 0});
    }

    @Test
    public void checkCopyOfWithZeroNewElements() throws Exception {
        Assert.assertArrayEquals(ArrayUtils.copyOf(new int[0], 0), new int[0]);
    }

    @Test
    public void checkCopyOfWithNegativeValue() throws Exception {
        Assert.assertArrayEquals(ArrayUtils.copyOf(new int[0], -5), new int[0]);
    }

    @Test
    public void checkCopyOfWithCorrectValueAndAddTail() throws Exception {
        Assert.assertArrayEquals(ArrayUtils.copyOf(new int[]{1, 5, 2}, 5), new int[]{1, 5, 2, 0, 0});
    }

    @Test
    public void checkCopyOfWithCorrectValueWithoutTail() throws Exception {
        Assert.assertArrayEquals(ArrayUtils.copyOf(new int[]{1, 5, 2}, 1), new int[]{1});
    }

    @Test
    public void checkCompareDifferentSize() throws Exception {
        Assert.assertFalse(ArrayUtils.compare(new int[0], new int[]{1, 2, 3}));
    }

    @Test
    public void checkCompareDifferentValues() throws Exception {
        Assert.assertFalse(ArrayUtils.compare(new int[]{3, 2, 5}, new int[]{1, 2, 3}));
    }

    @Test
    public void checkCompareCorrectValues() throws Exception {
        Assert.assertTrue(ArrayUtils.compare(new int[]{3, 2, 1}, new int[]{1, 2, 3}));
    }

    @Test
    public void checKMixWitoutValues() throws Exception {
        int[] a = new int[0];
        ArrayUtils.mix(a);
        Assert.assertArrayEquals(a, new int[0]);
    }

    @Test
    public void checKMixWithOneValue() throws Exception {
        int[] a = new int[]{1};
        ArrayUtils.mix(a);
        Assert.assertArrayEquals(a, new int[]{1});
    }

    @Test
    public void checKMix() throws Exception {
        int[] a = new int[]{13, 2, 1, 5};
        ArrayUtils.mix(a);
        Assert.assertTrue(ArrayUtils.compare(a, new int[]{13, 2, 1, 5}));
    }

    @Test
    public void checkPrint() throws Exception {
        Assert.assertTrue(ArrayUtils.print(new int[]{1, 2, 3, 4}).equals("1 2 3 4 "));
    }
}
