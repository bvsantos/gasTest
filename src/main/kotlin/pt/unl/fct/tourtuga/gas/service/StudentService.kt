package pt.unl.fct.tourtuga.gas.service


import org.springframework.stereotype.Service
import pt.unl.fct.tourtuga.gas.api.student.StudentDTO
import pt.unl.fct.tourtuga.gas.data.dao.StudentDAO
import pt.unl.fct.tourtuga.gas.data.repository.StudentRepository

import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import pt.unl.fct.tourtuga.gas.api.student.StudentCVDTO
import pt.unl.fct.tourtuga.gas.exceptions.HTTPConflictException
import pt.unl.fct.tourtuga.gas.exceptions.HTTPNotFoundException

@Service
class StudentService(
        val students: StudentRepository
) {

    fun getAllStudents():List<StudentDTO>{
       var list = students.findAll().toList()
        return list.map {
            StudentDTO(it)
        }
    }

    fun getStudentById(id:Long) =  StudentDTO(students.findById(id).orElseThrow{
            HTTPNotFoundException("Student with id $id doesn't exist");
        })


    fun getStudentByEmail(email: String):StudentDTO{
        val stud = students.findOneByEmail(email)
        val response = JSONObject()
        return if(stud.isPresent){
            StudentDTO(stud.get())
        }else{
            response.put("error", "User doesnt exit").toString()
            throw HTTPNotFoundException("User was not found")
        }
    }

    fun deleteStudent(id: Long){
        var stud = students.findById(id).orElseThrow{
            throw HTTPNotFoundException("User was not found")
        }
        students.deleteById(id)
    }

    fun createStudent(student: StudentDTO) {
        if(!students.findOneByEmail(student.email).isPresent){
            val s:StudentDAO = StudentDAO(student);
            s.password = BCryptPasswordEncoder().encode(s.password);
            students.save(StudentDAO(student))
        }
        else
            throw HTTPConflictException("Student with this email is already registered!")
    }

    fun updateStudent(student: StudentDTO) {
        students.findById(student.id).orElseThrow {
            throw HTTPConflictException("Student with id $student.id was not found")
        }.let {
            it.update(student)
            students.save(it)
        }
    }

    //-------------------------------------StudentCV------------------------------//


    fun createStudentCV(studentCV: StudentCVDTO) {
        //TODO
    }

    fun updateStudentCV(id: Long, studentCV: StudentCVDTO){
        //TODO
    }

}