package pt.unl.fct.tourtuga.gas.service

import org.springframework.stereotype.Service
import pt.unl.fct.tourtuga.gas.api.dataItem.DataItemDTO
import pt.unl.fct.tourtuga.gas.api.student.StudentDTO
import pt.unl.fct.tourtuga.gas.data.dao.DataItemDAO
import pt.unl.fct.tourtuga.gas.data.dao.StudentDAO
import pt.unl.fct.tourtuga.gas.data.repository.DataItemRepository
import pt.unl.fct.tourtuga.gas.exceptions.HTTPConflictException
import pt.unl.fct.tourtuga.gas.exceptions.HTTPNotFoundException

@Service
class ItemService (
        val items: DataItemRepository
){

    fun getAllItems():List<DataItemDTO>{
        var list = items.findAll().toList()
        return list.map {
            DataItemDTO(it)
        }
    }

    fun getItemById(id:Long) =  DataItemDTO(items.findById(id).orElseThrow{
        HTTPNotFoundException("Data item with id $id doesn't exist");
    })

    fun createItem(item: DataItemDTO) {
        if(!items.findOneByDataType(item.dataType).isPresent){
            items.save(DataItemDAO(item))
        }
        else
            throw HTTPConflictException("Data Item with this email is already registered!")
    }

    fun updateItem(item: DataItemDTO) {
        items.findById(item.id).orElseThrow {
            throw HTTPConflictException("Data item with id $item.id was not found")
        }.let {
            it.update(item)
            items.save(it)
        }
    }

    fun deleteItem(id: Long){
        items.findById(id).orElseThrow{
            throw HTTPNotFoundException("Data item was not found")
        }
        items.deleteById(id)
    }
}