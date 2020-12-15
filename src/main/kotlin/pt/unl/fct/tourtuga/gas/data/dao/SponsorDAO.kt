package pt.unl.fct.tourtuga.gas.data.dao

import pt.unl.fct.tourtuga.gas.api.sponsor.SponsorDTO
import pt.unl.fct.tourtuga.gas.api.student.StudentDTO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class SponsorDAO(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)  var id:Long,
        var name:String,
        var contact:Number,
        var role:String

){

    //Constructor with data from client
    constructor(sponsor: SponsorDTO) : this(sponsor.id, sponsor.name, sponsor.contact, "sponsor") {
    }

    //Empty constructor
    constructor() : this(0,"",0, "sponsor")


    fun update(sponsor: SponsorDTO){
        this.name = sponsor.name;
        this.contact = sponsor.contact;
    }
}