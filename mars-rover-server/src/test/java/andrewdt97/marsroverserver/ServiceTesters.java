package andrewdt97.marsroverserver;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import andrewdt97.marsroverserver.beans.PhotoList;
import andrewdt97.marsroverserver.beans.RoverList;
import andrewdt97.marsroverserver.services.AcceptanceTestService;
import andrewdt97.marsroverserver.services.DateService;
import andrewdt97.marsroverserver.services.PhotoService;
import andrewdt97.marsroverserver.services.RoverService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTesters {
    private final String CURIOSITY_NAME = "Curiosity";
    private final String FHAZ_CAMERA = "FHAZ";
    private final String FETCH_DATE = "2020-02-13"; 

    @Inject
    AcceptanceTestService acceptanceTestService;
    @Inject
    DateService dateService;
    @Inject
    PhotoService PhotoService;
    @Inject
    RoverService roverService;

    @Test
    public void testAcceptanceTest() {
        List<PhotoList> results = acceptanceTestService.preformAcceptanceTest();

        Assertions.assertEquals( 36, results.get( 0 ).getPhotos().size() );
        Assertions.assertEquals( 10, results.get( 1 ).getPhotos().size() );
        Assertions.assertEquals( 392, results.get( 2 ).getPhotos().size() );     
    }

    @Test
    public void testDateService() {
        // Expected results for each supported format
        Assertions.assertEquals( "2020-06-03", dateService.santizeDate( "06/03/20" ) ); // MM/dd/yy
        Assertions.assertEquals( "2020-06-03", dateService.santizeDate( "June 3, 2020" ) ); // MMM d, yyyy
        Assertions.assertEquals( "2020-06-03", dateService.santizeDate( "JUN-3-2020" ) ); // MMM-d-yyyy

        // Check invalid dates
        Assertions.assertThrows(NumberFormatException.class, () -> {
            dateService.santizeDate( "Luke, I am your father");
          });
        Assertions.assertThrows(NumberFormatException.class, () -> {
            dateService.santizeDate( "03.06.20");
        });
    }

    @Test
    public void testPhotoService() {
        // Check all photos
        PhotoList photos = PhotoService.fetchPhotoList( CURIOSITY_NAME, FETCH_DATE, "all" );

        Assertions.assertEquals(132, photos.getPhotos().size() );
        Assertions.assertEquals( CURIOSITY_NAME, photos.getPhotos().get( 0 ).getRover().getName() );
        Assertions.assertEquals( FETCH_DATE, photos.getPhotos().get( 0 ).getEarthDate() );

        // Check photos from one camera
        photos = PhotoService.fetchPhotoList( CURIOSITY_NAME, FETCH_DATE, FHAZ_CAMERA );

        Assertions.assertEquals(2, photos.getPhotos().size() );
        Assertions.assertEquals( CURIOSITY_NAME, photos.getPhotos().get( 0 ).getRover().getName() );
        Assertions.assertEquals( FHAZ_CAMERA, photos.getPhotos().get( 0 ).getCamera().getName() );
        Assertions.assertEquals( FETCH_DATE, photos.getPhotos().get( 0 ).getEarthDate() );

    }

    @Test
    public void testRoverService() {
        RoverList rovers = roverService.fetchRoverList();

        Assertions.assertNotNull( rovers );
        Assertions.assertNotEquals( 0 , rovers.getRovers().size() );
    }
}