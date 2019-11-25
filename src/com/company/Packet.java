package com.company;

public class Packet {

    public static final int SizePacket = 1008;

    private ComPort cp;
    private byte sq;
    FRead fr;


    public Packet(String PortName, int BaudBRate, String FileName) throws Exception {

        this.cp = new ComPort(PortName, BaudBRate);
        fr = new FRead(FileName);



    }

    public void sendPacket(int type) throws Exception {

        switch (type) {

        case 0: {
                while (!fr.endOfFile()) {
                    try {

                        Send_T sendTrama = new Send_T();
                        byte[] pack = sendTrama.dataT(fr.getData(), this.sq);
                        this.cp.send(pack, pack.length);

                        if (sq == 0) {
                            this.sq = 1;
                        } else {
                            this.sq = 0;
                        }

                    } catch (Exception e) {
                        System.out.println("Erro no envio!");
                        e.printStackTrace();
                    }


                }

            }

        case 1: {
            while (!fr.endOfFile()) {
                try {

                    Send_T sendTrama = new Send_T();
                    byte[] pack = sendTrama.ackT(this.sq);
                    this.cp.send(pack, pack.length);

                    if (sq == 0) {
                        this.sq = 1;
                    } else {
                        this.sq = 0;
                    }

                } catch (Exception e) {
                    System.out.println("Erro no envio!");
                    e.printStackTrace();
                }
            }
        }


        case 2: {
                    while (!fr.endOfFile()) {
                        try {

                            Send_T sendTrama = new Send_T();
                            byte[] pack = sendTrama.ackT(this.sq);
                            this.cp.send(pack, pack.length);

                            if (sq == 0) {
                                this.sq = 1;
                            } else {
                                this.sq = 0;
                            }

                        } catch (Exception e) {
                            System.out.println("Erro no envio!");
                            e.printStackTrace();
                        }


                    }
                }
            }

        }

}
