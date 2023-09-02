package org.example;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

public class HTMLGenerator {
    private Writer writer;

    public HTMLGenerator(Writer writer) {
        this.writer = writer;
    }

    public void generate(List<Movie> movies){
        try {
            writeHeader();
            writeBody(movies);
            writeFooter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeHeader() throws Exception {
        writer.write("<!DOCTYPE html>");
        writer.write("<html lang=\"en\">");
        writer.write("<head>");
        writer.write("<link rel=\"stylesheet\" href=\"node_modules/bootstrap/dist/css/bootstrap.min.css\">");
        writer.write("<script src=\"node_modules/bootstrap/dist/js/bootstrap.bundle.min.js\"></script>\n");
        writer.write("<meta charset=\"UTF-8\">");
        writer.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
        writer.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        writer.write("<title>Movie List</title>");
        writer.write("</head>");
        writer.write("<body>");
    }

    private void writeBody(List<Movie> movies) throws Exception {
        writer.write("<h1>Movie List</h1>");
        writer.write("<ul>");
        for (Movie movie : movies) {
            writer.write("<li>");
            writer.write("<h2>" + movie.getTitle() + "</h2>");
            writer.write("<img src=\"" + movie.getImage() + "\" alt=\"" + movie.getTitle() + "\">");
            writer.write("</li>");
        }
        writer.write("</ul>");
    }

    private void writeFooter() throws Exception {
        writer.write("</body>");
        writer.write("</html>");
        writer.flush();
    }
}
