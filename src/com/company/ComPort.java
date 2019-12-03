package com.company;

import com.fazecast.jSerialComm.*;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import static com.company.FRead.DataSize;

public class ComPort {


    private String portN;
    private SerialPort sPort;
    private int baudrate;


    ComPort(String nomePorta, int baudRate){



        this.portN = nomePorta;
        this.baudrate = baudRate;

        this.sPort = SerialPort.getCommPort(this.portN);
       // this.sPort.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);
        this.sPort.setComPortParameters(this.baudrate,8, 1, SerialPort.NO_PARITY);
        this.reconect();

    }

    public boolean portStatus(){return this.sPort.openPort();}

    boolean send(byte[] packet, int size){
        if (this.sPort.isOpen()){
            sPort.writeBytes(packet,size);
            return true;
        }
        return false;
    }


    byte[] receive() {
        System.out.println("\nReceiving File...");

            try {
                    while (sPort.bytesAvailable() == 0) {
                        Thread.sleep(3000);
                    }

                        System.out.println("Bytes na trama: "+sPort.bytesAvailable());

                        byte[] readBuffer = new byte[this.sPort.bytesAvailable()];
                        sPort.readBytes(readBuffer, readBuffer.length);

                        for (byte aReadBuffer : readBuffer) {
                            System.out.print((aReadBuffer & 0xFF) + " ");
                        }

                        return readBuffer;




            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
                System.out.println("Reconnecting");
                this.reconect();

                this.receive();
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

    public void reconect(){

        int[] timer = new int[100];
        System.out.println("Trying to open port...");

        for (int t: timer) {
            while(!this.sPort.isOpen()){

                try{
                    this.sPort.openPort();
                    System.out.print(".");
                    Thread.sleep(1000);
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

    public int getBytesAvailable(){
        return this.sPort.bytesAvailable();
    }


}
