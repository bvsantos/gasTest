package pt.unl.fct.tourtuga.gas.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import pt.unl.fct.tourtuga.gas.data.dao.PanelDAO

interface PanelRepository : JpaRepository<PanelDAO, Long> {
}