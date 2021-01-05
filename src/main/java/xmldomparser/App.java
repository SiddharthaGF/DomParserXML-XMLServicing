package main.java.xmldomparser;
/*
    author: @pablo_guerra_6502, @andre_robalino_6481
*/
public class App {
    public static void main(String[] args) {
        DomParser dp = new DomParser("https://guarded-springs-28309.herokuapp.com/employees");
        try {
            dp.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
