import { Typography } from "@mui/material";
import React from "react";
import NavBar from "../components/NavBar";
import './Movie.css';

function Movie () {

    return (
        <>
        <NavBar />
        <div className="mainbody">
        <div className="leftDiv">
            <div className="moviePoster">
                PlaceHolder
            </div>
        </div>
        <div className="rightDiv">
                <Typography>
                    Movie Title
                </Typography>
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
        <div className="reviewDiv">
            <Typography>
                Reviews
            </Typography>
            <Typography>
                Create Review
            </Typography>
        </div>
        </div>
    </>
    )
}

export default Movie;