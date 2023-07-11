package org.acme.controller


import org.acme.entity.Review
import org.acme.service.ReviewService
import org.eclipse.microprofile.jwt.JsonWebToken
import javax.annotation.security.PermitAll
import javax.annotation.security.RolesAllowed
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.SecurityContext

@Path("/api/users")
class UserController{
    @Inject
    var jwt: JsonWebToken? = null
    @Inject
    var reviewResource: ReviewService? = null
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

    @GET
    @Path("/info")
    @PermitAll
    fun getinfo():String{
        return jwt!!.subject
    }


    @GET
    @RolesAllowed("admin")
    @Path("/{id}/reviews")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUserReviews(@PathParam("id") id: Long?): List<Review>? {
        return reviewResource?.getReviewsByUserId(id.toString())
    }



    @GET
    @RolesAllowed("user")
    @Path("/me/reviews")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun getMeReviews(@Context ctx: SecurityContext): List<Review> {
        return reviewResource!!.getReviewsByUserId(jwt!!.subject)
    }

    @GET
    @RolesAllowed("user")
    @Path("/me/reviews/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun getMeReviewsIndex(@PathParam("id") id: Int?,@Context ctx: SecurityContext): Any {
       val reviews: List<Review> = reviewResource!!.getReviewsByUserId(jwt!!.subject)
        return reviews.elementAt(id!!)
    }

    @PUT
    @RolesAllowed("user")
    @Path("/me/reviews/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun updateMeReviewsIndex(@PathParam("id") id: Int?,@Context ctx: SecurityContext, review: Review): Unit? {
        val reviews: List<Review> = reviewResource!!.getReviewsByUserId(jwt!!.subject)
        val reviewToBeUpdated: Review = reviews.elementAt(id!!)
        return reviewResource?.updateReview(reviewToBeUpdated.id, review)
    }

    @DELETE
    @RolesAllowed("user")
    @Path("/me/reviews/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun deleteMeReviewsIndex(@PathParam("id") id: Int?,@Context ctx: SecurityContext): Any {
        val reviews: List<Review> = reviewResource!!.getReviewsByUserId(jwt!!.subject)
        val reviewToBeDeleted: Review = reviews.elementAt(id!!)
        return reviewResource!!.deleteReview(reviewToBeDeleted.id)
    }

    @POST
    @RolesAllowed("user")
    @Path("/me/reviews")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createMeReviews(@Context ctx: SecurityContext,review: Review): Review? {
        val reviewToBeCreated: Review = review
        reviewToBeCreated.userId= jwt!!.subject
        return reviewResource?.addReview(reviewToBeCreated)
    }


}