package pt.unl.fct.tourtuga.gas.data.dao

import pt.unl.fct.tourtuga.gas.api.call.DataInCallDTO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class DataInCallDAO(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id:Long,
        var callId:Long,
        var dataItemId:Long,
        var isMandatory:Boolean
) {

    constructor(dataInCall: DataInCallDTO) : this(dataInCall.id, dataInCall.callId, dataInCall.dateItemId, dataInCall.isMandatory) {
    }

    //Empty constructor
    constructor() : this(0,0,0,false)
}