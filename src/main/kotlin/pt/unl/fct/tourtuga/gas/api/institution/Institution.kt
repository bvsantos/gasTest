package pt.unl.fct.tourtuga.gas.api.institution

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*
import pt.unl.fct.tourtuga.gas.data.dao.InstitutionDAO

@Api(value = "Application API", description = "Handles Institution related requests")
@RequestMapping("/institutions")
interface Institution {

    @ApiOperation(value = "View a list of all institutions", response = List::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "There are no institutions created")])
    @GetMapping("/")
    fun getAllInstitutions(): List<InstitutionDTO>

    @ApiOperation(value = "View details of a institution", response = InstitutionDAO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Institution doesnt exist")])
    @GetMapping("/{id}")
    fun getInstitutionById(@PathVariable id: Long): InstitutionDTO

    @ApiOperation(value = "Create an institution")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization")])
    @PostMapping("/")
    fun createInstitution(@RequestBody institution: InstitutionDTO)

    @ApiOperation(value = "Update an institution")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Institution doesnt exist")])
    @PutMapping("/{id}")
    fun updateInstitution(@PathVariable id: Long, @RequestBody institution: InstitutionDTO)

    @ApiOperation(value = "Delete an institution")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Institution doesnt exist")])
    @DeleteMapping("/{id}")
    fun deleteInstitution(@PathVariable id: Long)
}