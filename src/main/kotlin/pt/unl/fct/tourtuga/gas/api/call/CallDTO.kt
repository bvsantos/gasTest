package pt.unl.fct.tourtuga.gas.api.call

import java.time.LocalDate

data class CallDTO(var id: Long, var sponsorId: Long, var title: String, var description: String, var requirements: String, var funding: Int,
                   var openingDate: LocalDate, var closingDate: LocalDate, val createdBy: Long, val createdAt: LocalDate,
                   val updatedBy: Long, val updatedAt: LocalDate)

data class PanelDTO(val id:Long, val name:String)

data class ReviewerInPanelDTO(val id:Long, val panelId:Long, val reviewerId:Long, val isChair:Boolean)

data class DataInCallDTO(val id:Long, val callId:Long, val dateItemId:Long, val isMandatory:Boolean)