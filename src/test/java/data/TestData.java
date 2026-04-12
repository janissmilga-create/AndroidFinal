package data;

import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider(name = "bookingData")
    public Object[][] bookingData() {
        return new Object[][] {
                {"13 April 2026", "14 April 2026"},
                {"15 April 2026", "16 April 2026"},
                {"17 April 2026", "18 April 2026"}
        };
    }
}
