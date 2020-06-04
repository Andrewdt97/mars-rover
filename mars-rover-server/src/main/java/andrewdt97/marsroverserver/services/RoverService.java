package andrewdt97.marsroverserver.services;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import andrewdt97.marsroverserver.beans.RoverList;
import andrewdt97.marsroverserver.clients.NasaApiClient;

/**
 * @author andrewdt97
 * Class for handling rover requests
 */
@Component
public class RoverService {

    @Inject
    NasaApiClient apiClient;

    /**
     * @author: andrewdt97
     * Prepares call to NASA's api for rover information
     *
     * @return a RoverList with the API call results
     */
    public RoverList fetchRoverList() {
        return apiClient.fetchRoverList();
    }
}