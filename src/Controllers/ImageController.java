package Controllers;

import java.awt.Image;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
//ImageController.java
public class ImageController {

    public void previewImage(String imagePath) {//show image preview
        if (imagePath.length() != 0) {
            ImageIcon image = new ImageIcon(imagePath);
            Image resize = image.getImage();
            Image resizedImage = resize.getScaledInstance(700, 395, Image.SCALE_SMOOTH);
            image = new ImageIcon(resizedImage);
            JOptionPane.showMessageDialog(null, null, null, JOptionPane.INFORMATION_MESSAGE, image);
        } else {
            JOptionPane.showMessageDialog(null, "Select a Image");
        }
    }
      public ImageIcon setImageSize(String imagePath,int height,int width) {//resize the image and return it
        if (imagePath.length() != 0) {
            ImageIcon image = new ImageIcon(imagePath);
            Image resize = image.getImage();
            Image resizedImage = resize.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            image = new ImageIcon(resizedImage);
            return image;
        }
        return null;
    }
}
