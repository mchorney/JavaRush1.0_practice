package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Order;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet {

    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    final int number;

    public Tablet(int number) {
        this.number = number;
    }

    public void createOrder(){
        Order order = null;
        try {
            order = new Order(this);
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
        System.out.println(order);
    }

    @Override
    public String toString() {
        return "Tablet{number=" + number + "}";
    }
}