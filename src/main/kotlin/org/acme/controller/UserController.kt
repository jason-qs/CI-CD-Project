package org.acme.controller


import org.acme.config.Certification
import org.acme.config.Token
import org.acme.entity.Movie
import org.acme.entity.Review
import org.acme.entity.User
import org.acme.service.MovieService
import org.acme.service.ReviewService
import org.acme.service.UserService
import org.eclipse.microprofile.jwt.JsonWebToken
import javax.annotation.security.PermitAll
import javax.annotation.security.RolesAllowed
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.SecurityContext

@Path("/api/users")
class UserController{
    @Inject
    var jwt: JsonWebToken? = null
    @Inject
    var token : Token? = null
    @Inject
    var userResource: UserService? = null
    @Inject
    var reviewResource: ReviewService? = null
    @Inject
    var movieResource: MovieService? = null

    @GET
    @Path("/test")
    @PermitAll
    fun getResponseString(@Context ctx: SecurityContext): String {
        val name: String
        if(ctx.userPrincipal == null) {
            name = "anon"
        } else if (!ctx.userPrincipal.name.equals(jwt?.name)) {
            throw InternalServerErrorException("names do not match")
        } else {
            name = ctx.userPrincipal.name
        }
        return name
    }

    @GET
    @Path("/test2")
    @RolesAllowed("admin")
    @Produces(MediaType.TEXT_PLAIN)
    fun getOidcString(): String {
        return "Hello" + jwt.toString()
    }

//    @POST
//    @PermitAll
//    @Path("/login")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    fun login(user: User): Any? {
//        return if (userResource?.authenticate(user.userName,user.password) == true) {
//            val token = token?.getToken(user)
//            Response.ok(token).build()
//        } else Response.status(Response.Status.BAD_REQUEST).entity("Invalid credentials!").build()
//    }

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    fun getUsers(): List<User?>? {
        return userResource?.getUsers()
    }

    @GET
    @PermitAll
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUser(@PathParam("id") id: Long?): User? {
        return userResource?.getUser(id)
    }

    @GET
    @RolesAllowed("admin")
    @Path("/{id}/reviews")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserReviews(@PathParam("id") id: Long?): MutableSet<Review> {
        return userResource?.getUser(id)?.reviews!!
    }

//    @GET
//    @RolesAllowed("admin")
//    @Path("/keys")
//    @Produces(MediaType.APPLICATION_JSON)
//    fun getKeys(certification: Certification) {
//        return certification.generateKeyPairs()
//    }

//    @GET
//    @RolesAllowed("admin")
//    @Path("/{id}/movies")
//    @Produces(MediaType.APPLICATION_JSON)
//    fun getUserRestaurants(@PathParam("id") id: Long?): MutableSet<Movie> {
//        return userResource?.getUser(id)?.movie!!
//    }

//    @PUT
//    @RolesAllowed("admin")
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    fun updateUser(@PathParam("id") id: Long?, user: User?) {
//        userResource?.updateUser(id, user!!)
//    }
    @DELETE
    @RolesAllowed("admin")
    @Path("/{id}")
    fun deleteUser(@PathParam("id") id: Long?) {
        userResource?.deleteUser(id)
    }

//    @POST
//    @PermitAll
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    fun addUser( user: User): Response {
//        val result = userResource?.addUser(user)
//        return if (result != null) {
//            Response .status(Response.Status.CREATED).entity("created $result").build()
//        } else Response.status(Response.Status.BAD_REQUEST).entity("unable to create user!").build()
//    }
    @GET
    @RolesAllowed("user")
    @Path("/me")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun getMe(@Context ctx: SecurityContext): User? {
        return userResource!!.getUserByUsername(ctx.userPrincipal.name)
    }


    @GET
    @RolesAllowed("user")
    @Path("/me/reviews")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun getMeReviews(@Context ctx: SecurityContext): MutableSet<Review> {
        return userResource!!.getUserByUsername(ctx.userPrincipal.name.toString())!!.reviews
    }

    @GET
    @RolesAllowed("user")
    @Path("/me/reviews/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun getMeReviewsIndex(@PathParam("id") id: Int?,@Context ctx: SecurityContext): Any {
       val reviews: MutableSet<Review> = userResource!!.getUserByUsername(ctx.userPrincipal.name)!!.reviews
        return reviews.elementAt(id!!)
    }

    @PUT
    @RolesAllowed("user")
    @Path("/me/reviews/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun updateMeReviewsIndex(@PathParam("id") id: Int?,@Context ctx: SecurityContext, review: Review): Unit? {
        val reviews: MutableSet<Review> = userResource!!.getUserByUsername(ctx.userPrincipal.name)!!.reviews
        val reviewToBeUpdated: Review = reviews.elementAt(id!!)
        return reviewResource?.updateReview(reviewToBeUpdated.id, review)
    }

    @DELETE
    @RolesAllowed("user")
    @Path("/me/reviews/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun deleteMeReviewsIndex(@PathParam("id") id: Int?,@Context ctx: SecurityContext): Any {
        val reviews: MutableSet<Review> = userResource!!.getUserByUsername(ctx.userPrincipal.name)!!.reviews
        val reviewToBeDeleted: Review = reviews.elementAt(id!!)
        return reviewResource!!.deleteReview(reviewToBeDeleted.id)
    }

    @POST
    @RolesAllowed("user")
    @Path("/me/reviews")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createMeReviews(@Context ctx: SecurityContext,review: Review): Review? {
        val user: User= userResource!!.getUserByUsername(ctx.userPrincipal.name.toString())!!
        val reviewToBeCreated: Review = review
        reviewToBeCreated.userId= user.id
        return reviewResource?.addReview(reviewToBeCreated)
    }
//
//    @GET
//    @RolesAllowed("user")
//    @Path("/me/movies/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    fun getMeMoviesIndex(@PathParam("id") id: Int?,@Context ctx: SecurityContext): Any {
//        val movies: MutableSet<Movie> = userResource!!.getUserByUsername(ctx.userPrincipal.name)!!.movie
//        return movies.elementAt(id!!)
//    }
//
//
//    @PUT
//    @RolesAllowed("user")
//    @Path("/me/movies/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    fun updateMeMovie(@PathParam("id") id : Long?, @Context ctx: SecurityContext, movie: Movie): Unit?{
//        val movies: MutableSet<Movie> = userResource!!.getUserByUsername(ctx.userPrincipal.name)!!.movie
//        val movieToBeUpdated: Movie = movies.elementAt(id!!.toInt())
//        return movieResource?.updateMovie(movieToBeUpdated.id,movie)
//    }

}