package andrewdt97.marsroverserver.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import andrewdt97.marsroverserver.NasaApiClient;
import andrewdt97.marsroverserver.beans.KeyValuePair;
import andrewdt97.marsroverserver.beans.PhotoList;

/**
 * @author andrewdt97
 * Class for handling photo requests
 */
@Component
public class PhotoService {
    private static final String EARTH_DATE_PARAM_NAME = "earth_date";
    private static final String CAMERA_PARAM_NAME = "camera";

    @Inject
    DateService dateService;
    
    @Inject
    NasaApiClient apiClient;

    /**
     * @author: andrewdt97
     * Prepares call to NASA's api for rover photos
     * 
     * @param rover the rover to collect pictures from
     * @param date the date to collect pictures from
     * @param camera the rover camera to fetch pictures from
     * 
     * @return a PhotoList with the API call results
     */
    public PhotoList fetchPhotoList( String rover, String date, String camera ) {
        String sanitizedDate = dateService.santizeDate( date );
        
        List<KeyValuePair> queryParams = new ArrayList<KeyValuePair>();
        queryParams.add( new KeyValuePair( EARTH_DATE_PARAM_NAME, sanitizedDate ) );
        if (!camera.equals( "all" )) {
            queryParams.add( new KeyValuePair( CAMERA_PARAM_NAME, camera ) );
        }
       
        return apiClient.fetchPhotoList( rover, queryParams );
    }
}