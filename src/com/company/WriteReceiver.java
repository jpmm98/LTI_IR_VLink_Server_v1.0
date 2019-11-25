package com.company;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class WriteReceiver {
    String filename;
    FWrite fw;

    public WriteReceiver() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o nome para o novo ficheiro\n");
        filename = scan.nextLine();


    }

    public FWrite getFw() {
        return fw;
    }


}
