public class component {
    private final int x;
    private final int y;
    private final int type;

    public component(int xPos, int yPos, int t) {
        x = xPos;
        y = yPos;
        type = t;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

}
