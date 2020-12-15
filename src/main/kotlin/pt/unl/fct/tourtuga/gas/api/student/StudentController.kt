package pt.unl.fct.tourtuga.gas.api.student

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import pt.unl.fct.tourtuga.gas.service.StudentService


@RestController
class StudentController(val students: StudentService): Student {


    override fun getAllStudents() = students.getAllStudents()

    override fun getStudentById(@PathVariable id:Long) = students.getStudentById(id)

    override fun getStudentByEmail(email: String) = students.getStudentByEmail(email)

    override fun createStudent(@RequestBody student: StudentDTO) = students.createStudent(student)

    override fun updateStudent(@PathVariable id: Long, student: StudentDTO) = students.updateStudent(student)

    override fun deleteStudent(@PathVariable id: Long) = students.deleteStudent(id)

    //------------------------------------StudentCV---------------------------------------------//

    override fun createStudentCV(studentCV: StudentCVDTO) = students.createStudentCV(studentCV)

    override fun updateStudentCV(id: Long, studentCV: StudentCVDTO) = students.updateStudentCV(id, studentCV)


}