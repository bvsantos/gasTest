package pt.unl.fct.tourtuga.gas

import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import pt.unl.fct.tourtuga.gas.api.application.ApplicationDTO
import pt.unl.fct.tourtuga.gas.data.dao.ApplicationDAO
import pt.unl.fct.tourtuga.gas.data.repository.ApplicationRepository
import pt.unl.fct.tourtuga.gas.exceptions.HTTPNotFoundException
import pt.unl.fct.tourtuga.gas.service.ApplicationService
import java.time.LocalDate
import java.util.*
import java.util.logging.LogManager

@RunWith(SpringRunner::class)
@SpringBootTest
class ApplicationServiceTests {

    @Autowired
    lateinit var application:ApplicationService

    @MockBean
    lateinit var repo:ApplicationRepository

    companion object Contants{
        val app1 = ApplicationDTO(1L, 1L,1L,"app1",false, 0,
                LocalDate.now(), 0, LocalDate.now())
        val app1DAO = ApplicationDAO(1L, 1L,1L,"app1",false, 0,
                LocalDate.now(), 0, LocalDate.now())

        val app1update = ApplicationDTO(1L, 1L,1L,"app1updated",false, 0,
                LocalDate.now(), 0, LocalDate.now())
        val appsDAO = mutableListOf(app1DAO);
    }

    @Test
    fun `basic test on create and get application`(){

        Mockito.`when`(repo.save(Mockito.any(ApplicationDAO::class.java)))
                .then {
                    val app:ApplicationDAO = it.getArgument(0)
                    Assert.assertEquals(app.id, app1.id)
                    Assert.assertEquals(app.studentId, app1.studentId)
                    Assert.assertEquals(app.callId, app1.callId)
                    Assert.assertEquals(app.name, app1.name)
                    Assert.assertEquals(app.wasAccepted, app1.wasAccepted)
                    app
                }

        application.createApplication(app1)
    }



    @Test
    fun `basic test on getAllApplications`(){
        Mockito.`when`(repo.findAll()).thenReturn(appsDAO);
            Assert.assertEquals(application.getAllApplications(), appsDAO as List<ApplicationDAO> )
    }

    @Test
    fun `basic test on getApplicationsByID`(){
        Mockito.`when`(repo.findById(1L)).thenReturn(Optional.of(app1DAO));

        Assert.assertEquals(application.getApplicationById(1L), app1DAO)
    }

    @Test(expected = HTTPNotFoundException::class)
    fun `basic test exception on getApplicationsByID`(){
        Mockito.`when`(repo.findById(anyLong())).thenReturn(Optional.empty())

        application.getApplicationById(0L)
    }

    @Test(expected = HTTPNotFoundException::class)
    fun `basic test exception on getAllApplications`(){

        application.getAllApplications()
    }

    @Test(expected = HTTPNotFoundException::class)
    fun `basic test on update application exception`(){

        Mockito.`when`(repo.findById(anyLong())).thenReturn(Optional.empty())

        application.updateApplication(app1update)
    }

    @Test
    fun `basic test on update application`(){
        Mockito.`when`(repo.findById(1L)).thenReturn(Optional.of(app1DAO));

        application.createApplication(app1)
        application.updateApplication(app1update)
    }

    @Test(expected = HTTPNotFoundException::class)
    fun `basic test on delete`(){

        application.createApplication(app1)
        Mockito.`when`(repo.findById(anyLong())).thenReturn(Optional.empty())
        application.deleteApplication(app1.id)
        Assert.assertEquals(application.getApplicationById(app1.id), null)
    }

    @Test(expected = HTTPNotFoundException::class)
    fun `basic test on delete fail`(){
        application.createApplication(app1)
        application.deleteApplication(2L)
        Assert.assertEquals(application.getApplicationById(app1.id).id,app1.id )
    }



}