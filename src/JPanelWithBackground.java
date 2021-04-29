import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class JPanelWithBackground extends JPanel {

    private Image backgroundImage;

    // Some code to initialize the background image.
    // Here, we use the constructor to load the image. This
    // can vary depending on the use case of the panel.
    public JPanelWithBackground(String fileName) throws IOException {
        backgroundImage = ImageIO.read(new File("./images/uno_assets_2d/unoGamePlay.png"));
        backgroundImage = backgroundImage.getScaledInstance(1024, 768, Image.SCALE_DEFAULT);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this);
    }

    public static void main(String[] args) throws IOException {
        JFrame f = new JFrame();
        f.getContentPane().add(new JPanelWithBackground("sample.jpeg"));
        f.setSize(1024,768);
        f.setVisible(true);
    }
}
