package pt.unl.fct.tourtuga.gas.api.student

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*
import pt.unl.fct.tourtuga.gas.data.dao.StudentDAO

@Api(value = "Student API", description = "Handles Student related requests")
@RequestMapping("/students")
interface Student {

    @ApiOperation(value = "View a list of all students", response = List::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization")])
    @GetMapping("/")
    fun getAllStudents(): List<StudentDTO>

    @ApiOperation(value = "Return Student by Email", response = StudentDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Student doesnt exist")])
    @GetMapping("/email")
    fun getStudentByEmail(@RequestParam email: String): StudentDTO

    @ApiOperation(value = "View details of a student", response = StudentDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization")])
    @GetMapping("/{id}")
    fun getStudentById(@PathVariable id: Long): StudentDTO

    @ApiOperation(value = "Register Student")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization")])
    @PostMapping("/")
    fun createStudent(@RequestBody student: StudentDTO)

    @ApiOperation(value = "Update a student")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Reviewer doesnt exist")])
    @PutMapping("/{id}")
    fun updateStudent(@PathVariable id: Long, @RequestBody student: StudentDTO)

    @ApiOperation(value = "Delete a student")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Reviewer doesnt exist")])
    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id: Long)

    //-------------------------------------------StudentCV--------------------------------------//

    @ApiOperation(value = "Create a StudentCV")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization")])
    @PostMapping("/studentCV/")
    fun createStudentCV(@RequestBody studentCV: StudentCVDTO)

    @ApiOperation(value = "Update a StudentCV")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Student doesnt exist")])
    @PutMapping("/studentCV/{id}")
    fun updateStudentCV(@PathVariable id: Long, @RequestBody studentCV: StudentCVDTO)

}