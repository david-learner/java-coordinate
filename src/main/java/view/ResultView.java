package view;

import domain.*;
import org.w3c.dom.css.Rect;

import java.util.List;

public class ResultView {
    static final int MAX = 24;
    static ShapeType shapeType;

    public static void printPoints(List<Point> points) {
        int pointIndex = 0;
        for (int yIndex = MAX; yIndex >= 1; yIndex--) {
            printYAxis(yIndex);
            pointIndex = printPoint(points, pointIndex, yIndex);
            System.out.println();
        }
        printXAxis();
    }

    private static int printPoint(List<Point> points, int pointIndex, int yIndex) {
        for (int xIndex = 1; xIndex < MAX; xIndex++) {
            if (points.size() <= pointIndex) {
                continue;
            }
            Point point = points.get(pointIndex);
            if (point.getY() != yIndex) {
                return pointIndex;
            }
            pointIndex = printX(point, pointIndex, xIndex);
        }
        return pointIndex;
    }

    private static int printX(Point point, int pointIndex, int xIndex) {
        if (point.getX() == xIndex) {
            System.out.print(String.format("%2s", "●"));
            return ++pointIndex;
        }
        System.out.print(String.format("%2s", "  "));
        return pointIndex;
    }

    private static Boolean printYAxis(int index) {
        if (index % 2 == 0) {
            System.out.print(String.format("%-2s" + "|", index));
            return true;
        }
        System.out.print(String.format("%-2s" + "|", "  "));
        return false;
    }

    private static void printXAxis() {
        System.out.print("  " + "+");
        for (int i = 0; i < MAX; i++) {
            System.out.print(String.format("%2s", "--")); // dash가 1개이면 x축이 짧아보여서 2개로 변경
        }
        System.out.print("\n" + String.format("%-3s", "0"));
        for (int i = 1; i < 13; i++) {
            System.out.print(String.format("%4s", i * 2));
        }
    }

    /*
    만약, Shape에서 line만 별개로 떼어낸다면, Triangle과 Rectangle을
    하나의 추상 클래스로 묶어낼 수 있다. 그러면 굳이 hasLineCount로 어떤 shape인지
    구분할 필요가 없으며, ShapeFactory에서 생성할 때만 신경써주면 된다.
    다형성으로 인해 각각의 클래스(Triangle, Rectangle)에서 재정의된 메소드가 호출될테니.
     */
    public static void printCalculation(Shape shape) {
        if (shape.hasLineCount(shapeType.LINE.getLineCount())) {
            Line line = (Line) shape;
            System.out.println("\n두 점 사이 거리는 " + line.getDistance());
        }
        if (shape.hasLineCount(shapeType.TRIANGLE.getLineCount())) {
            Triangle triangle = (Triangle) shape;
            System.out.println("\n삼각형 넓이는 " + triangle.getArea());
        }
        if (shape.hasLineCount(shapeType.RECTANGLE.getLineCount())) {
            Rectangle rectangle = (Rectangle) shape;
            System.out.println("\n사각형의 넓이는 " + rectangle.getArea());
        }
    }
}