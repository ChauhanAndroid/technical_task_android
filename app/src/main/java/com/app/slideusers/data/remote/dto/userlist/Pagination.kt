package com.app.slideusers.data.remote.dto.userlist

data class Pagination(
    val limit: Int,
    val links: Links,
    val page: Int,
    val pages: Int,
    val total: Int
)