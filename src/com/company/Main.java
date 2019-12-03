package com.company;

public class Main {

    public static void main(String[] args) throws Exception {


      //Menu m = new Menu();
        //Menu nao esta funcional
        //Faltam verificação dos Acks/Nacks no outro programa


       // WriteReceiver wr = new WriteReceiver();
       // FWrite fw=new FWrite(wr.filename);

        System.out.println("GitHub change");

        FWrite fw = new FWrite("tracy-chap-test.txt");




        ReceivePacket rp = new ReceivePacket("COM2", 9600);

          fw.writeData(rp.getDataF());


        System.out.println("Ficheiro recebido");

    }
}
