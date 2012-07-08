package mandelbrot.model;

/**
 * Mandelbrot
 * @author Michael
 * @link http://warp.povusers.org/Mandelbrot/
 * @since 2/27/11
 */
public class Mandelbrot implements IterativeFunction {
    public static final double DEFAULT_BOUND = 2.0;
    public static final int MAX_ITERATIONS = 50;

    public int getNumIterations(Complex z0) {
        return this.getNumIterations(z0, MAX_ITERATIONS, DEFAULT_BOUND);
    }

    public int getNumIterations(Complex z0, int maxIterations, double bound) {
        int numIterations = 0;

        Complex znext = new Complex(z0);
        do {
            znext = znext.mul(znext).add(z0);
            ++numIterations;
        } while ((numIterations < maxIterations) && (znext.magnitude() < bound));

        return numIterations;
    }

    public boolean isInside(Complex z0) {
        return isInside(z0, MAX_ITERATIONS, DEFAULT_BOUND);
    }

    public boolean isInside(Complex z0, int maxIterations, double bound) {
        boolean inside = true;

        Complex z = new Complex(z0);
        for (int i = 0; i < maxIterations; ++i) {
            if (z.magnitude() > bound) {
                inside = false;
                break;
            }

            z = z.mul(z).add(z0);
        }

        return inside;
    }
}
