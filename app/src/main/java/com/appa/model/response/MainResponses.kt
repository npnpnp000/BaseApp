package com.adviserall22spdaslld.model.response

class MainResponses : ArrayList<MainResponsesItem>()

data class MainResponsesItem(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)