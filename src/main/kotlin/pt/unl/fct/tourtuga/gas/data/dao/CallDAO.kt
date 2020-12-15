package pt.unl.fct.tourtuga.gas.data.dao

import pt.unl.fct.tourtuga.gas.api.application.ApplicationDTO
import pt.unl.fct.tourtuga.gas.api.call.CallDTO
import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class CallDAO(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long,
        var sponsorId: Long,
        var title: String,
        var description: String,
        var requirements: String,
        var funding: Number,
        var openingDate: LocalDate,
        var closingDate: LocalDate,
        var createdBy: Long,
        var createdAt: LocalDate,
        var updatedBy: Long,
        var updatedAt: LocalDate
) {

    constructor(call: CallDTO) : this(call.id, call.sponsorId, call.title, call.description, call.requirements,
            call.funding, call.openingDate, call.closingDate, call.createdBy, LocalDate.now(),
            call.updatedBy, LocalDate.now()) {
    }

    //Empty constructor
    constructor() : this(0, 0, "", "", "", 0, LocalDate.of(1995, 11, 17),
            LocalDate.of(1995, 11, 17), 0, LocalDate.now(), 0, LocalDate.now())

    fun update(call: CallDTO) {
        this.sponsorId = call.sponsorId;
        this.title = call.title;
        this.description = call.description;
        this.requirements = call.requirements;
        this.funding = call.funding;
        this.openingDate = call.openingDate;
        this.closingDate = call.closingDate;
        this.updatedBy = call.updatedBy;
        this.updatedAt = LocalDate.now();
    }
}