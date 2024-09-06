package com.apirr.avengers.avengers_api.application.web.resource.response

import com.apirr.avengers.avengers_api.domain.avenger.Avenger

data class AvengerResponse(
    val id: Long?,
    val nick: String,
    val person: String,
    val description: String?,
    val history: String?
) {
    companion object {
        fun from(avenger: Avenger) = AvengerResponse(
            id = avenger.id,
            nick = avenger.nick,
            person = avenger.person,
            description = avenger.description,
            history = avenger.history
        )
    }
}
