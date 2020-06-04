import React, { Component } from "react";
import ReactDOM from "react-dom";

class Header extends Component {
    constructor() {
        super();

        this.state = {};
    }

    render() {
        return(
            <h1>Welcome to the web client for Andrew Thomas's Mars Rover server</h1>
            <div>
                <ImageSearch />
            </div>
        );
    }
}

export default Header;
const wrapper = document.getElementById("container");
wrapper ? ReactDOM.render(<Header />, wrapper) : false;