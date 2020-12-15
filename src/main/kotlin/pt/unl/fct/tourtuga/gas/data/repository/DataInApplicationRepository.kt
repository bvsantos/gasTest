package pt.unl.fct.tourtuga.gas.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import pt.unl.fct.tourtuga.gas.data.dao.DataInApplicationDAO
import java.util.*

interface DataInApplicationRepository : JpaRepository<DataInApplicationDAO, Long> {

    fun findByDataItemId(dataId: Long): Optional<DataInApplicationDAO>

    @Query("SELECT data FROM DataInApplicationDAO data "
            + "INNER JOIN ApplicationDAO app ON app.id = data.applicationId WHERE app.id = :applicationId")
    fun findAllDataApplicationByApplicationId(applicationId:Long) : List<DataInApplicationDAO>
}