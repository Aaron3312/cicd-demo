package com.cicdemo;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    
    @Test
    public void testDummy() {
        // Simple test that always passes
        assertTrue(true, "This test should always pass");
    }
    
    @Test
    public void testServerResponseCode() throws Exception {
        // Start the server in a separate thread
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                Main.main(new String[]{});
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
        // Give the server a moment to start
        TimeUnit.SECONDS.sleep(1);
        
        try {
            // Test connection
            URL url = new URL("http://localhost:8080/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            // Check response code
            assertEquals(200, connection.getResponseCode());
            
        } finally {
            // Shutdown the executor (Note: This doesn't actually stop the server)
            executor.shutdownNow();
        }
    }
}