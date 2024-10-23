package org.SafeDrive;

import jakarta.ws.rs.core.UriBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import org.SafeDrive.Operacional.Menu;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

import static java.awt.SystemColor.menu;

/*Main class*/

public class Main {

    private static int getPort(int defaultPort) {
        //grab port from environment, otherwise fall back to default port 9998
        String httpPort = System.getProperty("jersey.test.port");
        if (null != httpPort) {
            try {
                return Integer.parseInt(httpPort);
            } catch (NumberFormatException e) {
            }
        }
        return defaultPort;
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(getPort(9998)).build();
    }

    public static final URI BASE_URI = getBaseURI();

    protected static HttpServer startServer() throws IOException {
        ResourceConfig resourceConfig = new PackagesResourceConfig("org.example");

        System.out.println("Starting grizzly2...");
        return GrizzlyServerFactory.createHttpServer(BASE_URI, resourceConfig);
    }


    public static Logger logger = (Logger) LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        // Grizzly 2 initialization
        HttpServer httpServer = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                        + "%sapplication.wadl\nHit enter to stop it...",
                BASE_URI));
        System.in.read();
        httpServer.stop();

        Menu menu = new Menu();

        try{
            logger.info(">> Sistema de AudioBook do Spotify Iniciado <<");

        } catch (Exception e) {
            e.printStackTrace();
            logger.fatal("Erro fatal: "+ e.getMessage() + "-" + Arrays.toString(e.getStackTrace()));
        }


    }
}

