/**
 * PicturePanel.java
 * This method adds functionality for JPanel to allow custom background images on panels
 *
 * CPSC 224_01, Spring 2018
 * @author Zach McKee
 * @verison 1.0 4/27/2018
 */

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;
public class PicturePanel extends JPanel {
	private Image background;

	public PicturePanel(String imageName) {
		background = new ImageIcon(imageName).getImage();
	}

	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	}
}
