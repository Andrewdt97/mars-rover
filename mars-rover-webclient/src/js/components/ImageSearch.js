import React, { Component } from "react";
import ReactDOM from "react-dom";
import Select from "react-select";

class ImageSearch extends Component {
    constructor() {
        super();

        this.state = {
            rovers : [
                { value: "curisioty", label: "Curiosity"},
                { value: "another", label: "another"}],
            cameras : [
                {value: "all", label: "All"},
                {value: "FHAZ", label: "FHAZ"},
            ]
        }

        this.handleRoverSelectChange = this.handleRoverSelectChange.bind(this);
        this.handleDatePickerChange = this.handleDatePickerChange.bind(this);
        this.handleSubmitButtonPress = this.handleSubmitButtonPress.bind(this);
    }

    searchResultsCallback(results) {
        this.props.parentCallback(results)
    }

    componentDidMount() {
        this.fetchRoverInfo();
    }

    fetchRoverInfo() {
        fetch("http://127.0.0.1:8080/api/v1/rovers")
        .then(res => res.json())
        .then(
            (result) => {
            var roversList = this.extractRoverNames(result);
            var camerasList = this.extractCameraNames(result);
            this.setState({
                rovers: roversList,
                cameras : camerasList
            });
            },
            (error) => {
            this.setState({
                isLoaded: true,
                error
            });
            }
        )
    }

    extractRoverNames(roversJson) {
        var roverNames = [];
        var lowerCaseName;
        roversJson.rovers.forEach(rover => {
            lowerCaseName = rover.name.toLowerCase();
            roverNames.push({
                label: rover.name,
                value: lowerCaseName   });
        });
        return roverNames;
    }

    extractCameraNames(roversJson) {
        var cameraNames = [];
        roversJson.rovers[0].cameras.forEach(camera => {
            cameraNames.push({
                label: camera.name,
                value: camera.name   });
        });
        return cameraNames;
    }

    handleRoverSelectChange(slectedEntry) {
        const { selectedValue } = slectedEntry.value;
        this.setState({
            roverSelect : selectedValue,
        });
    }

    handleDatePickerChange(event) {
        const { newDate } = event.target.value;
        this.setState({
            earth_date : newDate
        });
    }

    handleRoverSelectChange(slectedEntry) {
        const { selectedValue } = slectedEntry.value;
        this.setState({
            cameraSelect : selectedValue,
        });
    }

    handleSubmitButtonPress(event) {
        var uri = "http://localhost:8080/api/v1/";
        uri = uri.concat(this.state.roverSelect).concat("/photos");
        var data = {
            earth_date : this.state.earth_date,
            camera : this.state.camera
        };
        fetch(uri, data)
        .then(res => res.json())
        .then(
            (result) => {
                this.searchResultsCallback(result);
            },
            (error) => {
            this.setState({
                isLoaded: true,
                error
            });
            }
        )
    }

    render() {
        return(
            <form>
                <label htmlFor="rover">Rover: </label>
                <Select name="rover" options={this.state.rovers} isMulti={false} onChange={this.handleRoverSelectChange}/>
                <br/>
                <label htmlFor="earth_date">Date: </label>
                <input type="date" name="earth_date" onChange={this.handleDatePickerChange}/>
                <br/>
                <label htmlFor="camera">Camera: </label>
                <Select name="camera" options={this.state.cameras} isMulti={false} onChange={this.handleCameraSelectChange}/>
                <br/>
                <input type="submit" value="Search Photos" onClick={this.handleSubmitButtonPress}/>
            </form>
        )
    }
}

export default ImageSearch;