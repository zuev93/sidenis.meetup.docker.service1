package sidenis.meetup.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import sidenis.meetup.dto.CalcResponse
import sidenis.meetup.dto.Request
import sidenis.meetup.entity.RequestEntity
import sidenis.meetup.entity.RequestRepository

@Service
open class MainService(val repostiory: RequestRepository) {
    fun calc(request: Request) : Pair<Long, CalcResponse> {
        val calcResponse = remoteCalc(request.calcNumber)
        return repostiory
                .save(RequestEntity(user = request.user, calcResultValue = calcResponse?.first, calcedByNode = calcResponse?.second))
                .let(::mapEntity)
    }

    fun getCalcResult(calcId: Long) = repostiory.getOne(calcId).let(::mapEntity)

    private fun mapEntity(entity: RequestEntity) = Pair(entity.id!!, CalcResponse(entity.calcResultValue, entity.calcedByNode))

    private fun remoteCalc(number: Int) =
            RestTemplate().getForEntity("http://service2:8080/calc/$number", CalcResponse::class.java).body ?: null
}