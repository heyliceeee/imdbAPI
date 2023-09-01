package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class imdbAPI {

    static void get250Movies(){
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

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
