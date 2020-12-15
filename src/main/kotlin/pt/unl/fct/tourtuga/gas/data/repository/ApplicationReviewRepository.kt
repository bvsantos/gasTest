package pt.unl.fct.tourtuga.gas.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import pt.unl.fct.tourtuga.gas.data.dao.ApplicationDAO
import pt.unl.fct.tourtuga.gas.data.dao.ApplicationReviewDAO
import java.util.*

interface ApplicationReviewRepository: JpaRepository<ApplicationReviewDAO, Long> {

    @Query("SELECT student.institutionId, reviewer.institutionId, call.closingDate FROM ApplicationReviewDAO  appreview " +
        "INNER JOIN ApplicationDAO app ON app.id = appreview.applicationId INNER JOIN ReviewerDAO reviewer ON appreview.reviewerId = reviewer.id INNER JOIN StudentDAO  student ON app.studentId = student.id INNER JOIN CallDAO call ON app.callId = call.id WHERE app.id = :applicationId AND reviewer.id = :reviewerId")
    fun findStudentIdAndReviewInfo(applicationId:Long, reviewerId:Long) : List<String>

    fun findAllByApplicationId(id: Long) : List<ApplicationReviewDAO>

    fun findAllByReviewerId(id: Long) : List<ApplicationReviewDAO>

}