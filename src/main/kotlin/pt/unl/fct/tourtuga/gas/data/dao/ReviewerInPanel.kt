package pt.unl.fct.tourtuga.gas.data.dao

import pt.unl.fct.tourtuga.gas.api.call.ReviewerInPanelDTO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class ReviewerInPanelDAO(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id:Long,
        var panelId:Long,
        var reviewerId:Long,
        var isChair:Boolean
){

    constructor(reviewerInPanel: ReviewerInPanelDTO) : this(reviewerInPanel.id, reviewerInPanel.panelId, reviewerInPanel.reviewerId, reviewerInPanel.isChair) {
    }

    //Empty constructor
    constructor() : this(0,0,0,false)
}