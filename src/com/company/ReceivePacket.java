package com.company;

import java.util.Arrays;

public class ReceivePacket {
    private ComPort cP;
    private WriteReceiver WR;
    private Send_T st = new Send_T();
    private byte[] dataF;

    public ReceivePacket(String PortName, int Baudrate) throws Exception {
        cP = new ComPort(PortName,Baudrate);
        CheckLoss cl = new CheckLoss();


            this.dataF = cP.receive();


            try {

                assert this.dataF != null;
                byte x = this.dataF[2];

                if (cl.checkCRC(this.dataF)) {
                    if (cl.checkSEQ(this.dataF)) {
                        System.out.println("\nBem recebido\n" + this.dataF[2]);
                        cP.send(st.ackT(x), st.ackT(x).length);
                    } else {
                        System.out.println("Numero de sequencia errado");
                    }
                } else {

                    System.out.println("\nMal Recebido\n");
                    cP.send(st.nackT(x), st.nackT(x).length);


                }

            } catch (Exception e) {
                e.printStackTrace();
            }
    }





    public byte[] getDataF() {
        return Arrays.copyOfRange(dataF,3, this.dataF.length-5);
    }
}

