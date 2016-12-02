package com.example.asus.likemap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class InfoTest{
    @Test
    public void getID(){
        Info test1 = new Info("","123","123","123","123");
        assertEquals(test1.getID(),"");
        Info test2 = new Info(null,"","","","");
        assertEquals(test2.getID(),null);
        Info test3 = new Info("aaa","","","","");
        assertEquals(test3.getID(),"aaa");
        Info test4 = new Info("123","","","","");
        assertEquals(test4.getID(),"123");

    }
    @Test
    public void getBuildingTest(){
        Info test1 = new Info("123","","123","123","123");
        assertEquals(test1.getBuilding(),"");
        Info test2 = new Info("",null,"","","");
        assertEquals(test2.getBuilding(),null);
        Info test3 = new Info("","aaa","","","");
        assertEquals(test3.getBuilding(),"aaa");
        Info test4 = new Info("","123","","","");
        assertEquals(test4.getBuilding(),"123");
    }
    @Test
    public void getRoomTest(){
        Info test1 = new Info("123","123","","123","123");
        assertEquals(test1.getRoom(),"");
        Info test2 = new Info("","",null,"","");
        assertEquals(test2.getRoom(),null);
        Info test3 = new Info("","","aaa","","");
        assertEquals(test3.getRoom(),"aaa");
        Info test4 = new Info("","","123","","");
        assertEquals(test4.getRoom(),"123");
    }
    @Test
    public void getPicTest(){

        Info test1 = new Info("","","","123","");
        assertEquals(test1.getPic(),123);
        String s = "";
        try {
            Info test2 = new Info("", "", "","aaa","");
            test2.getPic();
        }catch(Throwable e) {
            s = "1";
        }
        assertEquals(s,"1");
        try {
            Info test3 = new Info("", "", "",null,"");
            test3.getPic();
        }catch(Throwable e) {
            s = "2";
        }
        assertEquals(s,"2");
        try {
            Info test4 = new Info("", "", "","","");
            test4.getPic();
        }catch(Throwable e) {
            s = "3";
        }
        assertEquals(s,"3");
        try {
            Info test5 = new Info("", "", "","9999999999999999999999999999999999999999999999","");
            test5.getPic();
        }catch(Throwable e) {
            s = "4";
        }
        assertEquals(s,"4");
    }
    @Test
    public void getTextTest() {
        Info test1 = new Info("123", "123", "123", "123", "");
        assertEquals(test1.getText(), "");
        Info test2 = new Info("123", "123", "123", "123", null);
        assertEquals(test2.getText(), null);
        Info test3 = new Info("123", "123", "123", "123", "aaa");
        assertEquals(test3.getText(), "aaa");
        Info test4 = new Info("123", "123", "123", "123", "123");
        assertEquals(test4.getText(), "123");
    }
}