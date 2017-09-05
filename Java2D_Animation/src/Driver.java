import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Filename:    Java2DP1
 * Author:      William Crutchfield
 * Date:        9/4/2017
 * Description: Creates an Animation JPanel that contains multiple images. Images are then
 * transformed depending on the frame of the Animation panel.
 */
public class Driver extends JFrame {

  /** Creates JFrame with Animation Panel */
  private Driver() {

    Animation animation = new Animation(); // Construct Class with Graphics Component
    add(animation); // Add to JFrame

    setSize(1000, 600); // Set Default Size
    setTitle("Java2D Animation"); // Set Title

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Default Close
    setLocationRelativeTo(null); // Center the Frame
    setResizable(false); // Disable Resize
  }

  public static void main(String[] args) {
    Driver driver = new Driver();
    driver.setVisible(true);
  }
}
