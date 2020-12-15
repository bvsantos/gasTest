package pt.unl.fct.tourtuga.gas.api.application

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*
import pt.unl.fct.tourtuga.gas.data.dao.ApplicationDAO
import pt.unl.fct.tourtuga.gas.data.dao.ApplicationReviewDAO
import pt.unl.fct.tourtuga.gas.data.dao.DataInApplicationDAO

@Api(value = "Application API", description = "Handles Application related requests")
@RequestMapping("/applications")
interface Application {

    //-------------------------- Application ------------------------------------//

    @ApiOperation(value = "View a list of all applications", response = List::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "There are no applications created")])
    @GetMapping("/")
    fun getAllApplications(): List<ApplicationDAO>

    @ApiOperation(value = "View a list of all applications", response = List::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "There are no applications created")])
    @GetMapping("/taste")
    fun test(): String

    @ApiOperation(value = "View details of a application", response = ApplicationDAO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Application doesnt exist")])
    @GetMapping("/{id}")
    fun getApplicationById(@PathVariable id: Long): ApplicationDAO

    @ApiOperation(value = "Get all Applications from a Student", response = List::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Students has no Applications")])
    @GetMapping("/student/{id}")
    fun getApplicationsByStudentId(@PathVariable id: Long): List<ApplicationDAO>

    @ApiOperation(value = "Get all Applications from a Reviewer", response = List::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Reviewer has no Applications")])
    @GetMapping("/reviewers/{id}")
    fun getApplicationsByReviewId(@PathVariable id: Long) : List<ApplicationDAO>

    @ApiOperation(value = "Create an application")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization")])
    @PostMapping("/")
    fun createApplication(@RequestBody application: ApplicationDTO)

    @ApiOperation(value = "Update an application")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Application doesnt exist")])
    @PutMapping("/{id}")
    fun updateApplication(@PathVariable id: Long, @RequestBody application: ApplicationDTO)

    @ApiOperation(value = "Delete an application")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Application doesnt exist")])
    @DeleteMapping("/{id}")
    fun deleteApplication(@PathVariable id: Long)

    //--------------------------------   ApplicationData ---------------------------//

    @ApiOperation(value = "Get data items from a application", response = List::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Application doesnt exist")])
    @GetMapping("/data/{id}")
    fun getAllDataFromApplicationById(@PathVariable id: Long): List<DataInApplicationDAO>

    @ApiOperation(value = "Add data to the application")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Application doesnt exist")])
    @PostMapping("/data")
    fun addDataToApplication(@RequestBody data: DataInApplicationDTO)

    @ApiOperation(value = "Delete a data item from the application")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Application doesnt exist")])
    @DeleteMapping("/data/{id}")
    fun deleteDataFromApplication(@PathVariable id: Long)

    //------------------------------------------------Review_Application-----------------------------------------//

    @ApiOperation(value = "Review an application")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Application doesnt exist")])
    @PostMapping("/applicationReview")
    fun applicationReview(@RequestBody review: ApplicationReviewDTO)

    @ApiOperation(value = "Get application reviewed by reviewer", response = List::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Application doesnt exist")])
    @GetMapping("/applicationReview/{id}")
    fun getApplicationReviewedByReviewerId(@PathVariable id:Long) : List<ApplicationReviewDAO>

    @ApiOperation(value = "Get reviews from Application", response = List::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Application doesnt exist")])
    @GetMapping("/applicationReview/review/{id}")
    fun getReviewsByApplicationId(@PathVariable id:Long) : List<ApplicationReviewDAO>


}