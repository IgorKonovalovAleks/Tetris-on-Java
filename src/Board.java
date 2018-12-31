import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class Board extends JPanel {

    private Figure figure;
    private int[][] filled;
    private Timer timer;
    public int width;
    public int height;
    public int scores;
    TetrisFrame parentFrame;

    private ActionListener actionListener;

    {
        actionListener = e -> timerEvent();
    }

    public Board(TetrisFrame frame) {
        parentFrame = frame;
        filled = new int[10][20];
        width = Data.WINDOW_WIDTH / 10;
        height = Data.WINDOW_HEIGHT / 20;
        timer = new Timer(500, actionListener);
        timer.setRepeats(true);
        timer.start();
        newTurn();
        scores = 0;
    }

    private void timerEvent(){
        tryMoveDown();
        int n = 0;
        int count = -1;
        while (n != -1) {
            n = checkFullLines();
            if (n != -1) removeLine(n);
            count++;
        }
        scores += 100 * count;
        parentFrame.onScoresChanged();
    }

    private void removeLine(int line) {
        for (; line > 0; line--) {
            for (int j = 0; j < 10; j++){
                filled[j][line] = filled[j][line - 1];
            }
        }
    }

    private int checkFullLines(){
        for (int i = 0; i < 20; i++){
            if (checkLine(i)){
                return i;
            }
        }
        return -1;
    }

    private boolean checkLine(int n){
        for (int i = 0; i < 10; i++){
            if(filled[i][n] == 0) {
                return false;
            }
        }
        return true;
    }

    private void newTurn(){
        Random random = new Random();
        drop();
        figure = new Figure(Math.abs(random.nextInt()) % 6 + 1);
        if (!isPossibleDown(figure.position)){
            gameOver();
        }
    }

    private void gameOver(){
        timer.stop();
    }

    private void drop(){
        if (figure == null) return;
        for (int i = 0; i < 4; i++){
            filled[figure.position[i][0]][figure.position[i][1]] = figure.color;
        }
    }

    public void tryFall(){
        while (tryMoveDown());
    }

    public boolean tryMoveDown(){
        //copy current position
        int[][] next = new int[4][2];
        for (int i = 0; i < 4; i++){
            next[i][0] = figure.position[i][0];
            next[i][1] = figure.position[i][1];
        }
        //predict next position
        for (int[] cell : next){
            cell[1]++;
        }
        //check validity
        if (isPossibleSides(next) && isPossibleDown(next)){
            figure.moveDown();
            return true;
        } else if (!isPossibleDown(next)){
            newTurn();
        }
        return false;
    }

    public void tryRotateLeft(){
        //copy current position and predict next shape
        int[][] next = Figure.predictRotateLeft(figure.shape);
        //predict next position
        for (int[] cell : next){
            cell[0] += figure.getX();
            cell[1] += figure.getY();
        }
        //check validity
        if (isPossibleSides(next) && isPossibleDown(next)){
            figure.rotateLeft();
        }
    }

    public void tryRotateRight(){
        int[][] next = Figure.predictRotateRight(figure.shape);
        //predict next position
        for (int[] cell : next){
            cell[0] += figure.getX();
            cell[1] += figure.getY();
        }
        //check validity
        if (isPossibleSides(next) && isPossibleDown(next)){
            figure.rotateRight();
        }
    }

    public void tryMoveLeft(){
        //copy current position
        int[][] next = new int[4][2];
        for (int i = 0; i < 4; i++){
            next[i][0] = figure.position[i][0];
            next[i][1] = figure.position[i][1];
        }
        //predict next position
        for (int[] cell : next){
            cell[0]--;
        }
        //check validity
        if (isPossibleSides(next) && isPossibleDown(next)){
            figure.moveLeft();
        }
    }

    public void tryMoveRight(){
        int[][] next = new int[4][2];
        for (int i = 0; i < 4; i++){
            next[i][0] = figure.position[i][0];
            next[i][1] = figure.position[i][1];
        }
        for (int[] cell : next){
            cell[0]++;
        }
        if (isPossibleSides(next) && isPossibleDown(next)){
            figure.moveRight();
        }
    }

    private boolean isPossibleSides(int[][] position){
        for(int[] cell : position){
            if (cell[0] > 9
                    || cell[0] < 0){
                return false;
            }
        }
        return true;
    }

    private boolean isPossibleDown(int[][] position){
        for(int[] cell : position){
            try {
                if (cell[1] < 0
                        || cell[1] > 19
                        || filled[cell[0]][cell[1]] != 0) {
                    return false;
                }
            } catch (java.lang.ArrayIndexOutOfBoundsException ignored) {}
        }
        return true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        paint(g2d);
        repaint();
    }

    private void paint(Graphics2D g) {
        g.setColor(Color.decode(Data.border));
        g.drawLine(0, 0, Data.WINDOW_WIDTH, 0);
        g.drawLine(Data.WINDOW_WIDTH, 0, Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT);
        g.drawLine(Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT, 0, Data.WINDOW_HEIGHT);
        g.drawLine(0, Data.WINDOW_HEIGHT, 0, 0);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                if (filled[i][j] != 0) {
                    drawTetrominoe(g, j, i, Data.colors[filled[i][j]]);
                }
            }
        }
        for (int[] cell : figure.position){
            drawTetrominoe(g, cell[1], cell[0], Data.colors[figure.color]);
        }

    }

    private void drawTetrominoe(Graphics2D g, int i, int j, String color){
        g.setColor(Color.decode(color));
        g.fillRect(j * width, i * height, width, height);
        g.setColor(Color.decode(Data.border));
        g.drawLine(j * width, i * height, j * width + width, i * height);
        g.drawLine(j * width, i * height, j * width, i * height + height);
        g.drawLine(j * width + width, i * height, j * width + width, i * height + height);
        g.drawLine(j * width, i * height + height, j * width + width, i * height + height);
    }
}