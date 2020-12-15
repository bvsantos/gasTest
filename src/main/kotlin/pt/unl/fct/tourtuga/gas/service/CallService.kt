package pt.unl.fct.tourtuga.gas.service

import org.springframework.stereotype.Service
import pt.unl.fct.tourtuga.gas.api.application.ApplicationDTO
import pt.unl.fct.tourtuga.gas.api.call.CallDTO
import pt.unl.fct.tourtuga.gas.api.call.DataInCallDTO
import pt.unl.fct.tourtuga.gas.api.call.PanelDTO
import pt.unl.fct.tourtuga.gas.api.call.ReviewerInPanelDTO
import pt.unl.fct.tourtuga.gas.api.reviewer.ReviewerDTO
import pt.unl.fct.tourtuga.gas.data.dao.*
import pt.unl.fct.tourtuga.gas.data.repository.CallRepository
import pt.unl.fct.tourtuga.gas.data.repository.DataInCallRepository
import pt.unl.fct.tourtuga.gas.data.repository.PanelRepository
import pt.unl.fct.tourtuga.gas.data.repository.ReviewerInPanelRepository
import pt.unl.fct.tourtuga.gas.exceptions.HTTPBadRequestException
import pt.unl.fct.tourtuga.gas.exceptions.HTTPConflictException
import pt.unl.fct.tourtuga.gas.exceptions.HTTPNotFoundException
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.TimeUnit

@Service
class CallService(
        val calls: CallRepository,
        val panels: PanelRepository,
        val reviewerInPanels: ReviewerInPanelRepository,
        val dataInCalls: DataInCallRepository
) {

    fun getAllCalls(): List<CallDAO> {
        var allCalls = calls.findAll();
        if (allCalls.isEmpty())
            throw HTTPNotFoundException("There are no Calls!");
        else
            return allCalls;
    }

    fun getActiveCalls(): List<CallDAO> {
        var allCalls = calls.findAllActiveCall(LocalDate.now());
        if (allCalls.isEmpty())
            throw HTTPNotFoundException("There are no Calls!");
        else
            return allCalls;
    }

    fun getCallById(id: Long): CallDAO {
        return calls.findById(id).orElseThrow {
            HTTPNotFoundException("Call doesn't exist with id: $id")
        }
    }

    fun createCall(call: CallDTO) {
        var invalidDate = ChronoUnit.DAYS.between(call.openingDate, call.closingDate) < 0;
        if (invalidDate) {
            throw HTTPBadRequestException("Opening date can't be greater than Closing date!");
        } else if (getCall(call.id, true) != null) {
            calls.save(CallDAO(call));
        }
    }

    fun updateCall(call: CallDTO) {
        if (getCall(call.id, false) != null) {
            calls.findById(call.id).get().let {
                it.update(call);
                calls.save(it);
            };
        }
    }

    fun deleteCall(id: Long) {
        if (getCall(id, false) != null) {
            calls.deleteById(id);
        }
    }

    //----------------------------------------------Panel----------------------------------------//

    fun getPanelById(id: Long): PanelDAO {
        return panels.findById(id).orElseThrow {
            HTTPNotFoundException("Panel doesn't exist with id: $id")
        }
    }

    fun associatePanelToCall(panel: PanelDTO) {
        if (getPanel(panel.id, true) != null) {
            panels.save(PanelDAO(panel));
        }
    }

    fun updatePanel(panel: PanelDTO, reviewers: List<ReviewerInPanelDTO>) {
        if (getPanel(panel.id, false) != null) {
            panels.findById(panel.id).get().let {
                it.update(panel);
                panels.save(it);
                if (reviewers.isNotEmpty()) {
                    reviewers.forEach { it -> associateReviewerToPanel(it) }
                }
            };
        }
    }

    fun deletePanel(id: Long) {
        if (getPanel(id, false) != null) {
            panels.deleteById(id);
        }
    }
    //---------------------------------------AssociateReviewerToPanel---------------------------------//

    //Se calhar usar uma query para receber os reviewersInPanel + Reviewers
    fun getAllReviewersInPanel(panelId: Long): List<ReviewerInPanelDAO> {
        var allReviewersInPanel = reviewerInPanels.findAllByPanelId(panelId);
        if (allReviewersInPanel.isEmpty())
            throw HTTPNotFoundException("There are no Reviewers in Panel with id: $panelId!");
        else
            return allReviewersInPanel;
    }

    fun associateReviewerToPanel(reviewerInPanel: ReviewerInPanelDTO) {
        if (getReviewerInPanel(reviewerInPanel.id, true) != null) {
            reviewerInPanels.save(ReviewerInPanelDAO(reviewerInPanel));
        }
    }

    fun deleteReviewerInPanel(id: Long) {
        if (getReviewerInPanel(id, false) != null) {
            reviewerInPanels.deleteById(id);
        }
    }

    //---------------------------------------Data_In_Call--------------------------------------------//

    fun getAllDataItemsInCall(id: Long): List<DataInCallDAO> {
        var allDataItems = dataInCalls.findAll();
        if (allDataItems.isEmpty())
            throw HTTPNotFoundException("There are no Calls!");
        else
            return allDataItems;
    }

    fun associateDataItemInCall(dataInCall: DataInCallDTO) {
        if (getDataInCall(dataInCall.id, true) != null) {
            dataInCalls.save(DataInCallDAO(dataInCall));
        }
    }

    fun deleteDataItemInCall(id: Long) {
        if (getDataInCall(id, false) != null) {
            dataInCalls.deleteById(id);
        }
    }

    //--------------------------Private--------------------------------//

    /**
     * Returns a Call with the input id
     * if isCreating = False, it's updating or deleting, otherwise is creating
     */
    fun getCall(id: Long, isCreating: Boolean) {
        var call = calls.findById(id);
        if (!isCreating) {
            call.orElseThrow {
                HTTPNotFoundException("Call doesn't exist with id: $id");
            };
        } else if (call != null) {
            HTTPConflictException("Call already exists with id: $id");
        }
    }

    /**
     * Returns a Panel with the input id
     * if isCreating = False, it's updating or deleting, otherwise is creating
     */
    fun getPanel(id: Long, isCreating: Boolean) {
        var panel = panels.findById(id);
        if (!isCreating) {
            panel.orElseThrow {
                HTTPNotFoundException("Panel doesn't exist with id: $id");
            };
        } else if (panels != null) {
            HTTPConflictException("Panel already exists with id: $id");
        }
    }

    /**
     * Returns a Reviewer in Panel with the input id
     * if isCreating = False, it's updating or deleting, otherwise is creating
     */
    fun getReviewerInPanel(id: Long, isCreating: Boolean) {
        var reviewerInPanel = reviewerInPanels.findById(id);
        if (!isCreating) {
            reviewerInPanel.orElseThrow {
                HTTPNotFoundException("Reviewer in Panel doesn't exist with id: $id");
            };
        } else if (reviewerInPanel != null) {
            HTTPConflictException("Reviewer in Panel already exists with id: $id");
        }
    }

    /**
     * Returns a Data in Call with the input id
     * if isCreating = False, it's updating or deleting, otherwise is creating
     */
    fun getDataInCall(id: Long, isCreating: Boolean) {
        var dataInCall = dataInCalls.findById(id);
        if (!isCreating) {
            dataInCall.orElseThrow {
                HTTPNotFoundException("Data in Call doesn't exist with id: $id");
            };
        } else if (dataInCall != null) {
            HTTPConflictException("Data in Call already exists with id: $id");
        }
    }
}