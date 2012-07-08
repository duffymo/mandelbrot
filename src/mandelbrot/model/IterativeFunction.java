package mandelbrot.model;

/**
 * IterativeFunction
 * @author Michael
 * @since 3/2/11
 */
public interface IterativeFunction {
    int getNumIterations(Complex z0);

    int getNumIterations(Complex z0, int maxIterations, double bound);

    boolean isInside(Complex z0);

    boolean isInside(Complex z0, int maxIterations, double bound);
}
