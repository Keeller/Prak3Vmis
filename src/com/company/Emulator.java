package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Emulator {

    public static void run(){
       CommandMeneger.init();
       int j=0;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

       while (j<4){
           try {
               System.out.println("Введите значение элемента массива");
               String line=br.readLine();
               MemmoryManager.memmory.set(6 + j, line);
           }
           catch (Exception ex){
               ex.printStackTrace();
           }
           j++;
       }


    }
}
