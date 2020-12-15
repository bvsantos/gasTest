package pt.unl.fct.tourtuga.gas

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.hamcrest.Matchers.hasSize
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import pt.unl.fct.tourtuga.gas.api.application.ApplicationDTO
import pt.unl.fct.tourtuga.gas.data.dao.ApplicationDAO
import pt.unl.fct.tourtuga.gas.exceptions.HTTPNotFoundException
import pt.unl.fct.tourtuga.gas.service.ApplicationService
import java.time.LocalDate
import java.util.*
import javax.lang.model.type.NullType
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class ApplicationControllerTests {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var apps:ApplicationService

    companion object {
        // To avoid all annotations JsonProperties in data classes
        // see: https://github.com/FasterXML/jackson-module-kotlin
        // see: https://discuss.kotlinlang.org/t/data-class-and-jackson-annotation-conflict/397/6
        val mapper = ObjectMapper().registerModule(KotlinModule())

        val app1 = ApplicationDTO(1L, 1L, 1L, "app1", false, 0,
                LocalDate.now(), 0, LocalDate.now())
        val app1DAO = ApplicationDAO(1L, 1L, 1L, "app1", false, 0,
                LocalDate.now(), 0, LocalDate.now())

        val app2 = ApplicationDTO(3L, 2L, 2L, "app2", false, 0,
                LocalDate.now(), 0, LocalDate.now())
        val app2DAO = ApplicationDAO(1L, 2L, 2L, "app2", false, 0,
                LocalDate.now(), 0, LocalDate.now())

        val app1update = ApplicationDTO(1L, 1L, 1L, "app1updated", false, 0,
                LocalDate.now(), 0, LocalDate.now())
        val appsDAO = listOf(app1DAO);

        val appsDTO = appsDAO.map { ApplicationDTO(it.id, it.studentId, it.callId, it.name, it.wasAccepted,
                it.createdBy, it.createdAt, it.updatedBy, it.updatedAt)}

        val appsURL = "/applications"
    }

    @Test
    fun `Test GET all apps`() {
        Mockito.`when`(apps.getAllApplications()).thenReturn(appsDAO)

         mvc.perform(get(appsURL + "/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize<Any>(appsDAO.size)))

    }

    @Test
    fun `Test Get One App`() {
        Mockito.`when`(apps.getApplicationById(1L)).thenReturn(app1DAO)

        val result = mvc.perform(get("$appsURL/1"))
                .andExpect(status().isOk)
                .andReturn()

        val responseString = result.response.contentAsString
        val responseDTO = mapper.readValue<ApplicationDTO>(responseString)
        assertThat(responseDTO, equalTo(appsDTO[0]))
    }

    @Test
    fun `Test GET App by ID (Not Found)`() {
        Mockito.`when`(apps.getApplicationById(2L)).thenThrow(HTTPNotFoundException("no foubn"))

        mvc.perform(get("$appsURL/2"))
                .andExpect(status().is4xxClientError)
    }

    fun <T>nonNullAny(t: Class<T>):T = Mockito.any(t)


    @Test
    fun `Test POST Create App`() {
        val app2 = ApplicationDTO(2L, 2L, 2L, "app2", false, 0,
                LocalDate.now(), 0, LocalDate.now())

        val app2JSON = mapper.writeValueAsString(app2)

        Mockito.`when`(apps.createApplication(nonNullAny(ApplicationDTO::class.java)))
                .then { assertThat(it.getArgument(0), equalTo(app2)); it.getArgument(0) }

        mvc.perform(post(appsURL + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(app2JSON))
                .andExpect(status().isOk)
    }

    @Test
    fun `Test PUT update App`() {


        val app2JSON = mapper.writeValueAsString(app2)

        Mockito.`when`(apps.updateApplication(nonNullAny(ApplicationDTO::class.java)))
                .then { assertThat(it.getArgument(0), equalTo(app2)); it.getArgument(0) }

        mvc.perform(put(appsURL + "/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(app2JSON))
                .andExpect(status().isOk)
    }

    @Test
    fun `Test PUT update exception App`() {


        val app2JSON = mapper.writeValueAsString(app2)


        Mockito.`when`(apps.getApplicationById(app1.id)).thenThrow(HTTPNotFoundException("no foubn"))


        // Mockito.`when`(apps.getApplicationById(app1.id)).thenReturn(app1DAO)

        Mockito.`when`(apps.updateApplication(nonNullAny(ApplicationDTO::class.java)))
                .thenThrow(HTTPNotFoundException("no foubn"))

        mvc.perform(put(appsURL + "/${app1.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(app2JSON))
                .andExpect(status().is4xxClientError)
    }



   // Not working because its a void method
  /* @Test
    fun `Test DEL delete App`() {
        val obj = Unit
        Mockito.doNothing().`when`(apps.deleteApplication(app1.id))


        mvc.perform(delete(appsURL + "/${app1.id}"))
                .andExpect(status().isOk)
    }*/

    @Test
    fun `Test DEL exception delete App`() {


        Mockito.`when`(apps.deleteApplication(app1.id)).thenThrow(HTTPNotFoundException("Not found"))


        mvc.perform(delete(appsURL + "/${app1.id}"))
                .andExpect(status().is4xxClientError)
    }

}