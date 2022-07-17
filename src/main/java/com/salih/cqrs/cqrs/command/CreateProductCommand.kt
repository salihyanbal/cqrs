package com.salih.cqrs.cqrs.command


data class CreateProductCommand constructor(
        val name: String?
): Command {
    constructor() : this("") {

    }
}
