import React from "react";

const Movies = ({movies}) => {
    return <div>
        <h1>
            Movies
        </h1>
        {movies.map((movie) => (
            <div>
                <h2>{movie.name}</h2>
            </div>
        )) }
    </div>

}

export default Movies