package sidenis.meetup.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class RequestEntity(
        @Id
        @GeneratedValue
        var id: Long? = null,
        var user: String? = null,
        var calcResult: Pair<Double, String?> = Pair(0.0, null)
)