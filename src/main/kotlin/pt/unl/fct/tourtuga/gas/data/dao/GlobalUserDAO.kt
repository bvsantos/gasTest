package pt.unl.fct.tourtuga.gas.data.dao

import pt.unl.fct.tourtuga.gas.api.student.StudentDTO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
abstract class GlobalUserDAO(@Id @GeneratedValue(strategy = GenerationType.SEQUENCE) open var id:Long,
                             open var institutionId:Long,
                             open var name:String,
                             open var email:String,
                             open var password: String,
                             open var address: String,
                             open var role: String) {
    constructor() : this(0,0, "","","","", "")

}