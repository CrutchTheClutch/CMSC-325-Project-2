import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Filename:    Animation
 * Author:      William Crutchfield
 * Date:        9/4/2017
 * Description: JPanel that holds animated images.
 */
class Animation extends JPanel {

  private static int translateX = 0;
  private static int translateY = 0;
  private static double rotation = 0.0;
  private static double scaleX = 1.0;
  private static double scaleY = 1.0;

  private Images images = new Images();
  private BufferedImage redSquare = images.getImage(Images.redSquare);
  private BufferedImage cross = images.getImage(Images.cross);
  private BufferedImage letterF = images.getImage(Images.letterF);
  private BufferedImage coloredCross = images.getImage(Images.coloredCross);
  private BufferedImage circle = images.getImage(Images.circle);

  private int frameNumber;

  /** Default Constructor, starts animation Timer */
  Animation() {
    Timer animationTimer =
        new Timer(
            1000,
            arg0 -> {
              if (frameNumber >= 6) {
                frameNumber = 0;
              } else {
                frameNumber++;
              }
              repaint();
            });
    animationTimer.start(); // Start the animation running.
  }

  /**
   * Draws each image to animation Panel, applies transformations depending on frame
   *
   * @param g Graphics object, allows rendering
   */
  public void paintComponent(Graphics g) {

    Graphics2D g2 = (Graphics2D) g.create(); // Creates Graphics2D object

    // Enables Antialiasing
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Sets background color to WHITE
    g2.setPaint(Color.WHITE);
    g2.fillRect(0, 0, getWidth(), getHeight());

    // Controls View
    applyWindowToViewportTransformation(g2, -100, 100, -100, 100, true);
    AffineTransform savedTransform = g2.getTransform();

    // Applies transformations depending on frame
    switch (frameNumber) {
      case 0: // Resets all transformations
        translateX = 0;
        translateY = 0;
        scaleX = 1.0;
        scaleY = 1.0;
        rotation = 0;
        break;
      case 1: // Translates -10 in X direction
        translateX += -10;
        break;
      case 2: // Translates +12 in Y direction
        translateY += 12;
        break;
      case 3: // Rotates 55 degrees counter clockwise
        rotation += 55 * Math.PI / 180.0;
        break;
      case 4: // Rotates 75 degrees clockwise
        rotation += -75 * Math.PI / 180.0;
        break;
      case 5: // Scale 3 times for X component
        scaleX = 3.0;
        break;
      case 6: // Scales 1.5 times for Y component
        scaleY = 1.5;
        break;
    }

    // redSquare Image
    g2.translate(translateX, translateY); // Moves image
    g2.rotate(rotation); // Rotates image
    g2.scale(scaleX, scaleY); // Scales image
    g2.drawImage(redSquare, 0, 0, this); // Draw image
    g2.setTransform(savedTransform);

    // cross Image
    g2.translate(translateX, translateY); // Moves image
    g2.translate(-50, -25); // Offsets image
    g2.rotate(rotation); // Rotates image
    g2.scale(scaleX, scaleY); // Scales image
    g2.drawImage(cross, 0, 0, this); // Draw image
    g2.setTransform(savedTransform);

    // letterF Image
    g2.translate(translateX, translateY); // Moves image
    g2.translate(50, 25); // Offsets image
    g2.rotate(rotation); // Rotates image
    g2.scale(scaleX, scaleY); // Scales image
    g2.drawImage(letterF, 0, 0, this); // Draw image
    g2.setTransform(savedTransform);

    // coloredCross Image
    g2.translate(translateX, translateY); // Moves image
    g2.translate(-100, 25); // Offsets image
    g2.rotate(rotation); // Rotates image
    g2.scale(scaleX, scaleY); // Scales image
    g2.drawImage(coloredCross, 0, 0, this); // Draw image
    g2.setTransform(savedTransform);

    // circle Image
    g2.translate(translateX, translateY); // Moves image
    g2.translate(100, -25); // Offsets image
    g2.rotate(rotation); // Rotates image
    g2.scale(scaleX, scaleY); // Scales image
    g2.drawImage(circle, 0, 0, this); // Draw image
    g2.setTransform(savedTransform);
  }

  /**
   * Sets up a new coordinate system
   *
   * @param g2 Graphics object, allows rendering
   * @param left left of Viewport, used to calculate width
   * @param right right of Viewport, used to calculate width
   * @param bottom bottom of Viewport, used to calculate height
   * @param top top of Viewport, used to calculate height
   * @param preserveAspect boolean to determine if Aspect Ratio should be maintained
   */
  private void applyWindowToViewportTransformation(
      Graphics2D g2, double left, double right, double bottom, double top, boolean preserveAspect) {

    int width = getWidth(); // The width of this drawing area, in pixels
    int height = getHeight(); // The height of this drawing area, in pixels

    if (preserveAspect) {

      // Adjust the limits to match the aspect ratio of the drawing area
      double displayAspect = Math.abs((double) height / width);
      double requestedAspect = Math.abs((bottom - top) / (right - left));
      if (displayAspect > requestedAspect) {
        // Expand the viewport vertically
        double excess = (bottom - top) * (displayAspect / requestedAspect - 1);
        bottom += excess / 2;
        top -= excess / 2;
      } else if (displayAspect < requestedAspect) {
        // Expand the viewport horizontally
        double excess = (right - left) * (requestedAspect / displayAspect - 1);
        right += excess / 2;
        left -= excess / 2;
      }
    }

    g2.scale(width / (right - left), height / (bottom - top));
    g2.translate(-left, -top);
  }
}
