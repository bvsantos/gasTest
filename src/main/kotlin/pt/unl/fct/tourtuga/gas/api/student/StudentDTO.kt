package pt.unl.fct.tourtuga.gas.api.student

import pt.unl.fct.tourtuga.gas.data.dao.StudentDAO

data class StudentDTO(val id:Long, val institutionId:Long, val name:String, val email: String, val password:String, val address : String ){
    constructor(student: StudentDAO): this(student.id, student.institutionId, student.name, student.email, student.password, student.address)
}

data class StudentCVDTO(val id:Long, val cv:ByteArray)