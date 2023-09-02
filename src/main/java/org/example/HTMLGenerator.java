package org.example;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

public class HTMLGenerator {
    private Writer writer;

    public HTMLGenerator(Writer writer) {
        this.writer = writer;
    }

    public void generate(List<Movie> movies) {
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
        writer.write("<title>Movie website</title>");
        writer.write("<nav class=\"navbar navbar-dark bg-primary\">");
        writer.write("<div class=\"container-fluid\">");
        writer.write("<span class=\"navbar-brand mb-1 h1\">Top Movies</span>");
        writer.write("</div>");
        writer.write("</nav>");
        writer.write("</head>");
        writer.write("<body class=\"bg-dark\">");
    }

    private void writeBody(List<Movie> movies) throws Exception {
        writer.write("<ul>");
        writer.write("<br>");
        writer.write("<div class=\"row\">");

        for (Movie movie : movies) {
            writer.write("<div class=\"col-sm-2 mb-1 mb-sm-0\">");
            writer.write("<div class=\"card\" style=\"width: 18rem;\">");
            writer.write("<img src=\"" + movie.getImage() + "\" class=\"card-img-top\" alt=\"" + movie.getTitle() + "\">");
            writer.write("<div class=\"card-body\">");

            writer.write("<h5 class=\"card-title\">" + movie.getTitle() + "</h5>");

            writer.write("<p class=\"card-text\">Rate: " + movie.getRating() + " - Year: " + movie.getYear() + "</p>");

            writer.write("</div>");
            writer.write("</div>");
            writer.write("<br>");
            writer.write("</div>");
        }
        writer.write("</div>");
        writer.write("</ul>");
    }

    private void writeFooter() throws Exception {
        writer.write("</body>");
        writer.write("</html>");
        writer.flush();
    }
}
