package org.example;

import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class imdbAPIClient {

    static void get250Movies() {
        try {
            //criar um httpclient
            HttpClient client = HttpClient.newHttpClient();

            //definir o url para o request
            URI uri = new URI("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json");

            //criar um httprequest
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(uri)
                    .GET() //especifica que é um request do tipo GET
                    .build();

            //enviar o request e obter a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //imprimir a resposta
            System.out.println(response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void getTitleURLImage250Movies() {
        String returnJSON;

        //criar um httpclient
        HttpClient client = HttpClient.newHttpClient();

        //criar um httprequest
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json"))
                .GET() //especifica que é um request do tipo GET
                .build();

        //enviar o request e obter a resposta
        returnJSON = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        JSONArray jsonMovies = new JSONObject(returnJSON).getJSONArray("items");

        List<Movie> movies = new ArrayList<>();

        for (Object obj : jsonMovies) {
            JSONObject movieJson = (JSONObject) obj;
            Movie movie = new Movie(
                    movieJson.getString("title"),
                    movieJson.getString("image"),
                    movieJson.getDouble("imDbRating"),
                    movieJson.getInt("year"));

            movies.add(movie);
        }

        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    static void getHTML() {
        String returnJSON;

        //criar um httpclient
        HttpClient client = HttpClient.newHttpClient();

        //criar um httprequest
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json"))
                .GET() //especifica que é um request do tipo GET
                .build();

        //enviar o request e obter a resposta
        returnJSON = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        JSONArray jsonMovies = new JSONObject(returnJSON).getJSONArray("items");

        List<Movie> movies = new ArrayList<>();

        for (Object obj : jsonMovies) {
            JSONObject movieJson = (JSONObject) obj;
            Movie movie = new Movie(
                    movieJson.getString("title"),
                    movieJson.getString("image"),
                    movieJson.getDouble("imDbRating"),
                    movieJson.getInt("year"));

            movies.add(movie);
        }

        try (PrintWriter writer = new PrintWriter("output.html")) {
            HTMLGenerator generator = new HTMLGenerator(writer);
            generator.generate(movies);  // supondo que moviesList é sua lista de Movie
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

