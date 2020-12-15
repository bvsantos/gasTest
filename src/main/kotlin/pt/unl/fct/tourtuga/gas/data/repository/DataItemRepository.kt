package pt.unl.fct.tourtuga.gas.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import pt.unl.fct.tourtuga.gas.data.dao.DataItemDAO
import pt.unl.fct.tourtuga.gas.data.dao.StudentDAO
import java.util.*

interface DataItemRepository : JpaRepository<DataItemDAO, Long>{
    fun findOneByDataType(datatype: String): Optional<DataItemDAO>
}