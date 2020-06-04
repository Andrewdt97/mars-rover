package andrewdt97.marsroverserver.endpoints;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import andrewdt97.marsroverserver.beans.RoverList;
import andrewdt97.marsroverserver.services.RoverService;

/**
 * @author andrewdt97
 * API endpoint for rovers
 * 
 */
@CrossOrigin
@RestController
@RequestMapping( "api/v1/" )
public class RoversEndpoint {
    @Inject
    private RoverService roverService;

    /**
	 * @author andrewdt97
	 * api/v1/rovers
	 * 
	 * @return JSON information for all rovers
	 */
    @RequestMapping( value = "rovers", method = RequestMethod.GET )
    public RoverList getRoverList() {
        return roverService.fetchRoverList();
    }

}