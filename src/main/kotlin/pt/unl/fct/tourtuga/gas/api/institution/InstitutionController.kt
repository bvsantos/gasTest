package pt.unl.fct.tourtuga.gas.api.institution

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import pt.unl.fct.tourtuga.gas.api.institution.InstitutionDTO
import pt.unl.fct.tourtuga.gas.service.InstitutionService

@RestController
class InstitutionController(val institutions: InstitutionService): Institution {

    override fun getAllInstitutions() = institutions.getAllInstitutions()

    override fun getInstitutionById(@PathVariable id:Long) = institutions.getInstitutionById(id)

    override fun createInstitution(@RequestBody institution: InstitutionDTO) = institutions.createInstitution(institution)

    override fun updateInstitution(id: Long, sponsor: InstitutionDTO) = institutions.updateInstitution(sponsor)

    override fun deleteInstitution(id: Long) = institutions.deleteInstitution(id)

}