package pt.unl.fct.tourtuga.gas.api.dataItem

import pt.unl.fct.tourtuga.gas.data.dao.DataItemDAO

data class DataItemDTO(val id: Long, val dataType: String){
    constructor(item: DataItemDAO): this(item.id, item.dataType)
}