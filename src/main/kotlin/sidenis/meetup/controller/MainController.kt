package sidenis.meetup.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import sidenis.meetup.dto.Request
import sidenis.meetup.service.MainService

@Controller
@RequestMapping("/api/main")
open class MainController(val service: MainService) {
    @RequestMapping("calc", method = [RequestMethod.POST])
    @ResponseBody
    fun createEntity(
            @RequestBody
            request: Request
    ) = service.calc(request)

    @RequestMapping("calcResults/{calcId}", method = [RequestMethod.GET])
    @ResponseBody
    fun getCalcResult(
            @PathVariable("calcId")
            calcId: Long
    ) = service.getCalcResult(calcId)
}