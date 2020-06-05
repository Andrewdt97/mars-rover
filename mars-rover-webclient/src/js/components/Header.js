import React, { Component } from "react";
import ReactDOM from "react-dom";
import ImageSearch from "./ImageSearch"
import ImageViwer from "./ImageViwer";

class Header extends Component {
    constructor() {
        super();

        const qParams = new URLSearchParams(location.search);
        const roverSelect = qParams.get('rover') || "curiosity";
        const date = qParams.get("earth_date")  || "2020-06-03";
        const camera = qParams.get("camera") || "NAVCAM"; 
        this.state = {
            rover: roverSelect,
            earth_date: date,
            camera: camera,
        };

        this.imageSearchCallback = this.imageSearchCallback.bind(this);
    }

    imageSearchCallback(roverName, date, cameraSelect) {
        this.setState({
            rover: roverName,
            earth_date: date,
            camera: cameraSelect,
        });
    }

    render() {
        return(
            <>
                <div>
                    <h1>Welcome to the web client for Andrew Thomas's Mars Rover server</h1>
                </div>
                <div>
                    <ImageSearch parentCallback={this.imageSearchCallback} />
                    <ImageViwer rover={this.state.rover} earth_date={this.state.earth_date} camera={this.state.camera} />
                </div>
            </>
        );
    }
}

export default Header;
const wrapper = document.getElementById("container");
wrapper ? ReactDOM.render(<Header />, wrapper) : false;