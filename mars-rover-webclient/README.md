# Mars Rover Web Client
## Introduction
This is a bare-bones single page application written with React. It polls the mars rover server to populate search parameters and an image viewer.

## Installation
1. Navigate to `mars-rover/mars-rover0webclient`
2. Install packages with `npm install`
3. Start the development server with `npm start` (by default set to launch on port 8081)

Alternatively, you can run `npm run build` and open `./dist/index.html`.

## Technical Overview
### Component Tree
```
Header
 |__ ImageSearch
 |__ ImageViewer
     |__ ImagePager
```
#### `Header`
The container component for the app.

#### `ImageSearch`
Contains all the fields for searching for photos and a submit button to reload the app with the new parameters.

#### `ImageViwer`
Displays the image and contains the ImagePager

#### `ImagePager`
Allows the user to navigate between images.

## Quirks and Future Improvements
### Quirks
1. I had a lot of trouble fetching the image lists. The `fetch()` call for image lists would work in some contexts and not others.
The only reliable way for me to execute it was to place it in the `ImageViewer`'s `componentDidMount()`. Needing to remount the component to get a new list,
I took advantage of the fact the the search submit reloads the page with the search parameters as URL query parameters to populate `ImageViewer`'s `props` with those query parameters.
This is a no doubt hacky and the app shouldn't have to refresh when a search is made, but for the sake of time, I decided this was a sufficient work around.

### Future Improvements
1. Add documentation of any sort
2. Prevent invalid searches from being made. The client has all the information on which cameras the rovers have and which dates they are active.
Logic needs to be implemented so that you cannot select a camera or a date that a rover does not have.
3. Add unit and end-to-end tests
4. Do some stylizing
5. Pass data with the Context API instead of call backs.
6. Improve distribution of props and state variables
