package mandelbrot.view;

import mandelbrot.model.Complex;
import mandelbrot.model.IterativeFunction;
import mandelbrot.model.Mandelbrot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * MandelbrotPanel
 * @author Michael
 * @link http://users.rcn.com/ziring/mandel_applet.html
 * @link http://download.oracle.com/javase/tutorial/uiswing/events/mousemotionlistener.html
 * @since 2/27/11
 */
public class MandelbrotPanel extends JPanel implements MouseListener, MouseMotionListener, WindowListener {
    private static final boolean COLOR_DISPLAY = true;
    public static final int DEFAULT_WIDTH = 700;
    public static final int DEFAULT_HEIGHT = 700;
    public static final int DEFAULT_NUM_COLORS = 50;

    private List<Integer> colors;
    private Point pressedCorner = new Point(0, 0);
    private Point releasedCorner = new Point(MandelbrotPanel.DEFAULT_WIDTH, MandelbrotPanel.DEFAULT_HEIGHT);
    private PixelMapper pixelMapper;
    private BufferedImage currentImage;
    private boolean newSelection = false;

    public static void main(String[] args) {
        MandelbrotPanel mandelbrotPanel = new MandelbrotPanel(false, DEFAULT_NUM_COLORS);

        JFrame frame = new JFrame("Mandelbrot Display");
        frame.add(mandelbrotPanel);
        frame.addWindowListener(mandelbrotPanel);
        int width = ((args.length > 0) ? Integer.valueOf(args[0]) : DEFAULT_WIDTH);
        int height = ((args.length > 1) ? Integer.valueOf(args[1]) : DEFAULT_HEIGHT);
        frame.setSize(width, height);
        mandelbrotPanel.setSize(width, height);
        frame.setVisible(true);
    }

    public MandelbrotPanel(boolean isDoubleBuffered, int numColors) {
        this(isDoubleBuffered, generateColors(numColors));
    }

