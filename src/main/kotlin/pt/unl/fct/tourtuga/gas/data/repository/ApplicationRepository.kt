package pt.unl.fct.tourtuga.gas.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import pt.unl.fct.tourtuga.gas.data.dao.ApplicationDAO
import pt.unl.fct.tourtuga.gas.data.dao.StudentDAO

import java.util.*

interface ApplicationRepository : JpaRepository<ApplicationDAO, Long> {

    fun findAllByStudentId(studentId: Long): List<ApplicationDAO>

    @Query("SELECT a FROM ApplicationDAO  a INNER JOIN ApplicationReviewDAO ar ON a.id = ar.applicationId " +
            "INNER JOIN ReviewerDAO r ON ar.reviewerId = r.id WHERE r.id = :reviewerId")
    fun findAllByReviewerId(reviewerId: Long): List<ApplicationDAO>

    @Query("SELECT s FROM StudentDAO s " +
            "INNER JOIN ApplicationDAO app ON app.studentId = s.id WHERE app.id = :applicationId")
    fun getStudentsInstitutionByApplicationId(applicationId: Long): Optional<StudentDAO>

}