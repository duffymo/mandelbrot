package mandelbrot.model;

import java.text.DecimalFormat;

/**
 * Complex
 * @author Michael
 * @since 2/27/11
 */
public class Complex {
    private static final DecimalFormat DEFAULT_FORMAT = new DecimalFormat("#.###");

    private final double re;
    private final double im;

    public Complex() {
        this(0.0, 0.0);
    }

    public Complex(double re) {
        this(re, 0.0);
    }

    public Complex(double x, double y) {
        this.re = x;
        this.im = y;
    }

    public Complex(Complex other) {
        this(other.re, other.im);
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    public double magnitude() {
        return (Math.sqrt(re * re + im * im));
    }

    public Complex conjugate() {
        return new Complex(this.re, -this.im);
    }

    public Complex add(Complex x) {
        return Complex.add(this, x);
    }

    public Complex add(double c) {
        return Complex.add(this, c);
    }

    public Complex sub(Complex x) {
        return Complex.sub(this, x);
    }

    public Complex sub(double c) {
        return Complex.sub(this, c);
    }

    public Complex mul(Complex x) {
        return Complex.mul(this, x);
    }

    public Complex mul(double c) {
        return Complex.mul(this, c);
    }

    public Complex div(Complex x) {
        return Complex.div(this, x);
    }

    public Complex div(double c) {
        return Complex.div(this, c);
    }

    public static Complex add(Complex x, Complex y) {
        return new Complex((x.re + y.re), (x.im + y.im));
    }

    public static Complex add(Complex x, double c) {
        return new Complex((x.re + c), x.im);
    }

    public static Complex sub(Complex x, Complex y) {
        return new Complex((x.re - y.re), (x.im - y.im));
    }

    public static Complex sub(Complex x, double c) {
        return new Complex((x.re - c), x.im);
    }

    public static Complex mul(Complex x, Complex y) {
        return new Complex((x.re * y.re - x.im * y.im), (x.re * y.im + x.im * y.re));
    }

    public static Complex mul(Complex x, double c) {
        return new Complex(c * x.re, c * x.im);
    }

    public static Complex div(Complex x, Complex y) {
        return Complex.div(Complex.mul(x, y.conjugate()), (y.re * y.re + y.im * y.im));
    }

    public static Complex div(Complex x, double c) {
        return new Complex(x.re / c, x.im / c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Complex)) {
            return false;
        }

        Complex complex = (Complex) o;

        if (Double.compare(complex.im, im) != 0) {
            return false;
        }
        if (Double.compare(complex.re, re) != 0) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = re != +0.0d ? Double.doubleToLongBits(re) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = im != +0.0d ? Double.doubleToLongBits(im) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(DEFAULT_FORMAT.format(re));
        if (im != 0.0)
            sb.append(',').append(DEFAULT_FORMAT.format(im)).append('i');
        sb.append(')');
        return sb.toString();
    }
}
