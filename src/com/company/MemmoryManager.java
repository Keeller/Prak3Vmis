package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemmoryManager {

   public static List<String> memmory=new ArrayList<>();
   public static HashMap<String,List<Integer>> DS=new HashMap<>();
   public static List<String> CS=new ArrayList<>();
   public static HashMap<String,Integer> Labels=new HashMap<>();
   public static int EAX=0;
   public static int EBX=1;
   public static int ECX=2;
   public static int ESI=3;
   public static int ESC=4;
   public static int EDX=5;

public  static void init(){
    for(int i=0;i<100;i++)
        memmory.add("0");
}



}

