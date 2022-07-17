package com.salih.cqrs.model

import javax.persistence.*

@Entity
@Table(name = "product_info")
data class Product constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val name: String
) {
        constructor() : this(0, "") {

        }
}