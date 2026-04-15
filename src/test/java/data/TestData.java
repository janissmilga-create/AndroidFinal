package data;

import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider(name = "bookingData")
    public Object[][] bookingData() {
        return new Object[][] {
                {"17 April 2026", "18 April 2026"},
                {"19 April 2026", "20 April 2026"},
                {"21 April 2026", "22 April 2026"}
        };
    }
}
