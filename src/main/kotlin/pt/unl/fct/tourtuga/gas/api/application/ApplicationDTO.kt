package pt.unl.fct.tourtuga.gas.api.application

import java.time.LocalDate

data class ApplicationDTO(val id: Long, val studentId: Long, val callId: Long, val name: String, val wasAccepted: Boolean,
                          val createdBy: Long, val createdAt: LocalDate, val updatedBy: Long, val updatedAt: LocalDate)

data class DataInApplicationDTO(val id: Long, val applicationID: Long, val dataID: Long, val data: ByteArray)

data class ApplicationReviewDTO(val id: Long, val applicationId: Long, val reviewerId: Long, val review: String)