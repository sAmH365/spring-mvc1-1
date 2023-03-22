package hello.springmvc.basic.request

import hello.springmvc.basic.HelloData
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

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

    @ResponseBody
    @RequestMapping("/request-param-v3")
    fun requestParamV3(
        @RequestParam username:String,
        @RequestParam age:Int
    ): String {
        log.info("username={}, age={}", username, age)

        return "ok"
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    fun requestParamV4(username:String, age:Int?): String {
        log.info("username={}, age={}", username, age)
        return "ok"
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    fun requestParamRequired(
        @RequestParam(required = true) username:String,
        @RequestParam(required = false) age:Int?
    ): String {
        log.info("username={}, age={}", username, age)

        return "ok"
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    fun requestParamDefault(
        @RequestParam(defaultValue = "guest") username:String,
        @RequestParam(defaultValue = "-1") age:Int
    ): String {
        log.info("username={}, age={}", username, age)

        return "ok"
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    fun requestParamMap(
        @RequestParam paramMap: Map<String, Any>
    ): String {
        log.info("paramMap={}", paramMap)

        return "ok"
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    fun modelAttributeV1(@ModelAttribute helloData: HelloData): String {
        log.info("username={}, age={}", helloData.username, helloData.age)

        return "ok"
    }


    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    fun modelAttributeV2(helloData: HelloData): String {
        log.info("username={}, age={}", helloData.username, helloData.age)

        return "ok"
    }
}