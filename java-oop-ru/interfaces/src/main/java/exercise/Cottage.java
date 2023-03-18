package exercise;

// BEGIN
public class Cottage implements Home {
    double area;
    int floorCount;

    public Cottage (double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }
    @Override
    public double getArea() {
        return this.area;
    }

    @Override
    public int compareTo(Home another) {
        double thisArea = this.getArea();
        double anotherArea = another.getArea();
        int result = 0;
        result = (thisArea > anotherArea) ? 1 : -1;
        return result;
    }

    public String toString () {
        return this.floorCount + " этажный коттедж площадью " + this.getArea() + " метров";
    }
}
// END
