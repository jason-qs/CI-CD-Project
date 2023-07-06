package org.acme.entity

import java.io.Serializable
import javax.persistence.*

@Table(name = "reviews")
@Entity
class Review : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var id: Long? = null
    var content: String? = null
    var rating: Float? = null
    var movieId: Long? = null
    var userId: String? = null

    constructor(id: Long?, content: String?, rating: Float?, movieId: Long?, userId: String?) {
        this.id = id
        this.content = content
        this.rating = rating
        this.movieId = movieId
        this.userId = userId
    }

    constructor() : super() {}
}