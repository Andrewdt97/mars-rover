package andrewdt97.marsroverserver.beans;

import java.util.List;

/**
 * @author jlowery
 *
 */
public class RoverList {

	public List<Rover> rovers;

	public List<Rover> getRovers() {
		return rovers;
	}

	public void setRovers( final List<Rover> rovers ) {
		this.rovers = rovers;
	}
}
