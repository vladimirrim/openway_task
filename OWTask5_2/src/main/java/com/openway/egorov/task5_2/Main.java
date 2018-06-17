package com.openway.egorov.task5_2;

public class Main {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Invalid number of arguments.Expected: 4 real numbers.");
            return;
        }
        double a, b, c, d;
        try {
            a = Double.parseDouble(args[0]);
            b = Double.parseDouble(args[1]);
            c = Double.parseDouble(args[2]);
            d = Double.parseDouble(args[3]);
        } catch (NumberFormatException e) {
            System.out.println("Sorry! Looks like error occurred during parsing your numbers.Error:" + e.getLocalizedMessage());
            return;
        }
        double ans = (a + b) / (c + d);
        System.out.println(ans);
    }
}
