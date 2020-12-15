package pt.unl.fct.tourtuga.gas.data.dao

import pt.unl.fct.tourtuga.gas.api.application.DataInApplicationDTO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class DataInApplicationDAO(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id:Long,
        var applicationId:Long,
        var dataItemId:Long,
        var data: ByteArray
){

    constructor(dataInApplication: DataInApplicationDTO) : this(dataInApplication.id, dataInApplication.applicationID, dataInApplication.dataID, dataInApplication.data) {
    }

    //Empty constructor
    constructor() : this(0,0,0,ByteArray(0))

}