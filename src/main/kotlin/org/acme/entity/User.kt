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


    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "userId")
    open var reviews: MutableSet<Review> = mutableSetOf()



    constructor(id: String?, username: String) : super() {

        this.id = id
        this.username = username

    }
    constructor() : super() {}
}