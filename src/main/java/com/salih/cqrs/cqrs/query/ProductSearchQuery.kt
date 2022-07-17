package com.salih.cqrs.cqrs.query

data class ProductSearchQuery constructor(
        val id: Long,
        val name: String
): Query {
    constructor() : this(0,"") {

    }
}
