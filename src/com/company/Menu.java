package com.company;

import java.util.Scanner;

public class Menu {
        private String porta;
        private int baudrate;
        private String fName = "test1.txt";
        private Send_T trama = new Send_T();
        private byte[] data;


    public Menu() throws Exception {
        Scanner aux = new Scanner(System.in);
        Scanner aux2 = new Scanner(System.in);
        System.out.println("Insira o valor de baudrate\n");
        this.baudrate = aux.nextInt();
        aux.reset();
        System.out.println("Insira a porta a que se quer ligar\n");
        this.porta = aux2.nextLine();
        aux2.reset();
        ComPort CP = new ComPort(porta,baudrate);



        System.out.println("Quer receber ou enviar um ficheiro?\n");
        System.out.println("1-Receber ficheiro 2-Enviar ficheiro\n");
        int chose = aux.nextInt();
        if (chose != 1 && chose != 2) {
            System.out.println("Insira apenas os números apresentados\n");
        }

        switch (chose) {
            case (1):
                WriteReceiver wR = new WriteReceiver();

                ReceivePacket r = new ReceivePacket(porta, baudrate);

                wR.getFw().writeData(r.getDataF());
                break;

            case (2):

                Packet eF = new Packet(porta, baudrate,fName);
                eF.sendPacket(0);
                break;
        }
    }
}
