public final class Data {


    public static final int DEFAULT_X = 3;
    public static final int DEFAULT_Y = 1;
    public static final int WINDOW_WIDTH = 250;
    public static final int WINDOW_HEIGHT = 500;

    public static final String[] colors = {
            "#ffffff",
            "#ff0000",
            "#bf00ff",
            "#0000ff",
            "#00ffff",
            "#00ff00",
            "#ffff00",
            "#ff9400"
    };

    public static final String border = "#af8600";

    public static final int[][][] tetrominoe = {
            {{}, {}, {}, {}}, //noShape
            { {0, -1}, {0, 0}, {-1, 0}, {-1, 1} },
            { {-1, -1}, {-1, 0}, {0, 0}, {0, 1} },
            { {0, -1}, {0, 0}, {0, 1}, {0, 2} },
            { {0, 0}, {1, 0}, {1, 1}, {0, 1} },    //cube 3
            { {0, -1}, {-1, 0}, {0, 0}, {1, 0} },
            { {0, -1}, {0, 0}, {0, 1}, {1, 1} },
            { {1, -1}, {1, 0}, {1, 1}, {0, 1} }
    };

}