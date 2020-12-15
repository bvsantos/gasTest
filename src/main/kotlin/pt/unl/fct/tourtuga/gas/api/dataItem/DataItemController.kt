package pt.unl.fct.tourtuga.gas.api.dataItem

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import pt.unl.fct.tourtuga.gas.service.ItemService

@RestController
class DataItemController(val items: ItemService) : DataItem {

    override fun getAllItems() = items.getAllItems()

    override fun getItemById(@PathVariable id: Long) = items.getItemById(id)

    override fun createItem(@RequestBody Item: DataItemDTO) = items.createItem(Item)

    override fun updateItem(id: Long, Item: DataItemDTO) = items.updateItem(Item)

    override fun deleteItem(id: Long) = items.deleteItem(id)
}