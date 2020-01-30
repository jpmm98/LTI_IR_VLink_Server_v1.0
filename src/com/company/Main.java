package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws Exception {


      //Menu m = new Menu();
        //Menu nao esta funcional
        //Faltam verificação dos Acks/Nacks no outro programa

        Path path = Paths.get("\\DFiles\\");

        try(Stream<Path> subPath = Files.walk(path)) {
            subPath.forEach(System.out::println);
        } catch (IOException e){
            e.printStackTrace();
        }


        while (true) {
            WriteReceiver wr = new WriteReceiver();
            FWrite fw = new FWrite(wr.filename);



            // FWrite fw = new FWrite("led_1.jfif");


            ReceivePacket rp = new ReceivePacket("COM5", 1500);

            fw.writeData(rp.getDataF());


            System.out.println("Ficheiro recebido");
            System.out.println("Pretende sair ?");
            Scanner sc = new Scanner(System.in);
            String res = sc.nextLine();

            if(res == "y" || res == "yes" ){
                System.exit(0);
            }
        }
    }
}
