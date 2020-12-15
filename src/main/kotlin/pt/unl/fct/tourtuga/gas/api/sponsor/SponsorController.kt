package pt.unl.fct.tourtuga.gas.api.sponsor

import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import pt.unl.fct.tourtuga.gas.service.SponsorService
import pt.unl.fct.tourtuga.gas.api.sponsor.SponsorDTO


@RestController
class SponsorController(val sponsors: SponsorService) : Sponsor {

    override fun getAllSponsors() = sponsors.getAllSponsors()

    override fun getSponsorById(@PathVariable id: Long) = sponsors.getSponsorById(id)

    override fun createSponsor(@RequestBody sponsor: SponsorDTO) = sponsors.createSponsor(sponsor)

    override fun updateSponsor(id: Long, sponsor: SponsorDTO) = sponsors.updateSponsor(sponsor)

    override fun deleteSponsor(id: Long) = sponsors.deleteSponsor(id)

}