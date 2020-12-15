package pt.unl.fct.tourtuga.gas.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import pt.unl.fct.tourtuga.gas.data.dao.SponsorDAO
import pt.unl.fct.tourtuga.gas.data.dao.StudentDAO
import java.util.*

interface SponsorRepository : JpaRepository<SponsorDAO, Long> {
    fun findOneByName(name: String): Optional<SponsorDAO>
}