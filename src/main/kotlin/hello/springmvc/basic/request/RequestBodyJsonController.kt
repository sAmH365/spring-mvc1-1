package hello.springmvc.basic.request

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValues
import hello.springmvc.basic.HelloData
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import java.nio.charset.StandardCharsets

@Controller
class RequestBodyJsonController {
    val log = LoggerFactory.getLogger(javaClass)

    val objectMapper = ObjectMapper()

    @PostMapping("/request-body-json-v1")
    fun requestBodyJsonV1(request: HttpServletRequest, response: HttpServletResponse) {
        val inputStream = request.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        val helloData = objectMapper.readValue(messageBody, HelloData::class.java)
        log.info("username={}, age={}", helloData.username, helloData.age)

        response.writer.write("request ok!!!")
    }

    @PostMapping("/request-body-json-v2")
    fun requestBodyJsonV2(@RequestBody messageBody: String, response: HttpServletResponse) {
        val helloData = objectMapper.readValue(messageBody, HelloData::class.java)
        log.info("username={}, age={}", helloData.username, helloData.age)
        response.writer.write("request ok!!")
    }

    @PostMapping("/request-body-json-v3")
    fun requestBodyJsonV3(@RequestBody helloData: HelloData, response: HttpServletResponse) {
        log.info("username={}, age={}", helloData.username, helloData.age)
        response.writer.write("request ok!!")
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    fun requestBodyJsonV4(data: HttpEntity<HelloData>): String {
        val helloData = data.body!!
        log.info("username={}, age={}", helloData.username, helloData.age)

        return "request ok!!"
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    fun requestBodyJsonV5(@RequestBody helloData: HelloData): HelloData {
        log.info("username={}, age={}", helloData.username, helloData.age)
        return helloData
    }

/* @ResponseBody 생략 불가능 생략할시 @ModelAttribute 적용
    @PostMapping("/request-body-json")
    fun requestBodyJson(helloData: HelloData): ResponseEntity<String> {
        log.info("username={}, age={}", helloData.username, helloData.age)
        return ResponseEntity("request ok!", HttpStatus.OK)
    }
*/
}