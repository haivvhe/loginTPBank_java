/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ThinkPro
 */
public class Validate {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public int getInt(String msg, String err, int min, int max) {
        int num = 0;
        boolean check;
        do {
            check = true;
            try {
                System.out.print(msg);
                num = Integer.parseInt(in.readLine());
                if (num < min || num > max) {
                    System.out.println("Number entered out of range");
                    check = false;
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println(err);
                check = false;
            }
        } while (!check);
        return num;
    }
}
