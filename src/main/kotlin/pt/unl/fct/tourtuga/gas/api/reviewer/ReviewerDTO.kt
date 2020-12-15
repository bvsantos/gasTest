package pt.unl.fct.tourtuga.gas.api.reviewer

import pt.unl.fct.tourtuga.gas.data.dao.ReviewerDAO

data class ReviewerDTO(val id: Long, val institutionId: Long, val name: String, val email: String, val password: String){
    constructor(reviewer: ReviewerDAO): this(reviewer.id, reviewer.institutionId, reviewer.name, reviewer.email, reviewer.password)
}