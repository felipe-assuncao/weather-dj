package com.mycompany.app;

import java.util.Scanner;

/**
 * 
 * */
public class App {
  private static Scanner scan = new Scanner(System.in);
  public static void main(String[] args) {
    System.out.println("! Bem vindo ao DJ inteligente de clima !");
    System.out.println("Com base no tempo da sua região, vou te recomendar uma playlist do Spotify");
    System.out.print("Diga em qual cidade você está: ");
    String cidade = scan.nextLine().trim();

    Clima clima = new Clima(cidade);
    String genero = Genero.getGenre(clima.getWeatherID(), clima.getTemperatura());
    System.out.println("Com base no tempo da sua loc. atual: " + clima.getWeatherName());
    System.out.println("Recomendamos o seguinte gênero: " + genero + '\n');

    SpotifyClient spotify = new SpotifyClient();
    spotify.imprimirMusicas(genero);
  }
}