import { Typography } from "@mui/material";
import React from "react";
import MainBody from "../components/MainBody";
import NavBar from "../components/NavBar";
import './User.css';
import ReviewCard from "../components/ReviewCard";

function UserPage () {

    return(
        <>
        <NavBar />
        <div className="mainbody">
            <div className="leftdiv">

    
        <Typography>
            Reviews
        </Typography>
        <Typography>
            Favorites
        </Typography>
        <Typography>
            Settings
        </Typography>
            </div>
            <div className="rightdiv">
        <Typography>
            Recent Reviews
        </Typography>
        </div>
        </div>
        </>
    )
}


export default UserPage;