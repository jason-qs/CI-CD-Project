import { Typography } from "@mui/material";
import React from "react";
import NavBar from "../components/NavBar";
import ReviewCard from "../components/ReviewCard";
import './Movie.css';

function Movie () {

    return (
        <>
        <NavBar />
        <div className="mainBody">
        <div className="bodyContainer">
        <div className="leftDiv">
            <div className="moviePoster">
                Text
            </div>
        </div>
        <div className="rightDiv">
            <div className="TitleDiv">
                <Typography>
                    Movie Title
                </Typography>
                </div>
                <div className="movieDetails">
                    <Typography>
                        Rating:
                    </Typography>
                    <Typography>
                        Release Date:
                    </Typography>
                    <Typography>
                        Genre:
                    </Typography>
                </div>

        </div>
    </div>
    <div className="review container">
        <div className="reviewDiv">
            <Typography>
                Reviews
            </Typography>
            <Typography>
                Create Review
            </Typography>
            <ReviewCard />
        </div>
        </div>
    </div>
    </>
    )
}

export default Movie;