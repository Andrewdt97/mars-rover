package andrewdt97.marsroverserver.endpoints;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import andrewdt97.marsroverserver.beans.PhotoList;
import andrewdt97.marsroverserver.services.AcceptanceTestService;


/**
 * @author andrewdt97
 * API endpoint for the acceptance test
 * 
 */
@CrossOrigin
@RestController
@RequestMapping( "api/v1/" )
public class AcceptanceTestEndpoint {

    @Inject
    AcceptanceTestService acceptanceTestService;
    
/**
 * @author andrewdt97
 * api/v1/acceptance_test
 * @return JSON for all photos from the dates outlines in resources/acceptance_test_dates.txt from the curiosity rover
 */
    @RequestMapping( value = "acceptance_test", method = RequestMethod.GET )
    public List<PhotoList> preformAcceptanceTest() {
        return acceptanceTestService.preformAcceptanceTest();
    }
}