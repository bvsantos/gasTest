package pt.unl.fct.tourtuga.gas.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import pt.unl.fct.tourtuga.gas.data.dao.DataInCallDAO

interface DataInCallRepository : JpaRepository<DataInCallDAO, Long> {

}