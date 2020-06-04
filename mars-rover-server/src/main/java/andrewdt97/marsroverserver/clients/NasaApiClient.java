package andrewdt97.marsroverserver.clients;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.logging.LoggingFeature.Verbosity;
import org.springframework.stereotype.Component;

import andrewdt97.marsroverserver.beans.KeyValuePair;
import andrewdt97.marsroverserver.beans.PhotoList;
import andrewdt97.marsroverserver.beans.RoverList;

/**
 * @author andrewdt97
 * Class for interfacing with NASA's mars rover API
 */
@Component
public class NasaApiClient {
    private static final String NASA_URI_BASE = "https://api.nasa.gov/mars-photos/api/v1";
	private static final String API_KEY_PARAM_NAME = "api_key";

	private final JacksonJsonProvider jacksonJsonProvider =
        new JacksonJaxbJsonProvider().configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false );

    private Client client = ClientBuilder.newClient( new ClientConfig( jacksonJsonProvider ) );

	/**
	 * @author andrewdt97
	 * Retrieves the API key from the NASA_API_KEY envrionment variable
	 * 
	 * @return the API key
	 */
    private String getApiKey() {
        try {
		    return System.getenv( "NASA_API_KEY" );
	    } catch(NullPointerException e) {
		    // logger.error( "NASA_API_KEY not found in enviornment variables" );
            throw e;
	    } catch(SecurityException e) {
		    // logger.error( "NasaApiClient does not have persmission to access envriornment variables" );
            throw e;
	    }
    }

	/**
	 * @author andrewdt97
	 * Fetches the list of photos matching the parameters
	 * 
	 * @param rover the rover to collect photos from
	 * @param queryParams a list of key value pairs with the query parameters
	 * 
	 * @return PhotoList of photos from given parameters
	 */
    public PhotoList fetchPhotoList( String rover, List<KeyValuePair> queryParams ) {
        final String API_KEY = getApiKey();

		StringBuilder callUri = new StringBuilder( NASA_URI_BASE )
			.append( "/rovers/" )
			.append( rover )
			.append( "/photos" );

		// Add all query parameters in quereyParams
		WebTarget target = client.target( callUri.toString() );
		for ( KeyValuePair kvp : queryParams ) {
			target = target.queryParam( kvp.getKey(), kvp.getValue() );
		}
		
		return target.queryParam( API_KEY_PARAM_NAME, API_KEY )
			.request( MediaType.APPLICATION_JSON ).get( PhotoList.class );
	}
	
	/**
	 * @author andrewdt97
	 * Fetches the rover information
	 * 
	 * @return RoverList of rover information
	 */
	public RoverList fetchRoverList() {
		final String API_KEY = getApiKey();
		
		StringBuilder callUri = new StringBuilder( NASA_URI_BASE )
			.append( "/rovers" );
		return client.target( callUri.toString() )
			.queryParam( API_KEY_PARAM_NAME, API_KEY )
			.request( MediaType.APPLICATION_JSON )
			.get( RoverList.class );
	}
}