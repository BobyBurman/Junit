import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

		// Convert Java object to JSON string using Jackson ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
		String jsonRequestBody = objectMapper.writeValueAsString(requestBody);
		
		 // Create HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Create HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("http://example.com/api"))
            .header("Content-Type", "application/json")
            .header("Custom-Header-1", "HeaderValue1")
            .header("Custom-Header-2", "HeaderValue2")
            .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
            .build();
			
			
		 // Send request and get raw response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); //HttpResponse.BodyHandlers.ofByteArray()
		
		// Send request and get raw response as byte array
        HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

		
		<dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.15.2</version> <!-- Use the latest version if available -->
    </dependency>
