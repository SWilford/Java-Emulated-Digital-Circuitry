import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class JEDCGraphics extends JPanel implements MouseListener, MouseMotionListener {

    private final ArrayList<JEDCButton> grid = new ArrayList<>();
    private final ArrayList<JEDCButton> tools = new ArrayList<>();
    private final component[][] components = new component[55][32];

    private static int mouseX;
    private static int mouseY;

    private int currentTool;
    private boolean toolSelected;

    // ImageIcons
    private final ImageIcon gridSquareImg = new ImageIcon("images/gridSquare.png");
    private final ImageIcon gridSquare2Img = new ImageIcon("images/gridSquare2.png");
    private final ImageIcon inputSelectImg = new ImageIcon("images/inputSelect.png");
    private final ImageIcon inputSelect2Img = new ImageIcon("images/inputSelect2.png");
    private final ImageIcon inputOnImg = new ImageIcon("images/inputOn.png");
    private final ImageIcon inputOn2Img = new ImageIcon("images/inputOn2.png");
    private final ImageIcon inputOffImg = new ImageIcon("images/inputOff.png");
    private final ImageIcon inputOff2Img = new ImageIcon("images/inputOff2.png");
    private final ImageIcon deleteToolImg = new ImageIcon("images/deleteTool.png");
    private final ImageIcon deleteTool2Img = new ImageIcon("images/deleteTool2.png");

    // End of Image Icons

    public JEDCGraphics() {
        addMouseListener(this);
        addMouseMotionListener(this);
        createGridButtons();
        createToolButtons();
        currentTool = 0;
        toolSelected = false;
        mouseX = 0;
        mouseY = 0;
    }

    public void display(Graphics g) {
        for(JEDCButton button:grid) {
            button.drawButton(g);
        }
        for(JEDCButton button:tools) {
            button.drawButton(g);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        display(g);
    }

    public void createGridButtons() {
        for(int x = 70; x <= 1370; x+=24) {
            for(int y = 0; y < 750; y+=24) {
                Shape gridSquare = new Rectangle(x, y, 24, 24);
                JEDCButton button = new JEDCButton(gridSquare, "gridSquare", gridSquareImg, gridSquare2Img);
                grid.add(button);
            }
        }
    }

    public void createToolButtons() {
        Shape toolSquare = new Rectangle(0, 0, 35, 35);
        JEDCButton inputButton = new JEDCButton(toolSquare, "inputSelect", inputSelectImg, inputSelect2Img);
        tools.add(inputButton);
        Shape toolSquare2 = new Rectangle(35, 0, 35, 35);
        JEDCButton deleteButton = new JEDCButton(toolSquare2, "delete", deleteToolImg, deleteTool2Img);
        tools.add(deleteButton);
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        int button = e.getButton();
        if(button == MouseEvent.BUTTON1) {
            for(JEDCButton b:tools) { //for tools
                if(b.getShape().contains(mouseX, mouseY)) {
                    switch(b.getTitle()) {
                        case "inputSelect" -> {
                            if(!toolSelected || currentTool > 1) {
                                toolSelected = true;
                                currentTool = 1;
                            }
                            else {
                                toolSelected = false;
                                currentTool = 0;
                            }
                        }
                        case "delete" -> {
                            if(!toolSelected || currentTool > 1) {
                                toolSelected = true;
                                currentTool = 2;
                            }
                            else {
                                toolSelected = false;
                                currentTool = 0;
                            }
                        }
                    }
                }
            }
            for(JEDCButton b:grid) { //for grid buttons
                if(b.getShape().contains(mouseX, mouseY)) {
                    int x = (int) b.getShape().getBounds().getX();
                    int y = (int) b.getShape().getBounds().getY();
                    int getX = (x / 24)-2;
                    int getY = y / 24;
                    switch (currentTool) {
                        case 0 -> {
                            if(b.isHasComponent()) {
                                switch (components[getX][getY].getType()) {
                                    case 1 -> {
                                        inputComponent input = (inputComponent)components[getX][getY];
                                        if (!input.isOn()) {
                                            input.setOn();
                                            b.setRegularImage(inputOnImg);
                                            b.setHighlightImage(inputOn2Img);
                                        } else {
                                            input.setOff();
                                            b.setRegularImage(inputOffImg);
                                            b.setHighlightImage(inputOff2Img);
                                        }
                                        components[getX][getY] = input;
                                    }
                                }
                            }
                        }
                        case 1 -> {
                            inputComponent input = new inputComponent(x, y, 1);
                            components[getX][getY] = input;
                            b.setHasComponent(true);
                            b.setRegularImage(inputOffImg);
                            b.setHighlightImage(inputOff2Img);
                        }
                        case 2 -> {
                            components[getX][getY] = null;
                            b.setHasComponent(false);
                            b.setRegularImage(gridSquareImg);
                            b.setHighlightImage(gridSquare2Img);
                        }
                    }
                }
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        for(JEDCButton b:grid) {
            if(b.getShape().contains(mouseX, mouseY)) {
                b.highlight();
            }
            else {
                b.unHighlight();
            }
        }
        for(JEDCButton b:tools) {
            if(b.getShape().contains(mouseX, mouseY)) {
                b.highlight();
            }
            else {
                b.unHighlight();
            }
        }
        repaint();
    }

}
