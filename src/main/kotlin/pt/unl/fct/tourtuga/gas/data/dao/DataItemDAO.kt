package pt.unl.fct.tourtuga.gas.data.dao

import pt.unl.fct.tourtuga.gas.api.dataItem.DataItemDTO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

//id podia ser Text ou criamos um nome para associar???

@Entity
class DataItemDAO(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id:Long,
        //var name:String,
        var dataType:String
) {
    constructor(dataItem: DataItemDTO) : this(dataItem.id, dataItem.dataType) {
    }
    //Empty constructor
    constructor() : this(0,"")

    fun update(item:DataItemDTO){
        this.dataType = item.dataType;
    }
}