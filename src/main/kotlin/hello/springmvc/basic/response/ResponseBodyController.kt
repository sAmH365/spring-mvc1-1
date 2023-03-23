package hello.springmvc.basic.response

import hello.springmvc.basic.HelloData
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

//@Controller + @ResponseBody
@RestController
class ResponseBodyController {

    val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/response-body-string-v1")
    fun responseBodyStringV1(response: HttpServletResponse) {
        response.writer.write("ok")
    }

    @GetMapping("/response-body-string-v2")
    fun responseBodyStringV2(response: HttpServletResponse): ResponseEntity<String> {
       return ResponseEntity("ok", HttpStatus.OK)
    }

//    @ResponseBody
    @GetMapping("/response-body-string-v3")
    fun responseBodyStringV3(response: HttpServletResponse): String {
        return "ok"
    }

    @GetMapping("/response-body-json-v1")
    fun responseBodyJsonV1(): ResponseEntity<HelloData> {
        val helloData = HelloData()
        helloData.username = "userA"
        helloData.age = 20

        return ResponseEntity(helloData, HttpStatus.OK)
    }

    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
    @GetMapping("/response-body-json-v2")
    fun responseBodyJsonV2(): HelloData {
        val helloData = HelloData()
        helloData.username = "userA"
        helloData.age = 20
        return helloData
    }
}