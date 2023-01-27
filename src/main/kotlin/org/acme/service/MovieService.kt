package org.acme.service

import org.acme.entity.Movie
import javax.inject.Inject
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Singleton
class MovieService {
    @Inject
    lateinit var entityManager: EntityManager

    fun getMovies(): kotlin.collections.List<Movie?>? {
        return entityManager!!.
        createQuery("Select c from Movie c").
        resultList as List<Movie?>?
    }

    fun getMovie(id: Long?): Movie {
        return  entityManager!!.find(Movie::class.java, id)
    }

    @Transactional(Transactional.TxType.REQUIRED)
    fun addMovie(movie: Movie?): Movie? {
        entityManager!!.persist(movie)
        return movie
    }

    @Transactional(Transactional.TxType.REQUIRED)
    fun updateMovie(id: Long?, movie: Movie) {
        val movieToUpdate: Movie = entityManager!!.
        find(Movie::class.java, id)
        if(null != movieToUpdate) {
            movieToUpdate.address = movie.address
            movieToUpdate.name = movie.name
        } else {
            throw RuntimeException("no such Movie available")
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    fun deleteMovie(id: Long?) {
        val movie: Movie = getMovie(id)
        entityManager!!.remove(movie)
    }
}