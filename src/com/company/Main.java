package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Emulator.run();
        try(FileReader reader=new FileReader("C:\\Users\\79636\\IdeaProjects\\untitled4\\src\\com\\company\\Programm.txt"))
        {
            int currString=0;
            BufferedReader bufferedReader=new BufferedReader(reader);
            String line=bufferedReader.readLine();


            while (line!=null){

                MemmoryManager.CS.add(line);
                if(!(line.contains("mov")|line.contains("add")|line.contains("xor")|line.contains("cmp")|line.contains("loop")|line.contains("inc")))
                    MemmoryManager.Labels.put(line,MemmoryManager.CS.size()-1);
                line=bufferedReader.readLine();

            }
            MemmoryManager.CS.add("END");
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        CommandMeneger.translateCommand();
        System.out.println("Максимум");
        System.out.println(MemmoryManager.memmory.get(MemmoryManager.EBX));
    }
}
