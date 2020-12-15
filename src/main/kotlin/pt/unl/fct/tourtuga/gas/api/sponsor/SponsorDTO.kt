package pt.unl.fct.tourtuga.gas.api.sponsor

import pt.unl.fct.tourtuga.gas.data.dao.SponsorDAO
import pt.unl.fct.tourtuga.gas.data.dao.StudentDAO

data class SponsorDTO(val id: Long, val name: String, val contact: Number){
    constructor(sponsor: SponsorDAO): this(sponsor.id, sponsor.name, sponsor.contact)
}