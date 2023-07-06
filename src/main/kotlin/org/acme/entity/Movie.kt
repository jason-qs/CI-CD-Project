package org.acme.entity

import java.io.Serializable
import javax.persistence.*

@Table(name="movies")
@Entity
class Movie: Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String? = null
    var address: String? = null
    var category: String? = null


    constructor(id: Long?, name: String?, address: String?, category: String?) {
        this.id = id
        this.name = name
        this.address = address
        this.category = category
    }

    constructor() : super() {}
}