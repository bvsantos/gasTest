package pt.unl.fct.tourtuga.gas.api.call

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import pt.unl.fct.tourtuga.gas.service.CallService

@RestController
class CallController(val calls: CallService) : Call {

    override fun getAllCalls() = calls.getAllCalls()

    override fun getCallById(@PathVariable id: Long) = calls.getCallById(id)

    override fun getActiveCalls() = calls.getActiveCalls()

    override fun createCall(@RequestBody call: CallDTO) = calls.createCall(call)

    override fun updateCall(id: Long, call: CallDTO) = calls.updateCall(call)

    override fun deleteCall(id: Long) = calls.deleteCall(id)

    override fun getPanelById(@PathVariable id: Long) = calls.getPanelById(id)

    //---------------------------------------Panel---------------------------------------------//

    override fun associatePanelToCall(@RequestBody panel: PanelDTO) = calls.associatePanelToCall(panel)

    override fun updatePanel(id: Long, panel: PanelDTO, reviewersInPanel : List<ReviewerInPanelDTO>) = calls.updatePanel(panel, reviewersInPanel)

    override fun deletePanel(id: Long) = calls.deletePanel(id)

    //---------------------------------------AssociateReviewerToPanel---------------------------------//

    override fun getAllReviewersInPanel(id: Long) = calls.getAllReviewersInPanel(id)

    override fun associateReviewerToPanel(reviewerToPanel: ReviewerInPanelDTO) = calls.associateReviewerToPanel(reviewerToPanel)

    override fun deleteReviewerInPanel(id: Long) = calls.deleteReviewerInPanel(id)

    //---------------------------------------Data_In_Call--------------------------------------------//

    override fun getAllDataItemsInCall(id: Long) = calls.getAllDataItemsInCall(id)

    override fun associateDataItemInCall(dataInCall: DataInCallDTO) = calls.associateDataItemInCall(dataInCall)

    override fun deleteDataItemInCall(id: Long) = calls.deleteDataItemInCall(id)


}