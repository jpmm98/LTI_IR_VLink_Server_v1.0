package com.company;

import java.util.Arrays;

public class CheckLoss {

    private int seq = 0;
    private byte sq = 0;


    public CheckLoss(){}

    public boolean checkCRC(byte[] dataF){

        try {
            byte[] Fcrc = Arrays.copyOfRange(dataF, dataF.length - 5, dataF.length - 1);
            Trailer tr = new Trailer(Arrays.copyOfRange(dataF,0,dataF.length-5));
            byte[] newCRC = tr.getCrcRes();

            return Arrays.equals(Fcrc,newCRC);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    public boolean checkSEQ(byte[] dataF){

        if (dataF[2] == this.sq){
            if (this.sq == 0){
                this.sq = 1;
            }else{ this.sq = 0;}

            return true;
        }else{
            return false;


        }





    }

}
