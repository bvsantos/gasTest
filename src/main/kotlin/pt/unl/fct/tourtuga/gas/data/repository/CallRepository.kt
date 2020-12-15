package pt.unl.fct.tourtuga.gas.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import pt.unl.fct.tourtuga.gas.data.dao.ApplicationDAO
import pt.unl.fct.tourtuga.gas.data.dao.CallDAO
import java.time.LocalDate
import java.util.*

interface CallRepository : JpaRepository<CallDAO, Long> {

    @Query("SELECT call FROM CallDAO call " +
            "INNER JOIN ApplicationDAO app ON call.id = app.callId WHERE app.id = :applicationId")
    fun findByApplicationId(applicationId: Long): Optional<CallDAO>

    @Query("SELECT call FROM CallDAO call WHERE :currDate BETWEEN call.openingDate AND call.closingDate")
    fun findAllActiveCall(currDate: LocalDate): List<CallDAO>
}