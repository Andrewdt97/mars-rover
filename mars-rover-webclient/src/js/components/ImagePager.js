import React, { Component } from "react";

class ImagePager extends Component {
    constructor(props) {
        super(props);
        this.state = {}

        this.handlePageLeft = this.handlePageLeft.bind(this);
        this.handlePageRight = this.handlePageRight.bind(this);
        this.fetchImageCallback = this.fetchImageCallback.bind(this);
    }

    fetchImageCallback(imgIdx) {
        if (imgIdx < 0 || imgIdx > this.props.maxImg) {
            imgIdx = 0;
        }
        this.props.parentCallback(imgIdx);
    }

    handlePageLeft(event) {
        var newIdx = (this.props.currentImg - 1) % this.props.maxImg;
        this.setState({
            currentImg: newIdx
        });
        this.fetchImageCallback(newIdx);
    }

    handlePageRight(event) {
        var newIdx = (this.props.currentImg + 1) % this.props.maxImg;
        this.fetchImageCallback(newIdx);
    }

    render() {
        return(
            <>
                <button type="button" onClick={this.handlePageLeft}> &lt; </button>
                <button type="button" onClick={this.handlePageRight}> &gt; </button>
                <div>Image {this.props.currentImg + 1} of {this.props.maxImg + 1}</div>
            </>
        )
    }
}

export default ImagePager;