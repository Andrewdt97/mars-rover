package andrewdt97.marsroverserver;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.springframework.boot.test.context.SpringBootTest;

import andrewdt97.marsroverserver.beans.KeyValuePair;
import andrewdt97.marsroverserver.beans.PhotoList;
import andrewdt97.marsroverserver.beans.RoverList;
import andrewdt97.marsroverserver.clients.NasaApiClient;

@SpringBootTest
public class NasaApiClientTester {
    private final String ROVER_NAME = "curiosity";
    private final String EARTH_DATE_PARAM_NAME = "earth_date";
    private final String EARTH_DATE_PARAM_VALUE = "2018-6-3";

    @Inject
    private NasaApiClient apiClient;

    @Test
    public void testFetchPhotoList() {
        List<KeyValuePair> queryParams = new ArrayList<>();
        queryParams.add(new KeyValuePair( EARTH_DATE_PARAM_NAME, EARTH_DATE_PARAM_VALUE) );
        
        PhotoList photoList = apiClient.fetchPhotoList( ROVER_NAME,  queryParams);

        Assertions.assertEquals( 74, photoList.getPhotos().size() );
        Assertions.assertEquals( "660558", photoList.getPhotos().get( 0 ).getId() );
        Assertions.assertEquals( "660631", photoList.getPhotos().get( 73 ).getId() );
    }

    @Test
    public void testFetchRoverList() {
        RoverList roverList = apiClient.fetchRoverList();

        Assertions.assertEquals( 3, roverList.getRovers().size() );
    }
}