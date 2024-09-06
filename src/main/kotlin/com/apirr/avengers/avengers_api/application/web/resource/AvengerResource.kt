package com.apirr.avengers.avengers_api.application.web.resource

import com.apirr.avengers.avengers_api.application.web.resource.request.AvengerRequest
import com.apirr.avengers.avengers_api.application.web.resource.response.AvengerResponse
import com.apirr.avengers.avengers_api.domain.avenger.AvengerRepository
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

private const val API_PATH = "/api/v1/avengers";

@RestController
@RequestMapping(API_PATH)
class AvengerResource(
    @Autowired private val repository: AvengerRepository,
) {
    @GetMapping
    fun getAvengers(): ResponseEntity<List<AvengerResponse>> =
        repository.getAvengers()
            .map { AvengerResponse.from(it) }
            .let {
                ResponseEntity.ok().body(it);
            };

    @GetMapping("{id}/detail")
    fun getAvengerDetail(@PathVariable("id") id: Long) =
        repository.getDetail(id)?.let {
            ResponseEntity.ok().body(AvengerResponse.from(it));
        } ?: ResponseEntity.notFound().build<Void>();


    @PostMapping
    fun createAvenger(@Valid @RequestBody request: AvengerRequest): ResponseEntity<AvengerResponse> =
        request.toAvenger().run {
            repository.create(this);
        }.let {
            ResponseEntity
                .created(URI("$API_PATH/${it.id}"))
                .body(AvengerResponse.from(it));
        };


    @PutMapping("{id}")
    fun updateAvenger(@Valid @RequestBody request: AvengerRequest,
                      @PathVariable("id") id: Long) =
        repository.getDetail(id)?.let {
            AvengerRequest.to(it.id, request).apply {
                repository.update(this)
            }.let { avenger ->
                ResponseEntity.accepted().body(AvengerResponse.from(avenger));
            }
        } ?: ResponseEntity.notFound().build<Void>();


    @DeleteMapping("{id}")
    fun deleteAvenger(@PathVariable("id") id: Long) =
        repository.delete(id).let {
            ResponseEntity.noContent().build<Void>();
        };
}