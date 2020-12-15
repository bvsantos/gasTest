package pt.unl.fct.tourtuga.gas.api.dataItem

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*
import pt.unl.fct.tourtuga.gas.data.dao.DataItemDAO

@Api(value = "DataItem API", description = "Handles Data Item related requests")
@RequestMapping("/items")
interface DataItem {

    @ApiOperation(value = "View a list of all Items", response = List::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "There are no Items created")])
    @GetMapping("/")
    fun getAllItems(): List<DataItemDTO>

    @ApiOperation(value = "View details of a Item", response = DataItemDTO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Item doesnt exist")])
    @GetMapping("/{id}")
    fun getItemById(@PathVariable id: Long): DataItemDTO

    @ApiOperation(value = "Create an Item")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization")])
    @PostMapping("/")
    fun createItem(@RequestBody Item: DataItemDTO)

    @ApiOperation(value = "Update a Item")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Item doesnt exist")])
    @PutMapping("/{id}")
    fun updateItem(@PathVariable id: Long, @RequestBody Item: DataItemDTO)

    @ApiOperation(value = "Delete a Item")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Item doesnt exist")])
    @DeleteMapping("/{id}")
    fun deleteItem(@PathVariable id: Long)
}