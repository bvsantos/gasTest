package pt.unl.fct.tourtuga.gas.service

import org.springframework.stereotype.Service
import pt.unl.fct.tourtuga.gas.api.institution.InstitutionDTO
import pt.unl.fct.tourtuga.gas.api.student.StudentDTO
import pt.unl.fct.tourtuga.gas.data.dao.InstitutionDAO
import pt.unl.fct.tourtuga.gas.data.dao.StudentDAO
import pt.unl.fct.tourtuga.gas.data.repository.InstitutionRepository
import pt.unl.fct.tourtuga.gas.exceptions.HTTPConflictException
import pt.unl.fct.tourtuga.gas.exceptions.HTTPNotFoundException

@Service
class InstitutionService(
        val institutions: InstitutionRepository
) {

    fun getAllInstitutions():List<InstitutionDTO>{
        var list = institutions.findAll().toList()
        return list.map {
            InstitutionDTO(it)
        }
    }

    fun getInstitutionById(id:Long) =  InstitutionDTO(institutions.findById(id).orElseThrow{
        HTTPNotFoundException("Institution with id $id doesn't exist");
    })

    fun createInstitution(institution: InstitutionDTO) {
        if(!institutions.findOneByName(institution.name).isPresent){
            institutions.save(InstitutionDAO(institution))
        }
        else
            throw HTTPConflictException("Institution with this email is already registered!")
    }

    fun updateInstitution(institution: InstitutionDTO) {
        institutions.findById(institution.id).orElseThrow {
            throw HTTPConflictException("Institution with id $institution.id was not found")
        }.let {
            it.update(institution)
            institutions.save(it)
        }
    }

    fun deleteInstitution(id: Long){
        var inst = institutions.findById(id).orElseThrow{
            throw HTTPNotFoundException("Institution was not found")
        }
        institutions.deleteById(id)
    }

}