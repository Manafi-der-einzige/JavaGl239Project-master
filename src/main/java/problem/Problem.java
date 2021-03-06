package problem;

import javax.media.opengl.GL2;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Класс задачи
 */
public class    Problem {
    /**
     * текст задачи
     */
    public static final String PROBLEM_TEXT = "ПОСТАНОВКА ЗАДАЧИ:\n" +
            "На плоскости задано множество точек.\n" +
            "Найти такие две окружности,\n" +
            "Что их центры находятся в точках заданного множества, \n" +
            "Внутри каждой из этих окружностей \n" +
            "Находится хотя бы половина из всех точек заданного множества. \n" +
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
    /**
     * circle - минимальная окружность
     * Rcircle - вторичная.
     */
    private Circle circle;
    private Circle Rcircle;

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
        for (Point p : points){ // Перекрашиваем точки в обычный цвет, если они были перекрашены при предыдущем решении
            p.solve = false;// solve - поле класса Point, отвечающее за цвет.
        }
        Circle circle1 = new Circle(); // Временная окружность, в которую мы перезаписываем все перебираемые окружности
        circle = new Circle(0, 0, 4);
        int k = 0; // Счётчик точек, находящихся внутри окружности.
        for (Point p1 : points){ // Перебираем точки центра окружности
            for (Point p2 : points){ // Перебираем точки радиуса окружности
                circle1.x = p1.x;
                circle1.y = p1.y;
                circle1.SetR(p2);
                for (Point p3 : points){ // Перебираем точки множества
                    if (circle1.inside(p3)){ // Записываем, сколько их лежит внутри перебираемой окружности
                        k++;
                    }
                    if (((k == points.size() / 2) && (points.size() % 2 == 0)) || ((k == (points.size() / 2) + 1) && (points.size() % 2 == 1))){
                        /**
                         * Перестаём перебирать точки, когда уже ясно, что внутри окружности содержится половина всего множества точек.
                         * Если эта окружность меньше минимальной найденной(которая записывается в circle), то записываем её как минимальную.
                         */
                        if (circle.r - circle1.r > 0.001){
                            circle.r = circle1.r;
                            circle.x = circle1.x;
                            circle.y = circle1.y;
                        }
                        break;
                    }
                }
                k = 0;
            }
        }
        Rcircle = new Circle(0, 0, 4);
        k = 0;
        // Далее проводим аналогичные действия со второй окружность, проверяя, не совпадают ли центры первой и второй.
        for (Point p1 : points){
            for (Point p2 : points){
                if ((Math.abs(p1.x - circle.x) > 0.001) && (Math.abs(p1.y - circle.y) > 0.001)) {
                    circle1.x = p1.x;
                    circle1.y = p1.y;
                    circle1.SetR(p2);
                    for (Point p3 : points) {
                        if (circle1.inside(p3)) {
                            k++;
                        }
                        if (((k == points.size() / 2) && (points.size() % 2 == 0)) || ((k == (points.size() / 2) + 1) && (points.size() % 2 == 1))) {
                            if (Rcircle.r > circle1.r) {
                                Rcircle.r = circle1.r;
                                Rcircle.x = circle1.x;
                                Rcircle.y = circle1.y;
                            }
                            break;
                        }
                    }
                }
                k = 0;
            }
        }
        for (Point p : points){ // Перебираем точки множества, перекрашиваем, если они лежат внутри минимальной окружности.
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
                out.printf("%.2f %.2f\n", point.x, point.y);
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
        Rcircle = null;
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
        if (Rcircle != null){
            Rcircle.render(gl);
        }
    }
}
