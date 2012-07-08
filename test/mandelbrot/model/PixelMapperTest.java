package mandelbrot.model;

import mandelbrot.view.PixelMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * PixelMapperTest
 * @author Michael
 * @since 2/28/11
 */
public class PixelMapperTest {
    @Test
    public void testMap() {
        PixelMapper mapper = new PixelMapper(400, 600, new Complex(2.0, 4.0), new Complex(4.0, 2.0));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        for (int i = 0; i <= 400; i += 100) {
            for (int j = 0; j <= 600; j += 150) {
                ps.printf("{%3d, %3d, %s} ", i, j, mapper.map(i, j));
            }
        }

        String expected = "{  0,   0, (2,4i)} {  0, 150, (2.5,4i)} {  0, 300, (3,4i)} {  0, 450, (3.5,4i)} {  0, 600, (4,4i)} {100,   0, (2,3.5i)} {100, 150, (2.5,3.5i)} {100, 300, (3,3.5i)} {100, 450, (3.5,3.5i)} {100, 600, (4,3.5i)} {200,   0, (2,3i)} {200, 150, (2.5,3i)} {200, 300, (3,3i)} {200, 450, (3.5,3i)} {200, 600, (4,3i)} {300,   0, (2,2.5i)} {300, 150, (2.5,2.5i)} {300, 300, (3,2.5i)} {300, 450, (3.5,2.5i)} {300, 600, (4,2.5i)} {400,   0, (2,2i)} {400, 150, (2.5,2i)} {400, 300, (3,2i)} {400, 450, (3.5,2i)} {400, 600, (4,2i)}";
        Assert.assertEquals(expected.trim(), baos.toString().trim());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUpperLeft() {
        new PixelMapper(400, 600, null, new Complex(4.0, 2.0));
    }
}
