package problem;

public class Circle {
    double x;
    double y;
    double r;
    public void Circle(){
        r = 1;
        x = 0;
        y = 0;
    }
    public void Circle(double x, double y, double r){
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
    public void SetCen(Point p){
        x = p.x;
        y = p.y;
    }
}
