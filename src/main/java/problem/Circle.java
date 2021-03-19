package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class Circle {
    double x;
    double y;
    double r;
    Circle(){
        r = 1;
        x = 0;
        y = 0;
    }
    Circle(double x, double y, double r){
        this.r = r;
        this.x = x;
        this.y = y;
    }
    public boolean inside(Point p){
        if (Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y)) <= r){
            return true;
        }
        else{
            return false;
        }
    }
    public void SetR(Point p){
        r = Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
    }
    public void render(GL2 gl){
        Figures.renderCircle(gl, new Vector2(x, y), r, false);
    }
}
