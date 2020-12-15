package pt.unl.fct.tourtuga.gas

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GasApplication

fun main(args: Array<String>) {
    //SpringApplication.run(GasApplication.class, args);
    runApplication<GasApplication>(*args)
}
