package andrewdt97.marsroverserver.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import andrewdt97.marsroverserver.beans.KeyValuePair;
import andrewdt97.marsroverserver.beans.PhotoList;
import andrewdt97.marsroverserver.clients.NasaApiClient;
import andrewdt97.marsroverserver.clients.PhotoClient;

/**
 * @author andrewdt97
 * Class for handling photo requests
 */
@Component
public class PhotoService {
    private static final String EARTH_DATE_PARAM_NAME = "earth_date";
    private static final String CAMERA_PARAM_NAME = "camera";
    private static final String IMAGE_CACHE_PATH = "/tmp/";
    
    private final Logger logger = LoggerFactory.getLogger( DateService.class );
    @Inject
    DateService dateService;
    
    @Inject
    NasaApiClient apiClient;

    @Inject
    PhotoClient photoClient;

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

    /**
     * @author andrewt97
     * Configures call to retrieve image
     * 
     * @param imgSrc the image source as returned by the NASA API
     * @return image file
     * @throws IOException
     */
    public File getPhoto( String imgSrc ) throws IOException {
        imgSrc = insureHttps( imgSrc );
        String fileName = imgSrc.substring( imgSrc.lastIndexOf( '/' ) + 1 );
        StringBuilder filePath = new StringBuilder( IMAGE_CACHE_PATH ).append( fileName );

        File img;

        if ( Files.exists( Paths.get( filePath.toString() ) ) ) {
            img = Paths.get( filePath.toString() ).toFile();
        } else {
            InputStream fileStream = photoClient.getPhoto( imgSrc );
            File targetFile = new File( filePath.toString() );
            Files.copy( fileStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING );
            IOUtils.closeQuietly( fileStream );

            img = targetFile;
        }

        return img;
    }

    /**
     * @author andrewdt97
     * The NASA API returns the image source with normal HTTP. However this will cause an error with the client
     * as NASA's servers will redirect the request to HTTPS. This should not be a problem, but I could not
     * identify the problem, hence this workaround.
     * 
     * @param url the url of a given photo
     * @return the url with
     */
    private String insureHttps( String url ) {
        if (url.charAt(4) != 's') {
            int len = url.length();
            char[] newUrlArr = new char[len + 1];
            url.getChars( 0, 4, newUrlArr, 0 );
            newUrlArr[4] = 's';
            url.getChars( 4, len, newUrlArr, 5 );
            return new String( newUrlArr );
        }
        return url;
    }
}