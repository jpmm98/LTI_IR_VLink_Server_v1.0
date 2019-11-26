package com.company;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import static com.company.Packet.SizePacket;

class ReceivePacket {
    private byte[] DataToW;

    ReceivePacket(String PortName, int Baudrate) throws Exception {
        ComPort cP = new ComPort(PortName, Baudrate);
        CheckLoss cl = new CheckLoss();
        byte[] dataF;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        do{

            dataF = cP.receive();
            baos.write(Arrays.copyOfRange(dataF,3, dataF.length-5));
            try {

                byte x = dataF[2];

                Send_T st = new Send_T();
                if (cl.checkCRC(dataF)) {
                    if (cl.checkSEQ(dataF)) {
                        System.out.println("\nBem recebido\n seqN: " + dataF[2]);
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
        }while(dataF.length == SizePacket);

        this.DataToW = baos.toByteArray();
    }





      byte[] getDataF() {
        return this.DataToW;
    }
}

