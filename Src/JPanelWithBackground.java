import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax. imageio.*;

public class JPanelWithBackground extends JPanel {

	private Image backgroundImage;
  
	// Some code to initialize the background image.
	// Here, we use the constructor to load the image. This
	// can vary depending on the use case of the panel.
	public JPanelWithBackground(String fileName) throws IOException {
	  backgroundImage = ImageIO.read(new File(fileName));
	}
  
	public void paintComponent(Graphics g) {
	  super.paintComponent(g);
  
	  // Draw the background image.
	  g.drawImage(backgroundImage, 0, 0, this);
	}
  }

