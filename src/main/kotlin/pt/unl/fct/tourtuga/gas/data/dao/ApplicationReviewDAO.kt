package pt.unl.fct.tourtuga.gas.data.dao

import pt.unl.fct.tourtuga.gas.api.application.ApplicationDTO
import pt.unl.fct.tourtuga.gas.api.application.ApplicationReviewDTO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class ApplicationReviewDAO(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id:Long,
        var applicationId:Long,
        var reviewerId:Long,
        var review:String
) {

    constructor(applicationReview: ApplicationReviewDTO) : this(applicationReview.id, applicationReview.applicationId, applicationReview.reviewerId, applicationReview.review) {
    }

    //Empty constructor
    constructor() : this(0,0,0,"")
}