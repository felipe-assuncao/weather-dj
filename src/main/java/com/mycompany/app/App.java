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
    System.out.println(clima.getSummarizedInfo());
    System.out.println(clima.getWeatherID());
    System.out.println(clima.getWeatherName());
    System.out.println(clima.getWeatherDescription());
    System.out.println(clima.getWeatherEnum());
  }
}
