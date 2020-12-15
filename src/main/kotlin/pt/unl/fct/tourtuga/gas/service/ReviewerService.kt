package pt.unl.fct.tourtuga.gas.service

import org.springframework.stereotype.Service
import pt.unl.fct.tourtuga.gas.api.reviewer.ReviewerDTO
import pt.unl.fct.tourtuga.gas.api.student.StudentDTO
import pt.unl.fct.tourtuga.gas.data.dao.ReviewerDAO
import pt.unl.fct.tourtuga.gas.data.dao.StudentDAO
import pt.unl.fct.tourtuga.gas.data.repository.ReviewerRepository
import pt.unl.fct.tourtuga.gas.exceptions.HTTPConflictException
import pt.unl.fct.tourtuga.gas.exceptions.HTTPNotFoundException

@Service
class ReviewerService(
        val reviewers: ReviewerRepository
) {

    fun getAllReviewers():List<ReviewerDTO>{
        var list = reviewers.findAll().toList()
        return list.map {
            ReviewerDTO(it)
        }
    }

    fun getReviewerById(id:Long) = ReviewerDTO(reviewers.findById(id).orElseThrow{
        HTTPNotFoundException("Reviewer with id $id doesn't exist");
    })

    fun createReviewer(reviewer: ReviewerDTO) {
        if(!reviewers.findOneByEmail(reviewer.email).isPresent){
            reviewers.save(ReviewerDAO(reviewer))
        }
        else
            throw HTTPConflictException("Reviewer with this email is already registered!")
    }

    fun updateReviewer(reviewer: ReviewerDTO) {
        reviewers.findById(reviewer.id).orElseThrow {
            throw HTTPConflictException("Student with id $reviewer.id was not found")
        }.let {
            it.update(reviewer)
            reviewers.save(it)
        }
    }

    fun deleteReviewer(id: Long){
        var stud = reviewers.findById(id).orElseThrow{
            throw HTTPNotFoundException("User was not found")
        }
        reviewers.deleteById(id)
    }
}