package andrewdt97.marsroverserver.endpoints;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import andrewdt97.marsroverserver.beans.PhotoList;
import andrewdt97.marsroverserver.services.PhotoService;

/**
 * @author andrewdt97
 * API endpoint for photos
 * 
 */
@CrossOrigin
@RestController
@RequestMapping( "api/v1/" )
public class PhotosEndpoint {
	
	@Inject
	private PhotoService photoService;
	
	/**
	 * @author andrewdt97
	 * api/v1/<rover>/photos
	 * 
	 * @param rover			the rover to retrieve images from
	 * @param earth_date	the day to retrieve images from
	 * @param camera		the camera to retrieve images from. See official API docs for availible cameras. "all" for all cameras.
	 * 
	 * @return JSON for all photos that fit the parameters
	 */
	@RequestMapping( value = "{rover}/photos", method = RequestMethod.GET )
	public PhotoList getPhotoList( @PathVariable String rover,
		@RequestParam( "earth_date" ) String earthDate, 
		@RequestParam String camera) {
		return photoService.fetchPhotoList( rover, earthDate, camera );
	}

}
