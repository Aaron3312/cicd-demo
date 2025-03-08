import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    private ExecutorService executorService;
    private Process serverProcess;

    @BeforeEach
    public void setUp() throws IOException, InterruptedException {
        // Start the server in a separate process
        serverProcess = new ProcessBuilder("java", "Main.java")
                .start();
        
        // Give the server some time to start
        Thread.sleep(2000);
    }

    @AfterEach
    public void tearDown() {
        if (serverProcess != null) {
            serverProcess.destroy();
        }
    }

    @Test
    public void testAppIsWorking() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("Hello World!"));
    }
}