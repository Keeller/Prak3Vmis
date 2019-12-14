package com.company;

public class CommandMeneger {
    public static int IP=0;
   public static boolean G=false;

    public static void xor(int result,int op1,int op2){
        int res=Integer.parseInt(MemmoryManager.memmory.get(op1))^Integer.parseInt(MemmoryManager.memmory.get(op2));
        MemmoryManager.memmory.set(result,Integer.toString(res,10));
    }
    public static void init(){
        MemmoryManager.init();
    }
    public static void add(int result,int op1,int op2){
        int res=Integer.parseInt(MemmoryManager.memmory.get(op1))+Integer.parseInt(MemmoryManager.memmory.get(op2));
        MemmoryManager.memmory.set(result,Integer.toString(res,10));

    }
    public static String getCommand(){
        return  MemmoryManager.CS.get(CommandMeneger.IP);
    }
    public static int parseAddr(String addr){
        if(addr.contains("[")&&addr.contains("]")){
            String s1=addr.substring(1,addr.length()-1);
            int a= parseAddr(s1);
            return Integer.parseInt(MemmoryManager.memmory.get(a));
        }
        else if(addr.contains("%")){
            String s1=addr.substring(1,addr.length());
            MemmoryManager.memmory.set(MemmoryManager.ESC,s1);
            return MemmoryManager.ESC;
        }
        else if(addr.equalsIgnoreCase("EAX"))
            return MemmoryManager.EAX;
        else if(addr.equalsIgnoreCase("EBX"))
            return MemmoryManager.EBX;
        else if(addr.equalsIgnoreCase("ECX"))
            return MemmoryManager.ECX;
        else if(addr.equalsIgnoreCase("ESI"))
            return MemmoryManager.ESI;
        else if(addr.equalsIgnoreCase("EDX"))
            return MemmoryManager.EDX;
        else
            return Integer.parseInt(addr);
    }
    public static int getLabel(String label){
        return MemmoryManager.Labels.get(label);
    }
    public static void translateCommand(){
        String s=getCommand();
        while (!s.equalsIgnoreCase("end")) {

            String[] form = s.split("\\s");
            if (form[0].equalsIgnoreCase("MOV")) {
                int op1 = parseAddr(form[1]);
                int op2 = parseAddr(form[2]);
                mov(op1, op2);

            } else if (form[0].equalsIgnoreCase("ADD")) {
                int res = parseAddr(form[1]);
                int op1 = parseAddr(form[2]);
                int op2 = parseAddr(form[3]);
                add(res, op1, op2);

            }else if (form[0].equalsIgnoreCase("XOR")) {
                int res = parseAddr(form[1]);
                int op1 = parseAddr(form[2]);
                int op2 = parseAddr(form[3]);
                xor(res, op1, op2);

            }
            else if (form[0].equalsIgnoreCase("SUB")) {
                int res = parseAddr(form[1]);
                int op1 = parseAddr(form[2]);
                int op2 = parseAddr(form[3]);
                sub(res, op1, op2);

            } else if (form[0].equalsIgnoreCase("INC")) {
                int op1 = parseAddr(form[1]);
                inc(op1);

            } else if (form[0].equalsIgnoreCase("DEC")) {
                int op1 = parseAddr(form[1]);
                dec(op1);

            } else if (form[0].equalsIgnoreCase("SHL")) {
                int op1 = parseAddr(form[1]);
                int op2 = parseAddr(form[2]);
                shl(op1, op2);

            } else if (form[0].equalsIgnoreCase("CMP")) {
                int op1 = parseAddr(form[1]);
                int op2 = parseAddr(form[2]);
                cmp(op1, op2);

            } else if (form[0].equalsIgnoreCase("LOOP")) {
                int op1 = getLabel(form[1]);
                loop(op1);

            } else if (form[0].equalsIgnoreCase("JMP")) {
                int op1 = getLabel(form[1]);
                jmp(op1);

            } else if (form[0].equalsIgnoreCase("JG")) {
                int op1 = getLabel(form[1]);
                jg(op1);

            }
            IP++;
            s=getCommand();
        }
    }

    public static void sub(int result,int op1,int op2){
        int res=Integer.parseInt(MemmoryManager.memmory.get(op1))-Integer.parseInt(MemmoryManager.memmory.get(op2));
        MemmoryManager.memmory.set(result,Integer.toString(res,10));

    }

    public static void inc(int op1){
        int res=Integer.parseInt(MemmoryManager.memmory.get(op1));
        res++;
        MemmoryManager.memmory.set(op1,Integer.toString(res,10));
    }

    public static void dec(int op1){
        int res=Integer.parseInt(MemmoryManager.memmory.get(op1));
        res--;
        MemmoryManager.memmory.set(op1,Integer.toString(res,10));
    }

    public static void shl(int op1,int arg){
        int res=Integer.parseInt(MemmoryManager.memmory.get(op1));
        res>>=arg;
        MemmoryManager.memmory.set(op1,Integer.toString(res,10));
    }

    public static void mov(int op1,int op2){
        int res1=Integer.parseInt(MemmoryManager.memmory.get(op2));
        MemmoryManager.memmory.set(op1,Integer.toString(res1,10));
    }

    public static void cmp(int op1,int op2){
        int res=Integer.parseInt(MemmoryManager.memmory.get(op1));
        int res1=Integer.parseInt(MemmoryManager.memmory.get(op2));
        if(res>res1)
            CommandMeneger.G=true;
        else
            CommandMeneger.G=false;
    }
    public static void jmp(int op1){
        CommandMeneger.IP=op1;
    }

    public static void jg(int op1){
        if(CommandMeneger.G) {
            jmp(op1);
            CommandMeneger.G = false;
        }
    }

    public static void loop(int op1){
        int res=Integer.parseInt(MemmoryManager.memmory.get(MemmoryManager.ECX));
        if(res!=0) {
            dec(MemmoryManager.ECX);
            jmp(op1);
        }
    }

}
