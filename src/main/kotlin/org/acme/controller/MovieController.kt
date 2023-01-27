package org.acme.controller


import org.acme.entity.Movie
import org.acme.entity.Review
import org.acme.entity.User
import org.acme.service.MovieService
import org.acme.service.ReviewService
import org.acme.service.UserService
import javax.annotation.security.PermitAll
import javax.annotation.security.RolesAllowed

import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.SecurityContext


@Path("/api/movies")
class MovieController {

    @Inject
    var userResource: UserService? = null
    @Inject
    var reviewResource: ReviewService? = null
    @Inject
    var movieResource: MovieService? = null


    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    fun getMovies(): List<Movie?>? {
        return movieResource?.getMovies()
    }

    @GET
    @PermitAll
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getMovie(@PathParam("id") id: Long?): Movie? {
        return  movieResource?.getMovie(id)
    }

    @PUT
    @RolesAllowed("admin")
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    fun updateMovie(@PathParam("id") id: Long?, movie: Movie?) {
        movieResource?.updateMovie(id, movie!!)
    }

    @POST
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun addMovie(movie: Movie?): Movie? {
        return  movieResource?.addMovie(movie)
    }
    @POST
    @RolesAllowed("user"+"admin")
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun rateMovie(@Context ctx: SecurityContext, @PathParam("id") id: Long?, movie: Movie): Movie? {
        val user: User = userResource!!.getUserByUsername(ctx.userPrincipal.name.toString())!!
        val reviewToBeCreated: Review = review
        reviewToBeCreated.userId= user.id
        reviewToBeCreated.movieId = id
        return reviewResource?.addReview(reviewToBeCreated)
    }

    @DELETE
    @RolesAllowed("admin")
    @Path("/{id}")
    fun deleteMovie(@PathParam("id") id: Long?): Unit? {
        return movieResource?.deleteMovie(id)
    }



}