package net.time4j;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses(
    {
        NavigationOperatorTest.class,
        OrdinalWeekdayTest.class,
        RatioTest.class,
        RoundingTest.class,
        SetLenientTest.class,
        StdDateOperatorTest.class,
        YOWOperatorTest.class
    }
)
public class OperatorSuite {

}