import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class BoardFrame extends JFrame implements TetrisFrame {

    private Board board;
    private JLabel scores;

    public BoardFrame() {
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, Data.WINDOW_WIDTH + 250, Data.WINDOW_HEIGHT + 40);
        setTitle("Tetris");
        board = new Board(this);
        scores = new JLabel();
        scores.setVerticalTextPosition(JLabel.CENTER);
        scores.setHorizontalTextPosition(JLabel.CENTER);
        scores.setFont(new Font(scores.getFont().getName(), Font.BOLD, 20));
        scores.setBounds(320, 100, 200, 200);
        scores.setText("SCORES: 0");

        board.setFocusable(true);
        board.requestFocusInWindow();

        board.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {

                    case KeyEvent.VK_UP:
                        upPressed();
                        break;

                    case KeyEvent.VK_DOWN:
                        downPressed();
                        break;

                    case KeyEvent.VK_LEFT:
                        leftPressed();
                        break;

                    case KeyEvent.VK_RIGHT:
                        rightPressed();
                        break;

                    case KeyEvent.VK_SPACE:
                        spacePressed();
                        break;

                }
            }
        });

        board.setLayout(null);
        board.setSize(Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT);
        board.add(scores);
        add(board);
        setResizable(false);
    }

    private void spacePressed(){
        board.tryFall();
    }

    private void upPressed() {
        board.tryRotateLeft();
    }

    private void downPressed() {
        board.tryRotateRight();
    }

    private void leftPressed() {
        board.tryMoveLeft();
    }

    private void rightPressed() {
        board.tryMoveRight();
    }

    @Override
    public void onScoresChanged() {
        scores.setText("SCORES: " + board.scores);
        System.out.println(scores.getText());
    }
}
