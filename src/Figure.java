public class Figure {

    public int color;
    public int[][] shape;
    public int[][] position;
    private int x;
    private int y;

    public Figure(int type){
        for (int[] i : shape = Data.tetrominoe[type]);
        color = type;
        position = new int[4][2];
        x = Data.DEFAULT_X;
        y = Data.DEFAULT_Y;
        updatePosition();
    }

    public static int[][] predictRotateLeft(int[][] shape){
        int[][] newShape = new int[4][2];
        for (int i = 0; i < 4; i++){
            newShape[i][0] = shape[i][1];
            newShape[i][1] = -shape[i][0];
        }
        return newShape;
    }

    public static int[][] predictRotateRight(int[][] shape){
        int[][] newShape = new int[4][2];
        for (int i = 0; i < 4; i++){
            newShape[i][0] = -shape[i][1];
            newShape[i][1] = shape[i][0];
        }
        return newShape;
    }

    public void moveLeft(){
        x--;
        updatePosition();
    }

    public void moveRight(){
        x++;
        updatePosition();
    }

    public void rotateLeft(){
        shape = predictRotateLeft(shape);
        updatePosition();
    }

    public void rotateRight(){
        shape = predictRotateRight(shape);
        updatePosition();
    }

    public void moveDown(){
        y++;
        updatePosition();
    }

    private void updatePosition(){
        for (int i = 0; i < 4; i++){
            position[i][0] = shape[i][0] + x;
            position[i][1] = shape[i][1] + y;
        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
