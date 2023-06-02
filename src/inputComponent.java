public class inputComponent extends component {

    private boolean on;

    public inputComponent(int x, int y, int type) {
        super(x, y, type);
        on = false;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn() {
        on = true;
    }

    public void setOff() {
        on = false;
    }

}
