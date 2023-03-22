package hello.springmvc.basic.request

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Locale

@RestController
class RequestHeaderController {

    val log = LoggerFactory.getLogger(javaClass)

    @RequestMapping("/headers")
    fun headers(
        request: HttpServletRequest,
        response: HttpServletResponse,
        locale: Locale,
        @RequestHeader headerMap: MultiValueMap<String, String>,
        @RequestHeader("host") host: String,
        @CookieValue(value = "myCookie", required = false) cookie: String
    ): String {
        log.info("request={}", request)
        log.info("response={}", response)
        log.info("locale={}", locale)
        log.info("headerMap={}", headerMap)
        log.info("host={}", host)
        log.info("myCookie={}", cookie)

        return "ok"
    }
}