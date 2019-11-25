package com.company;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Trama {

    private byte flag;
    private byte[] DataPacket;
    private ByteArrayOutputStream baos;

    public Trama(){
        this.flag = 126;
        this.baos = new ByteArrayOutputStream(1008);



    }
    // Junta o lido do buffer tudo num array DataPacket
    public byte[] getDataPacket() {
        this.DataPacket = baos.toByteArray();
        return DataPacket;
    }
    //Junta o byte de sequencia e o identificador do tipo de trama
    public void buildHead(byte type, byte sq) throws Exception{
        Header h = new Header(type);
        baos.write(h.getHeader(sq));
    }
    //Junta os dados do ficheiro á trama
    public void buildData(byte[] data)throws Exception{
        baos.write(data);
        this.DataPacket = this.baos.toByteArray();
        System.out.println("Data adicionado\n");
    }


    //So pode ser chamado após a montagem dos dados na trama
    public void buildTrailer() throws IOException {
        Trailer t = new Trailer(this.DataPacket);
        baos.write(t.getCrcRes());

        System.out.println("Trailer adicionado\n");
    }

    //Junta ambas as Flags
    public void buildFlag(){

        baos.write(this.flag);

    }


    public void resetOutStream(){
        this.baos.reset();
    }

}
