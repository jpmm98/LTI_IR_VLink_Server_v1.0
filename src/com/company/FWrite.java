package com.company;

import java.io.*;

public class FWrite {

    private FileOutputStream oStream;
    private String fName;
    private long fSize;
    private byte[] crc;


    public FWrite (String fN) throws FileNotFoundException {
        this.fName = fN;

        File dir = new File(".\\DFiles\\");

        dir.mkdir();

        File f = new File(".\\DFiles\\" + this.fName);

        this.oStream = new FileOutputStream(f);
    }



    public void writeData(byte[] data2) throws IOException{

        try{
                    this.oStream.write(data2);

            } catch (IOException e) {
                e.printStackTrace();
            }

        this.oStream.close();
    }



    public long getfSize()      { return new File(this.fName).length(); }
    public String getfName()    { return fName; }

    public FileOutputStream getfile(){
        return this.oStream;
    }

}
