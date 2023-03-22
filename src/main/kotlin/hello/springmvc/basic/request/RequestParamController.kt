package hello.springmvc.basic.request

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class RequestParamController {

    val log = LoggerFactory.getLogger(javaClass)

    @RequestMapping("/request-param-v1")
    fun requestParamV1(request: HttpServletRequest, response: HttpServletResponse) {
        val username = request.getParameter("username")
        val age = Integer.parseInt(request.getParameter("age"))
        log.info("username={}", username)
        log.info("age={}", age)

        response.writer.write("ok")
    }
}