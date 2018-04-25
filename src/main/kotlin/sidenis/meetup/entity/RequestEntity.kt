package sidenis.meetup.entity

import sidenis.meetup.dto.CalcResponse
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class RequestEntity(
        @Id
        @GeneratedValue
        var id: Long? = null,
        var user: String? = null,
        var calcResultValue: Double? = null,
        var calcedByNode: String? = null
)