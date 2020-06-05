# Mars Rover

Hello and welcome to my repository for a client and server that interacts with NASA's [Mars Rover Photos API](https://api.nasa.gov/). This reponsitory was completed as a project with the following criteria:

#### Using the NASA Mars Rover Photos API, write a program that collects a photo from a given day, storing it locally.

### Full Acceptance Criteria
* Use list of dates below to pull the images were captured on that day by reading in a text ﬁle:
    * 02/27/17
    * June 2, 2018
    * Jul-13-2016
    * April 31, 2018
* Language needs to be *Java*.
* We should be able to run and build (if applicable) locally after you submit it
* Include relevant documentation (.MD, etc.) in the repo

### Bonus Goals
* Bonus - Unit Tests, Static Analysis, Performance tests or any other things you feel are important for Deﬁnition of Done
* Double Bonus - Have the app display the image in a web browser
* Triple Bonus – Have it run in a Docker or K8s (Preferable)

## Solution

For a more thourough overview of each component including install instructions, see the README within their respective folders.

#### Server (./mars-rover-server)
There is a backend, Springboot server with a number of REST API endpoints exposed. Namely:

* *GET* which rovers have data availible : `api/v1/rovers`
* *GET* list of photos matching search criteria : `api/v1/<rover_name>/photos?<earth_date>&<camera>`
* *GET* image file from NASA's API : `api/v1/photo?<img_src>`

By default, the server launches at `localhost:8080/`

### Web Client (./mars-rover/webclient)
On the front end, there is a React based single page application that polls the Springboot server for data. By default, the dev server launches at `localhost:8081/`.
