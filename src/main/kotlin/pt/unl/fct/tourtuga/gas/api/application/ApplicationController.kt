package pt.unl.fct.tourtuga.gas.api.application

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import pt.unl.fct.tourtuga.gas.data.repository.ApplicationRepository
import pt.unl.fct.tourtuga.gas.service.ApplicationService

@RestController
class ApplicationController(val applications: ApplicationService) : Application {

    override fun getAllApplications() = applications.getAllApplications()

    override fun test(): String {
        return "ok";
    }

    override fun getApplicationById(@PathVariable id:Long) = applications.getApplicationById(id)

    override fun getApplicationsByStudentId(@PathVariable id: Long) = applications.getApplicationsByStudentId(id)

    override fun getApplicationsByReviewId(@PathVariable id : Long) = applications.getApplicationsByReviewId(id);

    override fun getApplicationReviewedByReviewerId(@PathVariable id:Long) = applications.getApplicationReviewedByReviewerId(id)

    override fun getReviewsByApplicationId(@PathVariable id:Long) = applications.getApplicationReviewedByReviewerId(id)

    override fun createApplication(@RequestBody application: ApplicationDTO) = applications.createApplication(application)

    override fun updateApplication(id: Long, application: ApplicationDTO) = applications.updateApplication(application)

    override fun deleteApplication(id: Long) = applications.deleteApplication(id)

    override fun addDataToApplication(@RequestBody data: DataInApplicationDTO) = applications.addDataToApplication(data)

    override fun deleteDataFromApplication(@PathVariable id: Long) = applications.deleteDataFromApplication(id)

    override fun getAllDataFromApplicationById(@PathVariable id: Long) = applications.getAllDataInApplicationById(id)

    override fun applicationReview(review: ApplicationReviewDTO) = applications.applicationReview(review)

}