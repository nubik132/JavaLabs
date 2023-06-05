package Lab4;

import Lab4.Bunnies.Mike;
import Lab4.Turtles.Gus;
import Lab4.Turtles.Tortuga;

public class Lab4 {
    public static void main(String args[]) {
        Mike mike = new Mike();
        mike.Bite();
        System.out.println("--------------------");
        Tortuga tortuga = new Tortuga();
        Gus gus = new Gus();
        tortuga.Hi();
        tortuga.Bum();
        gus.Hi();
        gus.Bum();
    }
}