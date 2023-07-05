package com.example.cardiacrecorder;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the various methods in the application.
 */
public class UnitTest {

    /**
     * Test for the getDate method in the single_record class.
     */
    @Test
    public void testGetDate(){
        single_record record = new single_record("05/07/2023","12:30","120","80","70",
                "Unit Test","abc","def");

        assertEquals("05/07/2023",record.getDate());
    }

    /**
     * Test for the getTime method in the single_record class.
     */
    @Test
    public void testGetTime(){
        single_record record = new single_record("05/07/2023","12:30","120","80","70",
                "Unit Test","abc","def");

        assertEquals("12:30",record.getTime());
    }

    /**
     * Test for the getSys method in the single_record class.
     */
    @Test
    public void testGetSys(){
        single_record record = new single_record("05/07/2023","12:30","120","80","70",
                "Unit Test","abc","def");

        assertEquals("120",record.getSys());
    }

    /**
     * Test for the getDias method in the single_record class.
     */
    @Test
    public void testGetDias(){
        single_record record = new single_record("05/07/2023","12:30","120","80","70",
                "Unit Test","abc","def");

        assertEquals("80",record.getDias());
    }

    /**
     * Test for the getRate method in the single_record class.
     */
    @Test
    public void testGetRate(){
        single_record record = new single_record("05/07/2023","12:30","120","80","70",
                "Unit Test","abc","def");

        assertEquals("70",record.getRate());
    }



    /**
     * Test for the getComment method in the single_record class.
     */
    @Test
    public void testGetComment(){
        single_record record = new single_record("05/07/2023","12:30","120","80","70",
                "Unit Test","abc","def");

        assertEquals("Unit Test",record.getComment());
    }

    @Test
    public void testGetUserID(){
        single_record record = new single_record("05/07/2023","12:30","120","80","70",
                "Unit Test","abc","def");

        assertEquals("abc",record.getUserID());
    }


    /**
     * Test for the getUserID method in the single_record class.
     */
    @Test
    public void testgetUserid(){
        UserInfo userInfo = new UserInfo("abc","Zakaria","01783067522","zakahossain22@gmail.com",
                "5'5","68");

        assertEquals("abc",userInfo.getUserid());
    }

    @Test
    public void testgetName(){
        UserInfo userInfo = new UserInfo("abc","Zakaria","01783067522","zakahossain22@gmail.com",
                "5'5","68");

        assertEquals("Zakaria",userInfo.getName());
    }

    /**
     * Test for the getUserid method inthe UserInfo class.
     */
    @Test
    public void testgetPhone(){
        UserInfo userInfo = new UserInfo("abc","Zakaria","01783067522","zakahossain22@gmail.com",
                "5'5","68");

        assertEquals("01783067522",userInfo.getPhone());
    }

    /**
     * Test for the getName method in the UserInfo class.
     */
    @Test
    public void testgetEmail(){
        UserInfo userInfo = new UserInfo("abc","Zakaria","01783067522","zakahossain22@gmail.com",
                "5'5","68");

        assertEquals("zakahossain22@gmail.com",userInfo.getEmail());
    }

    /**
     * Test for the getHeight method in the UserInfo class.
     */
    @Test
    public void getHeight(){
        UserInfo userInfo = new UserInfo("abc","Zakaria","01783067522","zakahossain22@gmail.com",
                "5'5","68");

        assertEquals("5'5",userInfo.getHeight());
    }

    /**
     * Test for the getWeight method in the UserInfo class.
     */
    @Test
    public void testgetWeight(){
        UserInfo userInfo = new UserInfo("abc","Zakaria","01783067522","zakahossain22@gmail.com",
                "5'5","68");

        assertEquals("68",userInfo.getWeight());
    }


}
