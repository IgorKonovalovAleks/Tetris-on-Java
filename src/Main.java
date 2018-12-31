import javax.swing.*;
import java.awt.*;

public class Main {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
                try {
                    Main window = new Main();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        );
    }

    public Main() {
        initialize();
    }

    private void setVisible(boolean b) {
        frame.setVisible(b);
    }

    private void initialize() {
        frame = new BoardFrame();
    }
}
