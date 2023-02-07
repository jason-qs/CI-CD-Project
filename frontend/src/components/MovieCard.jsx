import React from "react";
import Card from '@mui/material/Card'
import CardActionArea from '@mui/material'

function MovieCard () {
    return(
        <>
            <Card sx={{maxWidth:30}} >
                <CardActionArea>
                <CardMedia
          component="img"
          height="140"
          image="notFound.jpg"
          alt="movie example"
        />
                </CardActionArea>
            </Card>
        
        </>
    )
}

export default MovieCard;