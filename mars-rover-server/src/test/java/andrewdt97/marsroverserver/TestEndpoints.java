package andrewdt97.marsroverserver;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import andrewdt97.marsroverserver.endpoints.AcceptanceTestEndpoint;
import andrewdt97.marsroverserver.endpoints.PhotosEndpoint;
import andrewdt97.marsroverserver.endpoints.RoversEndpoint;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestEndpoints {

    @LocalServerPort
    private int port;

    @Inject
    private TestRestTemplate restTemplate;

    @Inject
    AcceptanceTestEndpoint acceptanceTestEndpoint;
    @Inject
    PhotosEndpoint photosEndpoint;
    @Inject
    RoversEndpoint roversEndpoint;

    @Test
    public void testEndpoints() {
        ResponseEntity<String> response = restTemplate.getForEntity( "http://localhost:" +  port + "/api/v1/acceptance_test", String.class );
        Assertions.assertEquals( HttpStatus.OK, response.getStatusCode() );
        
        response = restTemplate.getForEntity( "http://localhost:" +  port + 
            "/api/v1/curiosity/photos?earth_date=2020-02-14&camera=FHAZ", String.class );
        Assertions.assertEquals( HttpStatus.OK, response.getStatusCode() );
        
        response = restTemplate.getForEntity( "http://localhost:" +  port + "/api/v1/rovers", String.class );
		Assertions.assertEquals( HttpStatus.OK, response.getStatusCode() );
    }
}