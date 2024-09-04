package com.DemoSpringSecurity.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
// // import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    // // @NotEmpty
    @NotEmpty
    private String firstname;
    @Column(nullable = false)
    // @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotEmpty
    @Email(message = "{errors.invalid_email}")
    private String email;

    @NotEmpty
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "USER_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
                    @JoinColumn(name = "ROLL_ID", referencedColumnName = "ID") })
    private List<Role> roles;

    public User(User user) {
        this.firstname = user.getFirstname();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.password;
        this.roles = user.roles;
    }

    public User() {

    }

}
