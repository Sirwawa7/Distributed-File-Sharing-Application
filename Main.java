import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final String BASE_PATH = "C:/Users/Ndosi/Desktop/semester8/Distributed/project/Project/FileSharing/public";
    private static UserAuthentication userAuth = new UserAuthentication();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new RootHandler());
        server.createContext("/login", new LoginHandler());
        server.createContext("/upload", new UploadHandler());
        server.createContext("/download", new DownloadHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        logger.info("Server started on port 8000");
    }

    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            logger.info("Received request for path: " + path + " with method: " + exchange.getRequestMethod());
            if (path.equals("/") || path.equals("/login")) {
                path = "/index.html";
            }
            Path filePath = Paths.get(BASE_PATH + path);
            logger.info("Resolved file path: " + filePath.toString());
            if (Files.exists(filePath)) {
                byte[] response = Files.readAllBytes(filePath);
                exchange.sendResponseHeaders(200, response.length);
                OutputStream os = exchange.getResponseBody();
                os.write(response);
                os.close();
                logger.info("Served file: " + filePath.toString());
            } else {
                String error = "404 (Not Found)\n";
                exchange.sendResponseHeaders(404, error.length());
                OutputStream os = exchange.getResponseBody();
                os.write(error.getBytes());
                os.close();
                logger.warning("File not found: " + filePath.toString());
            }
        }
    }

    static class LoginHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            logger.info("Handling login request with method: " + exchange.getRequestMethod());
            if ("POST".equals(exchange.getRequestMethod())) {
                InputStream inputStream = exchange.getRequestBody();
                String requestBody = new String(inputStream.readAllBytes());
                logger.info("Received request body: " + requestBody);

                String[] credentials = requestBody.split("&");
                if (credentials.length < 2) {
                    String response = "Invalid request format";
                    exchange.sendResponseHeaders(400, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                    logger.warning("Invalid request format");
                    return;
                }

                String username = credentials[0].split("=")[1];
                String password = credentials[1].split("=")[1];
                logger.info("Username: " + username + ", Password: " + password);

                boolean isAuthenticated = userAuth.authenticate(username, password);
                String response = isAuthenticated ? "Login successful" : "Invalid credentials";
                logger.info("Authentication result: " + response);

                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                String response = "Method not allowed for login request";
                exchange.sendResponseHeaders(405, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                logger.warning("Method not allowed for login request");
            }
        }
    }

    static class UploadHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            logger.info("Handling file upload request with method: " + exchange.getRequestMethod());
            if ("POST".equals(exchange.getRequestMethod())) {
                String query = exchange.getRequestURI().getQuery();
                String fileName = query.split("=")[1];
                InputStream inputStream = exchange.getRequestBody();
                Path filePath = Paths.get(BASE_PATH + "/uploads/" + fileName);

                Files.copy(inputStream, filePath);
                String response = "File uploaded successfully";
                logger.info("File uploaded: " + filePath.toString());

                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                String response = "Method not allowed for file upload request";
                exchange.sendResponseHeaders(405, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                logger.warning("Method not allowed for file upload request");
            }
        }
    }

    static class DownloadHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            logger.info("Handling file download request with method: " + exchange.getRequestMethod());
            if ("GET".equals(exchange.getRequestMethod())) {
                String query = exchange.getRequestURI().getQuery();
                String fileName = query.split("=")[1];
                Path filePath = Paths.get(BASE_PATH + "/uploads/" + fileName);

                if (Files.exists(filePath)) {
                    byte[] fileBytes = Files.readAllBytes(filePath);
                    exchange.sendResponseHeaders(200, fileBytes.length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(fileBytes);
                    os.close();
                    logger.info("File downloaded: " + filePath.toString());
                } else {
                    String error = "404 (Not Found)\n";
                    exchange.sendResponseHeaders(404, error.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(error.getBytes());
                    os.close();
                    logger.warning("File not found: " + filePath.toString());
                }
            } else {
                String response = "Method not allowed for file download request";
                exchange.sendResponseHeaders(405, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                logger.warning("Method not allowed for file download request");
            }
        }
    }
}
