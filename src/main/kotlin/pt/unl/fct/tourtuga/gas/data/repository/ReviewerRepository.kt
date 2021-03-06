package pt.unl.fct.tourtuga.gas.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import pt.unl.fct.tourtuga.gas.data.dao.ReviewerDAO
import pt.unl.fct.tourtuga.gas.data.dao.StudentDAO
import java.util.*

interface ReviewerRepository : JpaRepository<ReviewerDAO, Long> {
    fun findOneByEmail(email: String): Optional<ReviewerDAO>
}