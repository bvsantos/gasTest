package pt.unl.fct.tourtuga.gas.data.dao

import pt.unl.fct.tourtuga.gas.api.student.StudentDTO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Table


@Entity
class StudentDAO(
        id:Long,
        institutionId: Long,
        name:String,
        email:String,
        password:String,
        address:String

): GlobalUserDAO(id, institutionId,  name, email, password, address,"student") {

    //Constructor with data from client
    constructor(student: StudentDTO) : this(student.id, student.institutionId, student.name, student.email, student.password, student.address) {
    }

    //Empty constructor
    constructor() : this(0,0,"","","","")

    fun update(student: StudentDTO){
        this.institutionId = student.institutionId;
        this.name = student.name;
        this.email = student.email;
        this.password = student.password;
        this.address = student.address;
    }

}