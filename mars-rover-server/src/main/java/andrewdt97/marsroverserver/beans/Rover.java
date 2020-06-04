package andrewdt97.marsroverserver.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jlowery
 * @author andrewdt97
 */
public class Rover {
	
	public String id;
	public String name;
	@JsonProperty( "landing_date" )
	public String landingDate;
	@JsonProperty( "max_date" )
	public String maxDate;
	public List<Camera> cameras;
	
	public String getId() {
		return id;
	}
	public void setId( final String id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName( final String name ) {
		this.name = name;
	}

	public String getLandingDate() {
		return landingDate;
	}
	public void setLandingDate( final String newLandingDate ) {
		this.landingDate = newLandingDate;
	}

	public String getMaxDate() {
		return maxDate;
	}
	public void setMaxDate( final String newMaxDate ) {
		this.maxDate = newMaxDate;
	}

	public List<Camera> getCameras() {
		return cameras;
	}
	public void setCameras( final List<Camera> newCameras ) {
		this.cameras = newCameras;
	}
}
