package mandelbrot.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * MandelbrotTest
 * @author Michael
 * @since 2/27/11
 */
public class MandelbrotTest {
    @Test
    public void testPointOutside() {
        Mandelbrot mandelbrot = new Mandelbrot();
        Complex z = new Complex(4.0, 2.0);
        int expected = 1;
        Assert.assertEquals(expected, mandelbrot.getNumIterations(z));
    }

    @Test
    public void testOrigin() {
        Mandelbrot mandelbrot = new Mandelbrot();
        Complex z = new Complex();
        int expected = Mandelbrot.MAX_ITERATIONS;
        Assert.assertEquals(expected, mandelbrot.getNumIterations(z));
    }


    @Test
    public void testPointInside() {
        Mandelbrot mandelbrot = new Mandelbrot();
        Complex z = new Complex(0.5, 0.1);
        int expected = 4;
        Assert.assertEquals(expected, mandelbrot.getNumIterations(z));
    }

    @Test
    public void testLineOfPoints() {
        int numPoints = 10;
        double xMin = -2.0;
        double xMax = 2.0;
        double dx = (xMax - xMin) / numPoints;
        double yMin = -2.0;
        double yMax = 2.0;
        double dy = (yMax - yMin) / numPoints;

        Mandelbrot mandelbrot = new Mandelbrot();
        Map<Complex, Integer> values = new LinkedHashMap<Complex, Integer>();
        for (double y = yMin; y <= yMax; y += dy) {
            for (double x = xMin; x <= xMax; x += dx) {
                Complex z = new Complex(x, y);
                values.put(z, mandelbrot.getNumIterations(z));
            }
        }

        System.out.println(values);
    }

    @Test
    public void testIsInside() {
        int numPoints = 20;
        double xMin = -2.0;
        double xMax = 2.0;
        double dx = (xMax - xMin) / numPoints;
        double yMin = -2.0;
        double yMax = 2.0;
        double dy = (yMax - yMin) / numPoints;

        Mandelbrot mandelbrot = new Mandelbrot();
        for (double y = yMin; y <= yMax; y += dy) {
            Map<Complex, Boolean> values = new LinkedHashMap<Complex, Boolean>();
            for (double x = xMin; x <= xMax; x += dx) {
                Complex z = new Complex(x, y);
                values.put(z, mandelbrot.isInside(z));
            }
            System.out.println(values);
        }
    }
}
