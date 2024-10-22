package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        System.out.println("Il client è partito!");
        Socket s = new Socket("localhost", 3000);
        System.out.println("Il client si è collegato");
        Scanner scanner = new Scanner(System.in);

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        
        String stringaRicevuta;
        String stringaInviata;
        boolean acceso = true;

        System.out.println("Prova ad indovinare un numero randomico\n Inserisci numero: ");

        do{
            out.writeBytes(scanner.nextLine() + "\n");
            stringaRicevuta = in.readLine();

            switch (stringaRicevuta) {
                case "<":
                    System.out.println("Numero troppo piccolo\n Ritenta:");
                    break;
                
                case ">":
                    System.out.println("Numero troppo grande\n Ritenta:");
                    break;

                case "=":
                    System.out.println("Valore corretto");
                    System.out.println("Ci hai messo: " + in.readLine() + " tentativi");
                    System.out.println("Vuoi riprovare?\n Digita 'y' o 'n': ");
                    stringaInviata = scanner.nextLine();
                    out.writeBytes(stringaInviata + "\n");

                    if(stringaInviata.equals("y")){
                        System.out.println("Inserisci numero: ");
                    } else{
                        acceso = false;
                    }
                    break;

                case "!":
                System.out.println("Valore inserito errato\n Ritenta:");
                break;
            }
        }while(acceso);
    }
}