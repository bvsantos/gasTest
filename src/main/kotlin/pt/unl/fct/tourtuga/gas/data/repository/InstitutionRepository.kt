package pt.unl.fct.tourtuga.gas.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import pt.unl.fct.tourtuga.gas.data.dao.InstitutionDAO
import pt.unl.fct.tourtuga.gas.data.dao.StudentDAO
import java.util.*

interface InstitutionRepository : JpaRepository<InstitutionDAO, Long> {
    fun findOneByName(name: String): Optional<InstitutionDAO>
}