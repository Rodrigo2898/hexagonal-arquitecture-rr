package com.apirr.avengers.avengers_api.domain.avenger

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AvengerRepository : JpaRepository<Avenger, Long> {
    fun getDetail(id: Long): Avenger;

    fun getAvengers(): List<Avenger>;

    fun create(avenger: Avenger): Avenger;

    fun delete(id: Long);

    fun update(avenger: Avenger): Avenger;
}