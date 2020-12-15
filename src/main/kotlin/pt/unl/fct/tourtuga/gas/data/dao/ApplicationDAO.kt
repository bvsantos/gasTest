package pt.unl.fct.tourtuga.gas.data.dao

import pt.unl.fct.tourtuga.gas.api.application.ApplicationDTO
import java.time.LocalDate
import javax.persistence.*

@Entity
class ApplicationDAO(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long,
        var studentId: Long,
        var callId: Long,
        var name: String,
        var wasAccepted: Boolean,
        var createdBy: Long,
        var createdAt: LocalDate,
        var updatedBy: Long,
        var updatedAt: LocalDate
) {
    constructor(application: ApplicationDTO) : this(application.id, application.studentId, application.callId,
            application.name, application.wasAccepted, application.createdBy, LocalDate.now(),
            application.updatedBy, LocalDate.now()) {
    }

    //Empty constructor
    constructor() : this(0, 0, 0, "", false, 0,
            LocalDate.now(), 0, LocalDate.now())

    fun update(application: ApplicationDTO) {
        this.name = application.name;
        this.callId = application.callId;
        this.wasAccepted = application.wasAccepted;
        this.updatedBy = application.updatedBy;
        this.updatedAt = LocalDate.now();
    }

}