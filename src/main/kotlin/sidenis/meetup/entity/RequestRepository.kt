package sidenis.meetup.entity

import org.springframework.data.jpa.repository.JpaRepository

interface RequestRepository : JpaRepository<RequestEntity, Long>