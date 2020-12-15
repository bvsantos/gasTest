package pt.unl.fct.tourtuga.gas.service

import org.springframework.stereotype.Service
import pt.unl.fct.tourtuga.gas.api.sponsor.SponsorDTO
import pt.unl.fct.tourtuga.gas.api.student.StudentDTO
import pt.unl.fct.tourtuga.gas.data.dao.SponsorDAO
import pt.unl.fct.tourtuga.gas.data.dao.StudentDAO
import pt.unl.fct.tourtuga.gas.data.repository.SponsorRepository
import pt.unl.fct.tourtuga.gas.exceptions.HTTPConflictException
import pt.unl.fct.tourtuga.gas.exceptions.HTTPNotFoundException
import java.util.*

@Service
class SponsorService(
        val sponsors: SponsorRepository
) {

    fun getAllSponsors():List<SponsorDTO> {
        var list = sponsors.findAll().toList()
        return list.map {
            SponsorDTO(it)
        }
    }

    fun getSponsorById(id:Long) =  SponsorDTO(sponsors.findById(id).orElseThrow{
        HTTPNotFoundException("Sponsor with id $id doesn't exist");
    })

    fun createSponsor(sponsor: SponsorDTO) {
        if(!sponsors.findOneByName(sponsor.name).isPresent){
            sponsors.save(SponsorDAO(sponsor))
        }
        else
            throw HTTPConflictException("Sponsor with this email is already registered!")
    }

    fun updateSponsor(sponsor: SponsorDTO) {
        sponsors.findById(sponsor.id).orElseThrow {
            throw HTTPConflictException("Sponsor with id $sponsor.id was not found")
        }.let {
            it.update(sponsor)
            sponsors.save(it)
        }
    }

    fun deleteSponsor(id: Long){
        var spo = sponsors.findById(id).orElseThrow{
            throw HTTPNotFoundException("Sponsor was not found")
        }
        sponsors.deleteById(id)
    }

}