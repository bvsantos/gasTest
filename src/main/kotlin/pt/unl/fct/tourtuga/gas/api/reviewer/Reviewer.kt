package pt.unl.fct.tourtuga.gas.api.reviewer

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*
import pt.unl.fct.tourtuga.gas.data.dao.ReviewerDAO

@Api(value = "Reviewer API", description = "Handles Reviewer related requests")
@RequestMapping("/reviewers")
interface Reviewer {

    @ApiOperation(value = "View a list of all reviewers", response = List::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "There are no reviewers created")])
    @GetMapping("/")
    fun getAllReviewers(): List<ReviewerDTO>

    @ApiOperation(value = "View details of a reviewer", response = ReviewerDAO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Reviewer doesnt exist")])
    @GetMapping("/{id}")
    fun getReviewerById(@PathVariable id: Long): ReviewerDTO

    @ApiOperation(value = "Create a reviewer")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization")])
    @PostMapping("/")
    fun createReviewer(@RequestBody reviewer: ReviewerDTO)

    @ApiOperation(value = "Update a reviewer")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Reviewer doesnt exist")])
    @PutMapping("/{id}")
    fun updateReviewer(@PathVariable id: Long, @RequestBody reviewer: ReviewerDTO)

    @ApiOperation(value = "Delete a reviewer")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Reviewer doesnt exist")])
    @DeleteMapping("/{id}")
    fun deleteReviewer(@PathVariable id: Long)

}