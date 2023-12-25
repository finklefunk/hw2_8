package com.example.hw2_8;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TimeZone;

@WebServlet(name = "time", value = "/time")
public class TimeServlet extends HttpServlet {
    private String message;

    public void init() {
        message = ZonedDateTime
                .now( ZoneId.of( "UTC" ) )
                .format( DateTimeFormatter.ofPattern( "uuuu-MM-dd HH:mm:ss z" ) );
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String timezoneVar = request.getParameter("timezone");

        if( timezoneVar != null && !timezoneVar.equals("UTC")){
            //ZoneId timezoneId = ZoneId.of(timezoneVar);
            //Set<String> timezoneIds = Set.of(TimeZone.getAvailableIDs());

            //if( Set.of(TimeZone.getAvailableIDs()).contains(ZoneId.of(timezoneVar)) ){
            //String timezoneName = String.valueOf(TimeZone.getTimeZone(timezoneVar));}

            message = ZonedDateTime
                    .now( ZoneId.of( timezoneVar.replace(' ','+') ) )
                    .format( DateTimeFormatter.ofPattern( "uuuu-MM-dd HH:mm:ss z" ) );
        }
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}