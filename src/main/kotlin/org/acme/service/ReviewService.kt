package org.acme.service

import org.acme.entity.Review
import javax.inject.Inject
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Singleton
class ReviewService {

    @Inject
    lateinit var entityManager: EntityManager

    fun getReviews(): kotlin.collections.List<Review?>? {
        return entityManager!!.createQuery("SELECT c From Review c").resultList as List<Review?>?
    }

    fun getReview(id: Long?): Review {
        return  entityManager!!.find(Review::class.java, id)
    }

    fun getReviewsByUserId(id: String): List<Review> {
        return entityManager!!.createQuery("SELECT c From Review c WHERE c.userId LIKE :userId")
            .setParameter("userId", id)
            .resultList as List<Review>
    }

    @Transactional(Transactional.TxType.REQUIRED)
    fun addReview(review: Review?): Review? {
        entityManager!!.persist(review)
        return review
    }
    @Transactional(Transactional.TxType.REQUIRED)
    fun updateReview(id: Long?, review: Review) {
        val movieToUpdate: Review = entityManager!!.
                find(Review::class.java, id)
        if( null!= movieToUpdate) {
            movieToUpdate.content = review.content
            movieToUpdate.rating = review.rating
        } else  {
            throw RuntimeException("No such review available")
        }
    }
    @Transactional(Transactional.TxType.REQUIRED)
    fun deleteReview(id: Long?) {
        val review: Review = getReview(id)
        entityManager!!.remove(review)
    }

}
