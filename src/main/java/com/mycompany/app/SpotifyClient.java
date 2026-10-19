package com.mycompany.app;

import io.github.cdimascio.dotenv.Dotenv;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import com.neovisionaries.i18n.CountryCode;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import java.util.Arrays;
import java.util.List;

public class SpotifyClient {
  Dotenv envfile = Dotenv.load();
  String token = "";
  SpotifyApi spotifyApi = null;

  public SpotifyClient() {
    this.spotifyApi = new SpotifyApi.Builder()
        .setClientId(envfile.get("SPOTIFY_CLIENT_ID"))
        .setClientSecret(envfile.get("SPOTIFY_CLIENT_SECRET"))
        .build();
    authenticate();
  }

  private void authenticate() {
    try {
      ClientCredentialsRequest request = spotifyApi.clientCredentials().build();
      ClientCredentials credentials = request.execute();
      spotifyApi.setAccessToken(credentials.getAccessToken());
    } catch (Exception e) {
      throw new RuntimeException("Falha ao autenticar no Spotify: " + e.getMessage(), e);
    }
  }

  private List<Track> listarMusicasPorGenero(String genero) throws Exception {
    SearchTracksRequest request = spotifyApi
        .searchTracks("genre:\"" + genero + "\"")
        .limit(10) // 10 músicas
        .offset(0)
        .market(CountryCode.BR) // opcional
        .build();
    Paging<Track> resultado = request.execute();
    return Arrays.asList(resultado.getItems());
  }

  public void imprimirMusicas(String genero) {
    try {
      List<Track> lista = listarMusicasPorGenero(genero);

      if (lista.isEmpty()) {
        System.out.println("Nenhuma música encontrada para o gênero: " + genero);
        return;
      }

      for (Track t : lista) {
        System.out.println(t.getName() + " - " + t.getArtists()[0].getName());
      }
    } catch (Exception e) {
      System.err.println("Erro ao buscar musicas: " + e.getMessage());
    }
  }
}