    public MandelbrotPanel(boolean isDoubleBuffered, List<Integer> colorList) {
        super(isDoubleBuffered);
        this.colors = new ArrayList<Integer>(colorList);
        this.pixelMapper = new PixelMapper(this.getWidth(), this.getHeight());
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * Paint the canvas
     * @param g graphics component
     * @link http://www.exampledepot.com/egs/java.awt.image/ImagePixel.html
     * @link http://stackoverflow.com/questions/1115359/how-to-draw-a-rectangle-on-a-java-applet-using-mouse-drag-event-and-make-it-stay
     * @link http://stackoverflow.com/questions/880753/how-to-draw-rectangle-on-java-applet-using-mouse-drag-event
     */
    public void paintComponent(Graphics g) {
        // Create an image that does not support transparency
        this.currentImage = getImage(this.getWidth(), this.getHeight());
        g.drawImage(currentImage, 0, 0, null);
        if (this.newSelection) {
            g.setColor(Color.WHITE);
            int x = this.pressedCorner.x;
            int y = this.pressedCorner.y;
            int w = this.releasedCorner.x - this.pressedCorner.x;
            int h = this.releasedCorner.y - this.pressedCorner.y;
            System.out.printf("painted (x=%d y=%d w=%d h=%d)\n", x, y, w, h);
            g.drawRect(x, y, w, h);
        }
    }

    private BufferedImage getImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        IterativeFunction mandelbrot = new Mandelbrot();
        int minIterations = Integer.MAX_VALUE;
        int maxIterations = Integer.MIN_VALUE;
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                int numIterations = mandelbrot.getNumIterations(pixelMapper.map(i, j));
                minIterations = Math.min(minIterations, numIterations);
                maxIterations = Math.max(maxIterations, numIterations);

                if (COLOR_DISPLAY) {
                    int color = (numIterations < this.colors.size() ? this.colors.get(numIterations) : 0);
                    image.setRGB(i, j, color);
                } else {
                    boolean isInside = mandelbrot.isInside(pixelMapper.map(i, j));
                    image.setRGB(i, j, isInside ? 0 : 0xfffff0);
                }
            }
        }
        return image;
    }

    /**
     * Generate colors for a given range
     * @param n size of List
     * @return List of color values
     * @link http://stackoverflow.com/questions/223971/how-to-generate-spectrum-color-palettes
     * @link http://www.java-tips.org/java-se-tips/java.awt/convert-rgb-value-to-hexadecimal-to-be-used-in-for-ex.html
     * @link http://www.java-tips.org/java-se-tips/java.lang/how-to-convert-a-hexadecimal-string-into-an-int-3.html
     */
    public static List<Integer> generateColors(int n) {
        List<Integer> cols = new LinkedList<Integer>();

        for (int i = 0; i < n; ++i) {
            Color hsbColor = Color.getHSBColor((float) i / (float) n, 0.85f, 1.0f);
            cols.add(fromRgbColorToInteger(hsbColor));
        }

        return cols;
    }

    /**
     * Convert an RGB Color to an integer value
     * @param color RGB to convert
     * @return integer representation of the RGB color.
     */
    public static int fromRgbColorToInteger(Color color) {
        int value = 0;

        if (color != null) {
            String hexString = Integer.toHexString(color.getRGB() & 0x00ffffff);
            value = Integer.valueOf(hexString, 16).intValue();
        }

        return value;
    }

    public void mouseClicked(MouseEvent e) {
        this.repaint();
    }

    public void mouseEntered(MouseEvent e) {
        this.repaint();
    }

    public void mouseExited(MouseEvent e) {
        this.repaint();
    }

    public void mouseDragged(MouseEvent e) {
        this.releasedCorner = e.getLocationOnScreen();
        System.out.printf("dragged to (%d, %d) %b\n", this.releasedCorner.x, this.releasedCorner.y, this.newSelection);
        this.repaint();
    }

    public void mousePressed(MouseEvent e) {
        this.pressedCorner = e.getLocationOnScreen();
        this.newSelection = true;
        System.out.printf("pressed at (%d, %d) %b\n", this.pressedCorner.x, this.pressedCorner.y, this.newSelection);
    }

    public void mouseMoved(MouseEvent e) {
        this.repaint();
    }

    public void mouseReleased(MouseEvent e) {
        this.releasedCorner = e.getLocationOnScreen();
        System.out.printf("released at (%d, %d) %b\n", this.releasedCorner.x, this.releasedCorner.y, this.newSelection);
        Complex upperLeft = this.pixelMapper.map(this.pressedCorner.x, this.pressedCorner.y);
        Complex lowerRight = this.pixelMapper.map(this.releasedCorner.x, this.releasedCorner.y);
        this.pixelMapper = new PixelMapper(this.getWidth(), this.getHeight(), upperLeft, lowerRight);
        this.repaint();
        this.newSelection = false;
    }

    public void windowOpened(WindowEvent e) {
        System.out.printf("event: %s\n", e);
    }

    public void windowClosing(WindowEvent e) {
        System.out.printf("event: %s\n", e);

        try {
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setMultiSelectionEnabled(false);
            int choice = fileChooser.showSaveDialog(this);
            if (choice == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                boolean selectedFileExists = selectedFile.exists();
                if (!selectedFileExists) {
                    selectedFileExists = selectedFile.createNewFile();
                }
                if (selectedFileExists) {
                    String selectedFileName = selectedFile.getName();
                    String selectedFileType = selectedFileName.substring(selectedFileName.lastIndexOf('.') + 1);
                    ImageIO.write(this.currentImage, selectedFileType, selectedFile);
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void windowClosed(WindowEvent e) {
        System.out.printf("event: %s\n", e);
        System.exit(0);
    }

    public void windowIconified(WindowEvent e) {
        System.out.printf("event: %s\n", e);
    }

    public void windowDeiconified(WindowEvent e) {
        System.out.printf("event: %s\n", e);
    }

    public void windowActivated(WindowEvent e) {
        System.out.printf("event: %s\n", e);
    }

    public void windowDeactivated(WindowEvent e) {
        System.out.printf("event: %s\n", e);
    }
}
