import React, { Component } from "react";
import ImagePager from "./ImagePager"

class ImageViwer extends Component {

    constructor(props) {
        super(props);
        
        this.state = {
            image: "",
            imageList: [],
            maxImg: 0,
            imgIdx: 0,
        }

        this.imagePagerCallback = this.imagePagerCallback.bind(this);
        this.fetchImage = this.fetchImage.bind(this);
        this.fetchPhotoList = this.fetchPhotoList.bind(this);
    }

    componentDidMount() {
        console.log("Mounting ImageViewer");
        console.log(this.props.timestamp);
        console.log(this.props.rover);
        console.log(this.props.earth_date);
        console.log(this.props.camera);
        this.fetchPhotoList();
    }

    componentDidUpdate(prevProps) {
        const isSameRover = this.props.rover == prevProps.rover;
        const isSameDate = this.props.earth_date == prevProps.earth_date;
        const isSameCamera = this.props.camera == prevProps.camera;
        if (!(isSameRover && isSameDate && isSameCamera)) {
          this.fetchPhotoList();
        }
      }

    fetchPhotoList() {
        var uri = "http://127.0.0.1:8080/api/v1/";
        uri = uri.concat(this.props.rover)
            .concat("/photos?earth_date=")
            .concat(this.props.earth_date)
            .concat("&camera=")
            .concat(this.props.camera);
        fetch(uri)
        .then(res => res.json())
        .then(
            (result) => {
                console.log(result);
                const maxPhotoIdx = result.photos.length -1;
                this.setState({
                    imageList: result.photos,
                    maxImg: maxPhotoIdx,
                    imgIdx: 0,
                });
                this.fetchImage(this.state.imgIdx);
            },
            (error) => {
            console.log("I had an error :(");
            console.log(error)
            this.setState({
                isLoaded: true,
                error
            });
            }
        );
    }

    fetchImage(idx) {
        var imgUrl = this.state.imageList[idx].img_src;
        var uri = "http://localhost:8080/api/v1/photo?img_src=".concat(imgUrl);
        fetch(uri)
          .then((response) => {
            return response.blob();
          })
          .then((blob) => {
            var objectURL = URL.createObjectURL(blob);
            this.setState({
                image: objectURL,
                imgIdx: idx,
            });
          });
    }

    imagePagerCallback(idx) {
        this.fetchImage(idx);
    }

    render() {
        return(
        <>
            <img src={this.state.image}/>
            <br/>
            <ImagePager imageList={this.state.imageList} maxImg={this.state.maxImg} currentImg={this.state.imgIdx} parentCallback={this.imagePagerCallback}/>
        </>
        )}
}

export default ImageViwer