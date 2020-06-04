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
            </form>
        )
    }
}

export default ImageSearch;