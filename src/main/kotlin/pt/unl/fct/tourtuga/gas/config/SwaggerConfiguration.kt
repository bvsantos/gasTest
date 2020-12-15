package pt.unl.fct.tourtuga.gas.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Bean
    fun api(): Docket =
            Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("pt.unl.fct.tourtuga.gas"))
                    .paths(PathSelectors.any())
                    .build().apiInfo(apiEndPointsInfo())

    fun apiEndPointsInfo(): ApiInfo =
            ApiInfoBuilder()
                    .title("Spring Boot REST Api GAS Aplication")
                    .description("Cadeira de IADI 2020")
                    .license("Apache 2.0")
                    .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                    .version("1.0.0")
                    .build()
}