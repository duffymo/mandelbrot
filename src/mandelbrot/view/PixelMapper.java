package mandelbrot.view;

import mandelbrot.model.Complex;

/**
 * PixelMapper takes in a pixel (x,y) coordinate and the four corners of
 * the region in the complex plane and returns the corresponding Complex number.
 * @author Michael
 * @since 2/28/11
 */
public class PixelMapper {
    private static final Complex DEFAULT_UPPER_LEFT = new Complex(-2.0, 2.0);
    private static final Complex DEFAULT_LOWER_RIGHT = new Complex(+2.0, -2.0);

    private int width;
    private int height;
    private Complex lowerLeft;
    private Complex lowerRight;
    private Complex upperRight;
    private Complex upperLeft;

    public PixelMapper() {
        this(MandelbrotPanel.DEFAULT_WIDTH, MandelbrotPanel.DEFAULT_HEIGHT);
    }

    public PixelMapper(int width, int height) {
        this(width, height, DEFAULT_UPPER_LEFT, DEFAULT_LOWER_RIGHT);
    }

    public PixelMapper(int width, int height, Complex upperLeft, Complex lowerRight) {
        if ((lowerRight == null) || (upperLeft == null))
            throw new IllegalArgumentException("Complex region bounds cannot be null");

        this.width = ((width <= 0) ? MandelbrotPanel.DEFAULT_WIDTH : width);
        this.height = ((height <= 0) ? MandelbrotPanel.DEFAULT_HEIGHT : height);
        this.lowerRight = lowerRight;
        this.upperLeft = upperLeft;
        this.lowerLeft = new Complex(this.upperLeft.getRe(), this.lowerRight.getIm());
        this.upperRight = new Complex(this.lowerRight.getRe(), this.upperLeft.getIm());
    }

    public Complex map(int x, int y) {
        double u = (double) x / this.width;
        double v = (double) y / this.height;

        // Bi-linear shape functions mapping pixel space to complex region
        Complex value = new Complex()
                .add(new Complex(this.upperLeft).mul(1 - u).mul(1 - v))
                .add(new Complex(this.upperRight).mul(1 - u).mul(v))
                .add(new Complex(this.lowerLeft).mul(u).mul(1 - v))
                .add(new Complex(this.lowerRight).mul(u).mul(v));
        return value;
    }
}
