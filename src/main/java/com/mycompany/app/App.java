package com.mycompany.app;

import java.util.Scanner;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * 
 * */
public class App {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    System.out.println("! Bem vindo ao DJ inteligente de clima !");
    System.out.println("Com base no tempo da sua região, vou te recomendar uma playlist do Spotify");
    System.out.print("Diga em qual cidade você está: ");
    String cidade = scan.nextLine().trim();

    Dotenv envfile = Dotenv.load();
    String weatherApiKey = envfile.get("WEATHER_API_KEY");

    Clima clima = new Clima(cidade, weatherApiKey);
    System.out.println(clima.getSummarizedInfo());

    scan.close();
  }
}
