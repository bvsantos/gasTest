package pt.unl.fct.tourtuga.gas.api.call

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*
import pt.unl.fct.tourtuga.gas.data.dao.CallDAO
import pt.unl.fct.tourtuga.gas.data.dao.DataInCallDAO
import pt.unl.fct.tourtuga.gas.data.dao.PanelDAO
import pt.unl.fct.tourtuga.gas.data.dao.ReviewerInPanelDAO

@Api(value = "Call API", description = "Handles Call related requests")
@RequestMapping("/calls")
interface Call {

    //-----------------------------------Gets------------------------------//
    @ApiOperation(value = "View a list of all calls", response = List::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "There are no calls created")])
    @GetMapping("/")
    fun getAllCalls(): List<CallDAO>

    //-----------------------------------Gets------------------------------//
    @ApiOperation(value = "View a list of active calls", response = List::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "There are no calls active")])
    @GetMapping("/active/")
    fun getActiveCalls(): List<CallDAO>

    @ApiOperation(value = "View details of a call", response = CallDAO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Call doesnt exist")])
    @GetMapping("/{id}")
    fun getCallById(@PathVariable id: Long): CallDAO

    @ApiOperation(value = "Create an call")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization")])
    @PostMapping("/")
    fun createCall(@RequestBody call: CallDTO)

    @ApiOperation(value = "Update a call")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Call doesnt exist")])
    @PutMapping("/{id}")
    fun updateCall(@PathVariable id: Long, @RequestBody call: CallDTO)

    @ApiOperation(value = "Delete a call")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Call doesnt exist")])
    @DeleteMapping("/{id}")
    fun deleteCall(@PathVariable id: Long)

    //---------------------------------------------Panel-----------------------------------------------------//

    @ApiOperation(value = "View details of a panel", response = PanelDAO::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Panel doesnt exist")])
    @GetMapping("/panels/{id}")
    fun getPanelById(@PathVariable id: Long): PanelDAO

    @ApiOperation(value = "Associate a panel to a call")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization")])
    @PostMapping("/panels/")
    fun associatePanelToCall(@RequestBody panel: PanelDTO)

    @ApiOperation(value = "Update a panel")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Panel doesnt exist")])
    @PutMapping("/panels/{id}")
    fun updatePanel(@PathVariable id: Long, @RequestBody panel: PanelDTO, @RequestBody reviewersInPanel: List<ReviewerInPanelDTO>)

    @ApiOperation(value = "Delete a panel")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Panel doesnt exist")])
    @DeleteMapping("/panels/{id}")
    fun deletePanel(@PathVariable id: Long)

    //---------------------------------------------ReviewersInPanel-----------------------------------------------------//

    @ApiOperation(value = "Get all reviewers in a panel", response = List::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Panel doesnt exist")])
    @GetMapping("/reviewersInPanel/{id}")
    fun getAllReviewersInPanel(@PathVariable id: Long): List<ReviewerInPanelDAO>

    @ApiOperation(value = "Associate a panel to a call")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization")])
    @PostMapping("/reviewersInPanel/")
    fun associateReviewerToPanel(@RequestBody reviewerToPanel: ReviewerInPanelDTO)

    @ApiOperation(value = "Delete a reviewer in a panel")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Panel doesnt exist")])
    @DeleteMapping("/reviewersInPanel/{id}")
    fun deleteReviewerInPanel(@PathVariable id: Long)

    //-----------------------------------------------Data_In_Call----------------------------------------------------------//

    @ApiOperation(value = "Get all data items in a call", response = List::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Call doesnt exist")])
    @GetMapping("/dataInCall/{id}")
    fun getAllDataItemsInCall(@PathVariable id: Long): List<DataInCallDAO>

    @ApiOperation(value = "Associate a data item to a call")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization")])
    @PostMapping("/dataInCall/")
    fun associateDataItemInCall(@RequestBody dataInCall: DataInCallDTO)

    @ApiOperation(value = "Delete a data item in a call")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Success"),
        ApiResponse(code = 401, message = "No authorization"),
        ApiResponse(code = 404, message = "Call doesnt exist")])
    @DeleteMapping("/dataInCall/{id}")
    fun deleteDataItemInCall(@PathVariable id: Long)

}