package pt.unl.fct.tourtuga.gas.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.stream.Collectors

class MyUserDetails:UserDetails {
    private val userName:String;
    private val pass:String;
    private var roles = mutableListOf<String>();




    constructor(userName:String, password:String, role:String){
        this.userName = userName;
        this.pass = BCryptPasswordEncoder().encode(password);
        this.roles.add( "ROLE_REGISTERED");
        this.roles.add("ROLE_$role");
        println("Found ${this.roles.toString()}");
        //this.roles[0] = SimpleGrantedAuthority("REGISTERED_USER");


    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.roles.stream().map {role-> SimpleGrantedAuthority(role) }.collect(Collectors.toList());
    }

    override fun getPassword(): String {
        return this.pass;
    }

    override fun getUsername(): String {
        return this.userName;
    }

    override fun isAccountNonExpired(): Boolean {
        return true;
    }

    override fun isAccountNonLocked(): Boolean {
        return true;
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true;
    }

    override fun isEnabled(): Boolean {
        return true;
    }
}