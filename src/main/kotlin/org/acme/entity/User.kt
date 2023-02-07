package org.acme.entity

import java.io.Serializable
import java.sql.Timestamp
import javax.persistence.*

@Table(name="users")
@Entity
class User : Serializable{
    @Id
    var id: String? = null
    var username: String = ""
    var email: String = ""
    var createdAt: Timestamp? = null

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "userId")
    open var reviews: MutableSet<Review> = mutableSetOf()

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "userId")
    open var movie: MutableSet<Movie> = mutableSetOf()


    constructor(id: String?, email: String,
                 username: String, createdAt:Timestamp) : super() {

        this.id = id
        this.email = email
        this.username = username
        this.createdAt = createdAt

    }
    constructor() : super() {}
}