package problem;

import javax.media.opengl.GL2;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс задачи
 */
public class Problem {
    /**
     * текст задачи
     */
    public static final String PROBLEM_TEXT = "ПОСТАНОВКА ЗАДАЧИ:\n" +
            "На плоскости задано множество точек.\n" +
            "Найти такие две окружности, что их центры находятся в точках заданного множества, \n" +
            "Внутри каждой из этих окружностей находится хотя бы половина из всех точек заданного множества. \n" +
            "Меньший из двух радиусов минимален.";

    /**
     * заголовок окна
     */
    public static final String PROBLEM_CAPTION = "Итоговый проект ученика 10-7 Александрова Владислава";

    /**
     * путь к файлу
     */
    private static final String FILE_NAME = "points.txt";

    /**
     * список точек
     */
    private ArrayList<Point> points;
    private Circle circle;

    /**
     * Конструктор класса задачи
     */
    public Problem() {
        points = new ArrayList<>();
    }

    /**
     * Добавить точку
     *
     * @param x      координата X точки
     * @param y      координата Y точки
     */

    public void addPoint(double x, double y) {
        Point point = new Point(x, y);
        points.add(point);
    }

    /**
     * Решить задачу
     */
    public void solve() {
        for (Point p : points){
            p.solve = false;
        }
        Circle circle1 = new Circle();
        double minR = 4;
        double cenx = 0;
        double ceny = 0;
        int k = 0;
        for (Point p : points){
            for (Point p2 : points){
                circle1.x = p.x;
                circle1.y = p.y;
                circle1.SetR(p2);
                for (Point p3 : points){
                    if (circle1.inside(p3)){
                        k++;
                    }
                    if (((k == points.size() / 2) && (points.size() % 2 == 0)) || ((k == (points.size() / 2) + 1) && (points.size() % 2 == 1))){
                        if (minR - circle1.r >= 0.00001){
                            minR = circle1.r;
                            cenx = circle1.x;
                            ceny = circle1.y;
                        }
                        break;
                    }
                }
                k = 0;
            }
        }
        this.circle = new Circle(cenx, ceny, minR);
        for (Point p : points){
            if (circle.inside(p)){
                p.solve = true;
            }
        }
    }

    /**
     * Загрузить задачу из файла
     */
    public void loadFromFile() {
        points.clear();
        try {
            File file = new File(FILE_NAME);
            Scanner sc = new Scanner(file);
            // пока в файле есть непрочитанные строки
            while (sc.hasNextLine()) {
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                sc.nextLine();
                Point point = new Point(x, y);
                points.add(point);
            }
        } catch (Exception ex) {
            System.out.println("Ошибка чтения из файла: " + ex);
        }
    }

    /**
     * Сохранить задачу в файл
     */
    public void saveToFile() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME));
            for (Point point : points) {
                out.printf("%.2f %.2f %d\n", point.x, point.y);
            }
            out.close();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файл: " + ex);
        }
    }

    /**
     * Добавить заданное число случайных точек
     *
     * @param n кол-во точек
     */
    public void addRandomPoints(int n) {
        for (int i = 0; i < n; i++) {
            Point p = Point.getRandomPoint();
            points.add(p);
        }
    }

    /**
     * Очистить задачу
     */
    public void clear() {
        points.clear();
        circle = null;
    }

    /**
     * Нарисовать задачу
     *
     * @param gl переменная OpenGL для рисования
     */
    public void render(GL2 gl) {
        for (Point point : points) {
            point.render(gl);
        }
        if (circle != null){circle.render(gl);}
    }
}
