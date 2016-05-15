package my.hw1.ru;

import org.testng.Assert;
import org.testng.annotations.Test;

import static my.hw1.ru.HomeWork.Distance;

/**
 * Created by ds on 15.5.16.
 */
public class DistanceTest {

    @Test
    public void testArea(){

        Point p1 = new Point(10,20);

        Point p2 = new Point(42,99);
        Assert.assertEquals(Distance(p1,p2), 85.23496934944014);


    }
}
