package com.mycompany.app;

import java.io.*;
import java.net.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Servidor {
  private static final int PORTA = 9806;

  public static void main(String[] args) throws IOException {
    try (ServerSocket server = new ServerSocket(PORTA)) {
      System.out.println("Servidor ouvindo na porta " + PORTA);
      while (true) {
        Socket socket = server.accept(); // bloqueia até chegar um cliente
        atender(socket);                 // atende e só depois volta ao accept()
      }
    }
  }

  private static void atender(Socket socket) {
    try (socket;
         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
         PrintStream out = new PrintStream(socket.getOutputStream(), true, UTF_8)) {

      String cidade = in.readLine();

      try {
        Clima clima = new Clima(cidade);
        String genero = Genero.getGenre(clima.getWeatherID());
        out.println("Com base no tempo da sua loc. atual: " + clima.getWeatherName());
        out.println("Recomendamos o seguinte gênero: " + genero + '\n');

        SpotifyClient spotify = new SpotifyClient();
        spotify.imprimirMusicas(genero, out, out);
      } catch (Exception e) {
        out.println("Erro: " + e);
      }
    } catch (IOException e) {
      System.err.println("Falha na conexão: " + e.getMessage());
    }
  }
}