package com.mycompany.app;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ClienteApp {
  private static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    String host = "localhost";
    int porta = 9806;

    System.out.println("! Bem vindo ao DJ inteligente de clima !");
    System.out.println("Com base no tempo da sua região, vou te recomendar uma playlist do Spotify");
    System.out.print("Diga em qual cidade você está: ");
    String cidade = scan.nextLine().trim();

    try (Socket socket = new Socket(host, porta);
         PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

      out.println(cidade);

      String clima = in.readLine();
      if (clima == null || clima.startsWith("ERRO:")) {
        System.out.println(clima == null ? "Sem resposta do servidor." : clima);
        return;
      }
      String genero = in.readLine();

      System.out.println("Com base no tempo da sua loc. atual: " + clima);
      System.out.println("Recomendamos o seguinte gênero: " + genero + '\n');

      String linha;
      while ((linha = in.readLine()) != null) {
        System.out.println(linha);
      }
    }
  }
}