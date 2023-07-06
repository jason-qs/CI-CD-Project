import { Card, Typography } from "@mui/material";
import React from "react"
import './ReviewCard.css'

function ReviewCard () {

    return (
        <>
        <div className="MainBody">
            <Card>
            <Typography>UserName:</Typography>
            <Typography>Rating</Typography>
            <Typography>Placeholder Text</Typography>
            </Card>
        </div>
        </>
    )
}

export default ReviewCard;