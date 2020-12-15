package pt.unl.fct.tourtuga.gas.data.dao

import pt.unl.fct.tourtuga.gas.api.application.ApplicationDTO
import pt.unl.fct.tourtuga.gas.api.call.PanelDTO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class PanelDAO(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id:Long,
        var name:String
) {

    constructor(panel: PanelDTO) : this(panel.id, panel.name) {
    }

    //Empty constructor
    constructor() : this(0,"")

    fun update(panel: PanelDTO) {
        this.name = panel.name;
    }
}