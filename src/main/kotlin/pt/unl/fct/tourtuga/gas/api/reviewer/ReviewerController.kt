package pt.unl.fct.tourtuga.gas.api.reviewer

import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import pt.unl.fct.tourtuga.gas.service.ReviewerService

@RestController
class ReviewerController(val reviewers: ReviewerService) : Reviewer {

    override fun getAllReviewers() = reviewers.getAllReviewers()

    override fun getReviewerById(@PathVariable id: Long) = reviewers.getReviewerById(id)

    override fun createReviewer(@RequestBody reviewer: ReviewerDTO) = reviewers.createReviewer(reviewer)

    override fun updateReviewer(id: Long, reviewer: ReviewerDTO) = reviewers.updateReviewer(reviewer)

    override fun deleteReviewer(id: Long) = reviewers.deleteReviewer(id)
}