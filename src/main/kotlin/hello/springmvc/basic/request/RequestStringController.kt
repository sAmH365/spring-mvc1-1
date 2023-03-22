package hello.springmvc.basic.request

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import java.io.InputStream
import java.io.Writer
import java.nio.charset.StandardCharsets

@Controller
class RequestStringController {

    val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/request-body-string-v1")
    fun requestBodyStringV1(request: HttpServletRequest, response: HttpServletResponse) {
        val inputStream = request.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        log.info("messageBody={}", messageBody)
        response.writer.write("ok")
    }

    @PostMapping("/request-body-string-v2")
    fun requestBodyStringV2(inputStream: InputStream, responseWriter: Writer) {
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)
        log.info("messageBody={}", messageBody)
        responseWriter.write("ok")
    }

    // httpMessage Converter가 동작 -> HTTP 요청/응답메시지를 스펙화 해놓은 것이라고 생각
    @PostMapping("/request-body-string-v3")
    fun requestBodyStringV3(httpEntity: RequestEntity<String>):ResponseEntity<String> {
        val messageBody = httpEntity.body
        log.info("messageBody={}", messageBody)
        return ResponseEntity("ok", HttpStatus.CREATED)
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    fun requestBodyStringV4(@RequestBody messageBody: String):String {
        log.info("messageBody={}", messageBody)
        return "ok"
    }
}