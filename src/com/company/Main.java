package com.company;

public class Main {

    public static void main(String[] args) throws Exception {


      //Menu m = new Menu();
        //Menu nao esta funcional
        //Faltam verificação dos Acks/Nacks no outro programa


       // WriteReceiver wr = new WriteReceiver();
       // FWrite fw=new FWrite(wr.filename);

        System.out.println("GitHub change");

        FWrite fw = new FWrite("test2.txt");

        System.out.println("Esperando o ficheiro ser enviado...\n");


        ReceivePacket rp = new ReceivePacket("COM3", 9600);

          fw.writeData(rp.getDataF());


        System.out.println("Ficheiro recebido");

    }
}
