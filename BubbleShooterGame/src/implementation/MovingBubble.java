package implementation;

import model.Bubble;
import utils.Constants;

import java.awt.Point;

public class MovingBubble extends Bubble {

    private boolean moving;
    private double step_x;
    private double step_y;
    private double coordinate_x;
    private double coordinate_y;
    private static double STEP = 5;



    public MovingBubble(Bubble b, Point dir) {
        super(b.getColor());
        loc = new Point(b.getLocation());
        coordinate_x = loc.x;
        coordinate_y = loc.y;
        setVisible(true);
        moving = true;
        double offset_x = dir.x - Constants.FIELD_SIZE_X / 2;
        double offset_y = dir.y - Constants.FIELD_SIZE_Y;
        double dist = Math.sqrt(Math.pow(offset_x, 2) + Math.pow(offset_y, 2));
        step_x = offset_x / dist * STEP;
        step_y = offset_y / dist * STEP;
    }


    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean x) {
        moving = x;
    }

    public void move() {
        if (coordinate_x + step_x < 0) {
            coordinate_x = (int) -(coordinate_x + step_x);
            step_x = -step_x;
        } else if (coordinate_x + step_x > Constants.FIELD_SIZE_X - 1 - 2 * (Bubble.RADIUS + 1)) {
            coordinate_x = (int) ((Constants.FIELD_SIZE_X - 1 - 2 * (Bubble.RADIUS + 1)) * 2 - (coordinate_x + step_x));
            step_x = -step_x;
        } else
            coordinate_x += step_x;
        coordinate_y += step_y;
        loc.x = (int) coordinate_x;
        loc.y = (int) coordinate_y;
    }

}
