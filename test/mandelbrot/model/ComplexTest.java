package mandelbrot.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * ComplexTest
 * @author Michael
 * @since 2/27/11
 */
public class ComplexTest {
    @Test
    public void testMagnitude() {
        Complex x = new Complex(4.0, 4.0);
        double expected = 4.0 * Math.sqrt(2.0);
        Assert.assertEquals(expected, x.magnitude(), 1.0e-4);
    }

    @Test
    public void testConjugate() {
        Complex x = new Complex(4.0, 4.0);
        Complex expected = new Complex(4.0, -4.0);
        Assert.assertEquals(expected, x.conjugate());
    }

    @Test
    public void testMul() {
        Complex x = new Complex(0.0, -1.0);
        Complex y = new Complex(1.0, -Math.sqrt(2.0));
        Complex expected = new Complex(-Math.sqrt(2.0), -1.0);
        Assert.assertEquals(expected, x.mul(y));
    }

    @Test
    public void testMulByConstant() {
        Complex y = new Complex(1.0, -Math.sqrt(2.0));
        Complex expected = new Complex(4.5, -4.5 * Math.sqrt(2.0));
        Assert.assertEquals(expected, y.mul(4.5));
    }

    @Test
    public void testDiv() {
        Complex x = new Complex(5.0);
        Complex y = new Complex(1.0, -1.0).mul(new Complex(2.0, -1.0)).mul(new Complex(3.0, -1.0));
        Complex expected = new Complex(0.0, 0.5);
        Assert.assertEquals(expected, x.div(y));
    }

    @Test
    public void testDivByConstant() {
        Complex y = new Complex(1.0, -Math.sqrt(2.0));
        Complex expected = new Complex(0.5, -Math.sqrt(2.0) / 2.0);
        Assert.assertEquals(expected, y.div(2.0));
    }

    @Test
    public void testAdd() {
        Complex x = new Complex(Math.sqrt(2.0), -1.0);
        Complex y = Complex.mul(new Complex(0.0, -1.0), new Complex(1.0, -Math.sqrt(2.0)));
        Complex expected = new Complex(0.0, -2.0);
        Assert.assertEquals(expected, x.add(y));
    }

    @Test
    public void testAddConstant() {
        Complex x = new Complex(Math.sqrt(2.0), -1.0);
        Complex expected = new Complex(10.0 + Math.sqrt(2.0), -1.0);
        Assert.assertEquals(expected, x.add(10.0));
    }

    @Test
    public void testSub() {
        Complex x = new Complex(7.0, 3.0);
        Complex y = new Complex(4.0, 2.0);
        Complex expected = new Complex(3.0, 1.0);
        Assert.assertEquals(expected, x.sub(y));
    }

    @Test
    public void testSubConstant() {
        Complex x = new Complex(Math.sqrt(2.0), -1.0);
        Complex expected = new Complex(-10.0 + Math.sqrt(2.0), -1.0);
        Assert.assertEquals(expected, x.sub(10.0));
    }

    @Test
    public void testEqualsNull() {
        Complex x = new Complex(5.0, 6.0);
        Assert.assertFalse(x.equals(null));
    }

    @Test
    public void testEqualsReflexive() {
        Complex x = new Complex(5.0, 6.0);
        Assert.assertTrue(x.equals(x));
    }

    @Test
    public void testEqualsSymmetric() {
        Complex x = new Complex(5.0, 6.0);
        Complex y = new Complex(x);
        Assert.assertTrue(x.equals(y) && y.equals(x));
        Assert.assertEquals(x.hashCode(), y.hashCode());
    }

    @Test
    public void testEqualsTransitive() {
        Complex x = new Complex(5.0, 6.0);
        Complex y = new Complex(x);
        Complex z = new Complex(x);
        Assert.assertTrue(x.equals(y) && y.equals(z) && z.equals(x));
        Assert.assertEquals(x.hashCode(), y.hashCode());
        Assert.assertEquals(y.hashCode(), z.hashCode());
        Assert.assertEquals(z.hashCode(), x.hashCode());
    }

    @Test
    public void testNotEqual() {
        Complex x = new Complex(10.0, 3.0);

        Complex y = new Complex(10.0, 4.0);
        Assert.assertFalse(x.equals(y));
        Assert.assertFalse(x.hashCode() == y.hashCode());

        Complex z = new Complex(0.0, 3.0);
        Assert.assertFalse(x.equals(z));
        Assert.assertFalse(x.hashCode() == z.hashCode());

        Complex w = new Complex();
        Assert.assertFalse(x.equals(w));
        Assert.assertFalse(x.hashCode() == w.hashCode());
    }

    @Test
    public void testToString() {
        Complex x = new Complex(10.0, 4.0);
        String expected = "(10,4i)";
        Assert.assertEquals(expected, x.toString());
    }
}
