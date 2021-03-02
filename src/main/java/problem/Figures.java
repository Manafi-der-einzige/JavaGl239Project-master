package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2GL3;

public class Figures {
    public static void renderPoint(GL2 gl, Vector2 pos, float size){
        gl.glPointSize(size);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2d(pos.getX(), pos.getY());
        gl.glEnd();
    }
    public static void renderLine(GL2 gl, Vector2 pos1, Vector2 pos2, float width){
        gl.glLineWidth(width);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2d(pos1.getX(), pos1.getY());
        gl.glVertex2d(pos2.getX(), pos2.getY());
        gl.glEnd();
    }
    public static void renderTraingle(GL2 gl, Vector2 pos1, Vector2 pos2, Vector2 pos3, boolean filled){
        if (filled) {
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex2d(pos1.getX(), pos1.getY());
            gl.glVertex2d(pos2.getX(), pos2.getY());
            gl.glVertex2d(pos3.getX(), pos3.getY());
            gl.glEnd();
        }
        else{
            gl.glBegin(GL.GL_LINE_STRIP);
            gl.glVertex2d(pos1.getX(), pos1.getY());
            gl.glVertex2d(pos2.getX(), pos2.getY());
            gl.glVertex2d(pos3.getX(), pos3.getY());
            gl.glVertex2d(pos1.getX(), pos1.getY());
            gl.glEnd();
        }
    }
    public static void renderQuad(GL2 gl, Vector2 pos1, Vector2 pos2, Vector2 pos3, Vector2 pos4, boolean filled){
        if (filled) {
            gl.glBegin(GL2GL3.GL_QUADS);
            gl.glVertex2d(pos1.getX(), pos1.getY());
            gl.glVertex2d(pos2.getX(), pos2.getY());
            gl.glVertex2d(pos3.getX(), pos3.getY());
            gl.glVertex2d(pos4.getX(), pos4.getY());
            gl.glEnd();
        }
        else{
            gl.glBegin(GL.GL_LINE_STRIP);
            gl.glVertex2d(pos1.getX(), pos1.getY());
            gl.glVertex2d(pos2.getX(), pos2.getY());
            gl.glVertex2d(pos3.getX(), pos3.getY());
            gl.glVertex2d(pos4.getX(), pos4.getY());
            gl.glVertex2d(pos1.getX(), pos1.getY());
            gl.glEnd();
        }
    }
    public static void renderCircle(GL2 gl, Vector2 cen, double r, boolean filled){
        if (filled){
            double x;
            double y;
            double a;
            gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex2d(cen.getX(), cen.getY());

        }
    }
}
