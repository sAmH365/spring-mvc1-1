package hello.springmvc.basic.requestmapping

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MappingController {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @RequestMapping("/hello-basic")
    fun helloBasic():String {
        log.info("hello basic")
        return "ok"
    }

    @GetMapping("/hello-basic/{userId}")
    fun mappingPath(@PathVariable("userId") data: String): String {
        log.info("mappingPath userId={}", data)
        return "ok"
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode"
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug"
     * params={"mode=debug", "data=good"}
     */
    @GetMapping(value = ["/mapping-param"], params = ["mode!=a"])
    fun mappingParam(): String {
        log.info("mappingParam")
        return "ok"
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug"
     */
    @GetMapping(value=["/mapping-header"], headers = ["mode=debug"])
    fun mappingHeader(): String {
        log.info("mappingHeader")
        return "ok"
    }


//      Content-Type 헤더 기반 추가 매핑
//      consumes="application/json",
//      consumes="!application/json"
//      consumes="application/*"
//      consumes="*\/*"
//      MediaType.APPLICATION_JSON_VALUE

    @GetMapping(value=["/mapping-consume"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun mappingConsumes(): String {
        log.info("mappingConsumes")
        return "ok"
    }

//      Accept 헤더 기반 Media Type
//      produces="text/html",
//      produces="!text/html"
//      produces="text/*"
//      produces="*\/*"

    @GetMapping(value=["/mapping-produce"], produces = ["text/html"])
    fun mappingProduces(): String {
        log.info("mappingProduces")
        return "ok"
    }
}