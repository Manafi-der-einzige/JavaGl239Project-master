package problem;

public class Vector2 {
    double x;
    double y;
    public Vector2(){
        x = 1;
        y = 0;
    }
    @Override
    public String toString(){
        return String.format("(%.2f,%.2f)", x, y);
    }
    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Vector2(Vector2 v){
        y =v.y;
        x = v.x;
    }
    public double getY(){
        return y;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double len(){
        return Math.sqrt(x*x+y*y);
    }
    public void x(double a){
        x = x * a;
        y = y * a;
    }
    public void plus(Vector2 v){
        x = getX() + v.getX();
        y = getY() + v.getY();
    }
    public void minus(Vector2 v){
        x = getX() - v.getX();
        y = getY() - v.getY();
    }
    public Vector2 sum(Vector2 v){
        return new Vector2(getX() + v.getX(), getY() + v.getY());
    }
    public static Vector2 sum(Vector2 v1, Vector2 v2){
        return new Vector2(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }
    public Vector2 mult(double a){
        return new Vector2(getX() * a, getY() * a);
    }
    public double mult(Vector2 v){
        return (v.len() * v.len() + len() * len() - sum(v.mult(-1)).len() * sum(v.mult(-1)).len()) / 2;
    }
    public static double mult(Vector2 v1, Vector2 v2){
        return (v1.len() * v1.len() + v2.len() * v2.len() - v2.sum(v1.mult(-1)).len() * v2.sum(v1.mult(-1)).len()) / 2;
    }
    public static Vector2 mult(Vector2 v, double a){
        return new Vector2(v.getX() * a, v.getY() * a);
    }
    public void normalize(){
        if (len() == 0){
            x = 0;
            y = 0;
        }
        else{
            x = getX() / len();
            if (y < 0){
                y = -Math.sqrt(1 - getX()*getX());
            }
            else y = Math.sqrt(1 - getX()*getX());
        }
    }
    public Vector2 norm(){
        if (len() == 0){
            return new Vector2(0, 0);
        }
        return new Vector2(getX() / len(), getY() / len());
    }
    public void rotate(double a){
        double l = len();
        double sin = Math.sin(a) * getX() / l + Math.cos(a) * getY() / l;
        double cos = Math.cos(a) * getX()/l - Math.sin(a) * getY()/ l;
        setY(sin * l);
        setX(l * cos);
    }
    public Vector2 rotated(double a){
        double sin = Math.sin(a) * getX() / len() + Math.cos(a) * getY() / len();
        double cos = Math.cos(a) * getX()/len() - Math.sin(a) * getY()/ len();
        return new Vector2(len() * cos, sin * len());
    }
    public Vector2 ort(){
        return rotated(Math.PI / 2);
    }
    public double phi(){
        return Math.acos(getX() / len());
    }
    public int getQuarte(){
        if ((x == 0) || (y == 0)){
            return 0;
        }
        if (x > 0){
            if (y < 0){
                return 4;
            }
            return 1;
        }
        if ((x < 0) && (y < 0)){
            return 3;
        }
        return 2;
    }
    public boolean equals(Vector2 v){
        if ((v.getX() == getX()) && (v.getY() == getY())){
            return true;
        }
        return false;
    }
}