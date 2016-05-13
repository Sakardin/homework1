package my.hw1.ru;

/**
 * Created by ds on 12.5.16.
 */
public class HomeWork {

  public static void main(String args[]) {

    Point p1 = new Point(10,20);

    Point p2 = new Point(42,99);


    System.out.println("Точка р1 x = " + p1.x + " у = " + p1.y);

    System.out.println("Точка р2 x = " + p2.x + " у = " + p2.y);
    System.out.println("Растояние между точками р1 и р2 x =	" + Distance(p1,p2));

  }
  public static double Distance(Point p1, Point p2) {

    return Math.sqrt( Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y-p2.y, 2) );
  }




  }


