package sidenis.meetup.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import sidenis.meetup.dto.Request
import sidenis.meetup.entity.RequestEntity
import sidenis.meetup.entity.RequestRepository

@Service
open class MainService(val repostiory: RequestRepository) {
    fun calc(request: Request) =
            repostiory
                    .save(
                            RequestEntity(user = request.user, calcResult = remoteCalc(request.calcNumber))
                    )
                    .let(::mapEntity)

    fun getCalcResult(calcId: Long) = repostiory.getOne(calcId).let(::mapEntity)

    private fun mapEntity(entity: RequestEntity) = Pair(entity.id!!, entity.calcResult)

    private fun remoteCalc(number: Int) =
            RestTemplate().getForEntity("http://service2:8080/calc/$number", Pair::class.java).body as Pair<Double, String?>?
            ?: Pair(0.0, null)
}