import javax.swing.*;

public class JEDCDriver {

    public static JEDCGraphics screen;

    public static void main(String[] args) {
        screen = new JEDCGraphics();
        JFrame frame = new JFrame("Digital Circuitry");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(screen);
        frame.setVisible(true);
    }


}
