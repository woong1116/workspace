package day08;

abstract class Shape {

    public abstract double getArea();
    public abstract double getPerimeter();

}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }
}

public class Exam02 {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[2];
        shapes[0] = new Circle(5.0);
        shapes[1] = new Rectangle(15.0, 15.0);

        for (int i = 0; i < shapes.length; i++) {
            System.out.print("Circle - Area: " + shapes[i].getArea()+ ", Perimeter: " + shapes[i].getPerimeter());
            System.out.print(shapes[i].getArea());
            System.out.println(", Perimeter: " + shapes[i].getPerimeter());}
    }
}
