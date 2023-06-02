import javax.swing.*;
import java.awt.*;

public class JEDCButton {
    private final Shape shape;
    private final String title;
    private Color color;
    private final Color regularColor, highlightColor, textColor;
    private ImageIcon image, regularImage, highlightImage;
    private boolean hasComponent;

    public JEDCButton(Shape s, String t, Color rc, Color hc, Color tc) {
        shape = s;
        title = t;
        regularColor = rc;
        highlightColor = hc;
        textColor = tc;
        color = regularColor;
        hasComponent = false;
    }

    public JEDCButton(Shape s, String t, ImageIcon ri, ImageIcon hi)
    {
        shape = s;
        title = t;
        regularColor = null;
        highlightColor = null;
        textColor = null;
        regularImage = ri;
        highlightImage = hi;
        image = regularImage;
        hasComponent = false;
    }

    public String getTitle() {
        return title;
    }

    public Shape getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public void highlight() {
        color = highlightColor;
        image = highlightImage;
    }

    public void unHighlight() {
        color = regularColor;
        image = regularImage;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void drawButton(Graphics g) {
        int x = (int)(this.getShape().getBounds().getX());
        int y = (int)(this.getShape().getBounds().getY());
        int width = (int)(this.getShape().getBounds().getWidth());
        int height = (int)(this.getShape().getBounds().getHeight());
        g.setColor(this.getColor());
        if (image != null) {
            g.drawImage(image.getImage(), x, y, width, height, null);
        }
        else if (this.getShape() instanceof Rectangle) {
            g.fillRect(x, y, width, height);
        }
        else {
            g.fillOval(x, y, width, height);
        }
        if (image == null) {
            g.setColor(this.getTextColor());
            g.drawString(this.getTitle(), x, y + (height / 2));
        }
    }

    public void setRegularImage(ImageIcon img) {
        regularImage = img;
    }

    public void setHighlightImage(ImageIcon img) {
        highlightImage = img;
    }

    public boolean isHasComponent() {
        return hasComponent;
    }

    public void setHasComponent(boolean b) {
        hasComponent = b;
    }
}
