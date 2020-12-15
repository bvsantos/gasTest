package pt.unl.fct.tourtuga.gas.api.sponsor

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*
import pt.unl.fct.tourtuga.gas.data.dao.SponsorDAO

@Api(value = "Application API", description = "Handles Sponsor related requests")
@RequestMapping("/sponsors")
interface Sponsor {

    @ApiOperation(value = "View a list of all sponsors", response = List::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "There are no sponsors created")])
    @GetMapping("/")
    fun getAllSponsors(): List<SponsorDTO>

    @ApiOperation(value = "View details of a sponsor", response = SponsorDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Sponsor doesnt exist")])
    @GetMapping("/{id}")
    fun getSponsorById(@PathVariable id: Long): SponsorDTO

    @ApiOperation(value = "Create a sponsor")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization")])
    @PostMapping("/")
    fun createSponsor(@RequestBody sponsor: SponsorDTO)

    @ApiOperation(value = "Update a sponsor")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Sponsor doesnt exist")])
    @PutMapping("/{id}")
    fun updateSponsor(@PathVariable id: Long, @RequestBody sponsor: SponsorDTO)

    @ApiOperation(value = "Delete a sponsor")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Sponsor doesnt exist")])
    @DeleteMapping("/{id}")
    fun deleteSponsor(@PathVariable id: Long)

}