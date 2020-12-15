package pt.unl.fct.tourtuga.gas.data.dao

import pt.unl.fct.tourtuga.gas.api.reviewer.ReviewerDTO
import pt.unl.fct.tourtuga.gas.api.student.StudentDTO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

// EXTEND GLOBALDAO

@Entity
class ReviewerDAO(
        id:Long,
        institutionId:Long,
        name:String,
        email:String,
        password:String,
        address:String
): GlobalUserDAO(id, institutionId,  name, email, password, address,"reviewer") {

    constructor(reviewer: ReviewerDTO) : this(reviewer.id, reviewer.institutionId, reviewer.name, reviewer.email, reviewer.password, "") {
    }

    //Empty constructor
    constructor() : this(0,0,"","","","")

    fun update(reviewer: ReviewerDTO){
        this.institutionId = reviewer.institutionId;
        this.name = reviewer.name;
        this.email = reviewer.email;
        this.password = reviewer.password;
    }

}