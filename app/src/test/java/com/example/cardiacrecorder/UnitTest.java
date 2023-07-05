package com.example.cardiacrecorder;

        import static org.junit.Assert.assertEquals;

        import org.junit.Assert;
        import org.junit.Test;

public class UnitTest {
    @Test
    public void testGetDate() {
        single_record record = new single_record("05/07/2023", "12:30", "120", "80", "70",
                "Unit Test", "abc", "def");

        assertEquals("05/07/2023", record.getDate());
    }

    @Test
    public void testGetTime() {
        single_record record = new single_record("05/07/2023", "12:30", "120", "80", "70",
                "Unit Test", "abc", "def");

        assertEquals("12:30", record.getTime());
    }

    @Test
    public void testGetSys() {
        single_record record = new single_record("05/07/2023", "12:30", "120", "80", "70",
                "Unit Test", "abc", "def");
    }
}
