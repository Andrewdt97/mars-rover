import React, { Component } from "react";
import ReactDOM from "react-dom";
import ImageSearch from "./ImageSearch"

class Header extends Component {
    constructor() {
        super();

        this.state = {};
    }

    render() {
        return(
            <>
                <div>
                    <h1>Welcome to the web client for Andrew Thomas's Mars Rover server</h1>
                </div>
                <div>
                    <ImageSearch />
                </div>
            </>
        );
    }
}

export default Header;
const wrapper = document.getElementById("container");
wrapper ? ReactDOM.render(<Header />, wrapper) : false;