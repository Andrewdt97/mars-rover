package andrewdt97.marsroverserver.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import andrewdt97.marsroverserver.beans.PhotoList;

/**
 * @author andrewdt97
 * Service to preform the acceptance test
 * 
 */
@Component
public class AcceptanceTestService {
    private final Logger logger = LoggerFactory.getLogger( AcceptanceTestService.class );
    private List<String> dateList = new ArrayList<String>();
    private String dateTextFile = "/acceptance_test_dates.txt";

    @Inject
    private DateService dateService;
    @Inject
    private PhotoService photoService;

    /**
     * @author andrewdt97
     * Preforms the acceptance test
     * 
     * @return PhotoList list, each entry is one date from resources/acceptance_test_dates/txt
     */
    public List<PhotoList> preformAcceptanceTest() {
        // Read dates from file
        try (InputStream inputStream = getClass().getResourceAsStream( dateTextFile );
                Stream<String> stream = new BufferedReader( new InputStreamReader( inputStream ) ).lines()) {

            stream.forEach(date -> {
                dateList.add( dateService.santizeDate( date ) );
            });
        } catch (Exception e) {
            logger.error( "Error reading file {}", dateTextFile, e.getStackTrace() );
        }

        // Fetch PhotoList for each date
        List<PhotoList> results = new ArrayList<PhotoList>( dateList.size() );
        for (String date : dateList) {
            results.add( photoService.fetchPhotoList( "curiosity", date, "all" ) );
        }

        return results;
    }
}