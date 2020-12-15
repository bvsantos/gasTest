package pt.unl.fct.tourtuga.gas.data.dao

import pt.unl.fct.tourtuga.gas.api.application.DataInApplicationDTO
import pt.unl.fct.tourtuga.gas.api.institution.InstitutionDTO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class InstitutionDAO(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id:Long,
        var name:String,
        var contact:Number
){

    constructor(institution: InstitutionDTO) : this(institution.id, institution.name, institution.contact) {
    }

    //Empty constructor
    constructor() : this(0,"",0)

    fun update(institution: InstitutionDTO){
        this.name = institution.name;
        this.contact = institution.contact;
    }

}