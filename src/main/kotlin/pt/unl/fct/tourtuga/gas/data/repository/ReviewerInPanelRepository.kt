package pt.unl.fct.tourtuga.gas.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import pt.unl.fct.tourtuga.gas.api.reviewer.ReviewerDTO
import pt.unl.fct.tourtuga.gas.data.dao.ReviewerInPanelDAO

interface ReviewerInPanelRepository : JpaRepository<ReviewerInPanelDAO, Long> {

    fun findAllByPanelId(panelId: Long) : List<ReviewerInPanelDAO>
}