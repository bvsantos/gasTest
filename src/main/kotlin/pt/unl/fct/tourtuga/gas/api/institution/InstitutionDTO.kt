package pt.unl.fct.tourtuga.gas.api.institution

import pt.unl.fct.tourtuga.gas.data.dao.InstitutionDAO

data class InstitutionDTO(val id:Long, val name:String, val contact: Number){
    constructor(institution: InstitutionDAO): this( institution.id, institution.name, institution.contact)
}