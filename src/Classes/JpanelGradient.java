package Classes;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class JpanelGradient extends JPanel {

    private Color firstColor;
    private Color secondColor;

    public JpanelGradient(Color first, Color second) {
        this.firstColor = first;
        this.secondColor = second;

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        GradientPaint gp = new GradientPaint(0, 0, firstColor, 200, height, secondColor);

        g2D.setPaint(gp);
        g2D.fillRect(0, 0, width, height);

    }
}