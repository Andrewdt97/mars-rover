package andrewdt97.marsroverserver.clients;

import java.io.InputStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.springframework.stereotype.Component;

/**
 * @author andrewdt97
 * Class for retrieving photos from a url
 */
@Component
public class PhotoClient {
    private final JacksonJsonProvider jacksonJsonProvider =
        new JacksonJaxbJsonProvider().configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false );

    private Client client = ClientBuilder.newClient( new ClientConfig( jacksonJsonProvider ) );
    
    /**
     * @author andrewdt97
     * 
     * @param url the url of the image to retrieve
     * @return an input stream of the file
     */
    public InputStream getPhoto( String url ) {
		return client.target( url )
				.request()
				.get( InputStream.class );
	}
}