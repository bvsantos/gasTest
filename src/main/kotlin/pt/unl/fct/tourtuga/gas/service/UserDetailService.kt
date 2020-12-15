package pt.unl.fct.tourtuga.gas.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import pt.unl.fct.tourtuga.gas.data.dao.ReviewerDAO
import pt.unl.fct.tourtuga.gas.data.dao.SponsorDAO
import pt.unl.fct.tourtuga.gas.data.dao.StudentDAO
import pt.unl.fct.tourtuga.gas.data.repository.ReviewerRepository
import pt.unl.fct.tourtuga.gas.data.repository.SponsorRepository
import pt.unl.fct.tourtuga.gas.data.repository.StudentRepository
import pt.unl.fct.tourtuga.gas.exceptions.HTTPNotFoundException
import pt.unl.fct.tourtuga.gas.security.MyUserDetails
import java.util.*

@Service("UserDetailService")
class UserDetailService(
        val students: StudentRepository,
        val sponsors: SponsorRepository,
        val reviewers: ReviewerRepository
):UserDetailsService{

    override fun loadUserByUsername(username: String): UserDetails? {
        var user:Optional<*> = students.findOneByEmail(username);

        if(user.isPresent){
            val student: StudentDAO = user.get() as StudentDAO;
            //println("Found ${student.password}")
            return MyUserDetails(username, student.password, "STUDENT")
        }
        /*user = sponsors.findOneByName(username);
        if(user.isPresent){
            val sponsor: SponsorDAO = user.get() as SponsorDAO;
            return MyUserDetails(username, sponsor.password, "SPONSOR")
        }*/
        user = reviewers.findOneByEmail(username);
        if(user.isPresent){
            val reviewer: ReviewerDAO = user.get() as ReviewerDAO;
            return MyUserDetails(username, reviewer.password, "SPONSOR")
        }
        throw HTTPNotFoundException("User not registered in the system");
    }

}