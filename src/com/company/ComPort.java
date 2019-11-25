package com.company;

import com.fazecast.jSerialComm.*;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import static com.company.FRead.DataSize;

public class ComPort {

    private String portN;
    private SerialPort sPort;
    private int baudrate;


    public ComPort(String nomePorta, int baudRate){
        this.portN = nomePorta;
        this.baudrate = baudRate;

        this.sPort = SerialPort.getCommPort(this.portN);
        this.sPort.setComPortParameters(this.baudrate,8, 1, 0);


    }

    public boolean portStatus(){return this.sPort.openPort();}

    public boolean send(byte[] packet, int size){
        if (this.sPort.isOpen()){
            sPort.writeBytes(packet,size);
            return true;
        }
        return false;
    }


    public byte[] receive() {


        this.sPort.openPort();

            try {
                    while (sPort.bytesAvailable() == 0) {
                        Thread.sleep(300);
                    }

                    //Packet.SizePacket
                    byte[] readBuffer = new byte[Packet.SizePacket];

                    sPort.readBytes(readBuffer, readBuffer.length);

                    for (byte aReadBuffer : readBuffer) {
                        System.out.print(aReadBuffer + " ");
                    }

                    return readBuffer;


            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;

    }

    public boolean receiveConf() {

        byte[] c = new byte[2];
        byte ack = 0;
        if (this.sPort.isOpen()){
            sPort.readBytes(c,1);
            return c[0] == ack;
        }
        return false;
    }

    public int getBytesAvailable(){
        return this.sPort.bytesAvailable();
    }


}
