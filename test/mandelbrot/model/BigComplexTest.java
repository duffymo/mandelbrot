package mandelbrot.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * ComplexTest
 * @author Michael
 * @since 2/27/11
 */
public class BigComplexTest {
    @Test
    public void testMagnitude() {
        BigComplex x = new BigComplex(new BigDecimal(4.0), new BigDecimal(4.0));
        double expected = 4.0 * Math.sqrt(2.0);
        Assert.assertEquals(new BigDecimal(expected), x.magnitude());
    }

    @Test
    public void testConjugate() {
        BigComplex x = new BigComplex(new BigDecimal(4.0), new BigDecimal(4.0));
        BigComplex expected = new BigComplex(new BigDecimal(4.0), new BigDecimal(-4.0));
        Assert.assertEquals(expected, x.conjugate());
    }

    @Test
    public void testMul() {
        BigComplex x = new BigComplex(BigDecimal.ZERO, new BigDecimal(-1.0));
        BigComplex y = new BigComplex(new BigDecimal(1.0), new BigDecimal(-Math.sqrt(2.0)));
        BigComplex expected = new BigComplex(new BigDecimal(-Math.sqrt(2.0)), new BigDecimal(-1.0));
        Assert.assertEquals(expected, x.mul(y));
    }

    @Test
    public void testMulByConstant() {
        BigComplex y = new BigComplex(new BigDecimal(1.0), new BigDecimal(-Math.sqrt(2.0)));
        BigComplex expected = new BigComplex(new BigDecimal(4.5), new BigDecimal(-4.5 * Math.sqrt(2.0)));
        Assert.assertEquals(expected, y.mul(new BigDecimal(4.5)));
    }

    @Test
    public void testDiv() {
        BigComplex x = new BigComplex(new BigDecimal(5.0));
        BigComplex y = new BigComplex(new BigDecimal(1.0), new BigDecimal(-1.0)).mul(new BigComplex(new BigDecimal(2.0), new BigDecimal(-1.0))).mul(new BigComplex(new BigDecimal(3.0), new BigDecimal(-1.0)));
        BigComplex expected = new BigComplex(BigDecimal.ZERO, new BigDecimal(0.5));
        Assert.assertEquals(expected, x.div(y));
    }

    @Test
    public void testDivByConstant() {
        BigComplex y = new BigComplex(new BigDecimal(1.0), new BigDecimal(-Math.sqrt(2.0)));
        BigComplex expected = new BigComplex(new BigDecimal(0.5), new BigDecimal(-Math.sqrt(2.0) / 2.0));
        Assert.assertEquals(expected, y.div(new BigDecimal(2.0)));
    }

    @Test
    public void testAdd() {
        BigComplex x = new BigComplex(new BigDecimal(Math.sqrt(2.0)), new BigDecimal(-1.0));
        BigComplex y = BigComplex.mul(new BigComplex(BigDecimal.ZERO, new BigDecimal(-1.0)), new BigComplex(new BigDecimal(1.0), new BigDecimal(-Math.sqrt(2.0))));
        BigComplex expected = new BigComplex(BigDecimal.ZERO, new BigDecimal(-2.0));
        Assert.assertEquals(expected, x.add(y));
    }

    @Test
    public void testAddConstant() {
        BigComplex x = new BigComplex(new BigDecimal(Math.sqrt(2.0)), new BigDecimal(-1.0));
        BigComplex expected = new BigComplex(new BigDecimal(10.0 + Math.sqrt(2.0)), new BigDecimal(-1.0));
        Assert.assertEquals(expected, x.add(new BigDecimal(10.0)));
    }

    @Test
    public void testSub() {
        BigComplex x = new BigComplex(new BigDecimal(7.0), new BigDecimal(3.0));
        BigComplex y = new BigComplex(new BigDecimal(4.0), new BigDecimal(2.0));
        BigComplex expected = new BigComplex(new BigDecimal(3.0), new BigDecimal(1.0));
        Assert.assertEquals(expected, x.sub(y));
    }

    @Test
    public void testSubConstant() {
        BigComplex x = new BigComplex(new BigDecimal(Math.sqrt(2.0)), new BigDecimal(-1.0));
        BigComplex expected = new BigComplex(new BigDecimal(-10.00).add(new BigDecimal(Math.sqrt(2.0))), new BigDecimal(-1.0));
        Assert.assertEquals(expected, x.sub(new BigDecimal(10.0)));
    }

    @Test
    public void testEqualsNull() {
        BigComplex x = new BigComplex(new BigDecimal(5.0), new BigDecimal(6.0));
        Assert.assertFalse(x.equals(null));
    }

    @Test
    public void testEqualsReflexive() {
        BigComplex x = new BigComplex(new BigDecimal(5.0), new BigDecimal(6.0));
        Assert.assertTrue(x.equals(x));
    }

    @Test
    public void testEqualsSymmetric() {
        BigComplex x = new BigComplex(new BigDecimal(5.0), new BigDecimal(6.0));
        BigComplex y = new BigComplex(x);
        Assert.assertTrue(x.equals(y) && y.equals(x));
        Assert.assertEquals(x.hashCode(), y.hashCode());
    }

    @Test
    public void testEqualsTransitive() {
        BigComplex x = new BigComplex(new BigDecimal(5.0), new BigDecimal(6.0));
        BigComplex y = new BigComplex(x);
        BigComplex z = new BigComplex(x);
        Assert.assertTrue(x.equals(y) && y.equals(z) && z.equals(x));
        Assert.assertEquals(x.hashCode(), y.hashCode());
        Assert.assertEquals(y.hashCode(), z.hashCode());
        Assert.assertEquals(z.hashCode(), x.hashCode());
    }

    @Test
    public void testNotEqual() {
        BigComplex x = new BigComplex(new BigDecimal(10.0), new BigDecimal(3.0));

        BigComplex y = new BigComplex(new BigDecimal(10.0), new BigDecimal(4.0));
        Assert.assertFalse(x.equals(y));
        Assert.assertFalse(x.hashCode() == y.hashCode());

        BigComplex z = new BigComplex(BigDecimal.ZERO, new BigDecimal(3.0));
        Assert.assertFalse(x.equals(z));
        Assert.assertFalse(x.hashCode() == z.hashCode());

        BigComplex w = new BigComplex();
        Assert.assertFalse(x.equals(w));
        Assert.assertFalse(x.hashCode() == w.hashCode());
    }

    @Test
    public void testToString() {
        BigComplex x = new BigComplex(new BigDecimal(10.0), new BigDecimal(4.0));
        String expected = "(10,4i)";
        Assert.assertEquals(expected, x.toString());
    }
}
