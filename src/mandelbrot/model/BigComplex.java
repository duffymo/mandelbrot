package mandelbrot.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Complex
 * @author Michael
 * @since 2/27/11
 */
public class BigComplex {
    private static final DecimalFormat DEFAULT_FORMAT = new DecimalFormat("#.###");
    private static final int DEFAULT_SCALE = 10;
    private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_EVEN;

    private final BigDecimal re;
    private final BigDecimal im;

    public BigComplex() {
        this(BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public BigComplex(BigDecimal re) {
        this(re, BigDecimal.ZERO);
    }

    public BigComplex(BigDecimal x, BigDecimal y) {
        this.re = x.setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
        this.im = y.setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
    }

    public BigComplex(BigComplex other) {
        this(other.re, other.im);
    }

    public BigDecimal magnitude() {
        return new BigDecimal(Math.sqrt(re.multiply(re).add(im.multiply(im)).doubleValue()));
    }

    public BigComplex conjugate() {
        return new BigComplex(this.re, this.im.negate());
    }

    public BigComplex add(BigComplex x) {
        return BigComplex.add(this, x);
    }

    public BigComplex add(BigDecimal c) {
        return BigComplex.add(this, c);
    }

    public BigComplex sub(BigComplex x) {
        return BigComplex.sub(this, x);
    }

    public BigComplex sub(BigDecimal c) {
        return BigComplex.sub(this, c);
    }

    public BigComplex mul(BigComplex x) {
        return BigComplex.mul(this, x);
    }

    public BigComplex mul(BigDecimal c) {
        return BigComplex.mul(this, c);
    }

    public BigComplex div(BigComplex x) {
        return BigComplex.div(this, x);
    }

    public BigComplex div(BigDecimal c) {
        return BigComplex.div(this, c);
    }

    public static BigComplex add(BigComplex x, BigComplex y) {
        return new BigComplex(x.re.add(y.re), x.im.add(y.im));
    }

    public static BigComplex add(BigComplex x, BigDecimal c) {
        return new BigComplex(x.re.add(c), x.im);
    }

    public static BigComplex sub(BigComplex x, BigComplex y) {
        return new BigComplex(x.re.subtract(y.re), x.im.subtract(y.im));
    }

    public static BigComplex sub(BigComplex x, BigDecimal c) {
        return new BigComplex((x.re.subtract(c)), x.im);
    }

    public static BigComplex mul(BigComplex x, BigComplex y) {
        return new BigComplex(x.re.multiply(y.re).subtract(x.im.multiply(y.im)), x.re.multiply(y.im).add(x.im.multiply(y.re)));
    }

    public static BigComplex mul(BigComplex x, BigDecimal c) {
        return new BigComplex(c.multiply(x.re), c.multiply(x.im));
    }

    public static BigComplex div(BigComplex x, BigComplex y) {
        return BigComplex.div(BigComplex.mul(x, y.conjugate()), (y.re.multiply(y.re).add(y.im.multiply(y.im))));
    }

    public static BigComplex div(BigComplex x, BigDecimal c) {
        return new BigComplex(x.re.divide(c), x.im.divide(c));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BigComplex that = (BigComplex) o;

        return (this.re.compareTo(that.re) == 0) && (this.im.compareTo(that.im) == 0);

    }

    @Override
    public int hashCode() {
        int result = re.hashCode();
        result = 31 * result + im.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(DEFAULT_FORMAT.format(re));
        if (!BigDecimal.ZERO.equals(im))
            sb.append(',').append(DEFAULT_FORMAT.format(im)).append('i');
        sb.append(')');
        return sb.toString();
    }
}
