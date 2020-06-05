# Mars Rover Server
## Introduction
This is a fully functional backend server run by Springboot serving a four-endpoint REST API. This was my first experience with Springboot and I must give credit to [jlowrey457](https://github.com/jlowery457/) for inspiration from [his solution](https://github.com/jlowery457/nasa-exercise).
The server comes with complete documentation and unit tests written in Junit.

## Installation
1. Sign up for an [API key](https://api.nasa.gov/) from NASA. Key generation is free and instant.
2. Insert that key into the enviornment variable `NASA_API_KEY`
3. Navigate to `mars-rover/mars-rover-server`
4. Run `./gradlew build`
5. Start server with `./gradlew bootRun`

**Note: The server is not configured to run in a container. While familiar with the concept of containers, I've never set one up and for the sake of time I decided to forego that stretch goal.**

## Testing
### Unit Tests
A full set of unit tests are available for the server.

To preform tests: `./gradlew test` from within the server root folder.

### Acceptance Test
#### Criteria
* Use list of dates below to pull the images were captured on that day by reading in a text ﬁle:
    * 02/27/17
    * June 2, 2018
    * Jul-13-2016
    * April 31, 2018
    
#### Running as Unit Test
The acceptance test is implemented as a unit test and as an endpoint. It takes the values from the `resources/acceptance_test_dates.txt` file and pulls the list of photos on the Curiosity rover for each.
If you have a testing environment configured, you can run the unit test individually from `testAcceptanceTest()` in `test/src/java/andrewdt97/mars-rover/ServiceTesters.java`.
Because the behavior for the acceptance test is much different from that of the web app interface, the acceptance test gets its own service. Note that the last date of the acceptance test, April 31, 2018 is invalid and not processed.

#### Running as API Call
The JSON results of the acceptance test can also be accessed via a call to `localhost:8080/api/v1/acceptance_test`.

## Technical overview
### Exposed endpoints
* *GET* which rovers have data available : `api/v1/rovers`
* *GET* list of photos matching search criteria : `api/v1/<rover_name>/photos?<earth_date>&<camera>`
* *GET* image file from NASA's API : `api/v1/photo?<img_src>`
* *GET* acceptance test results : `api/v1/acceptance_test`

### Photo Fetching
When the server receives a request for a photo, it first checks a local cache at `/tmp/`. If the image is not found, it is pulled from NASA and passed along while storing in the cache.

### Photo list fetching
When called to fetch a list of photos, the server insures the date is formatted correctly and valid before calling NASA and returning a list of `Photo` objects with metadata on the image, including the source, `img_src`.

## Quirks and Future Improvements
### Quirks
1. The server fails when not making https requests because it can't handle the redirect from NASA to https. After wrestling for some time, I decided to just ensure that request uris are https before calling NASA.
This is definitely less than ideal, but for the scope of the project, I considered it an acceptable solution.

### Future improvements
1. Fix name space naming to be more conventional
2. Improve API endpoint paths to be more uniform
3. Increase unit test coverage of edge and error cases
4. Add more robust logging, particularly for unit tests
5. Because the webapp always displays images from it's received list of photos, it makes sense to start caching images after a photo list fetch.

## Technologies Used
* Java 8
* Springboot
* Junit
* Gradle
* Ubuntu
