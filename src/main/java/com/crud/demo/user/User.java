package com.crud.demo.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "test1", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }, name = "LOGIN_UNIQ_ID"))
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    // @NotBlank(message = "First Name is mandatory")
    @NotEmpty(message = "First Name is mandatory")
    private String firstName;

    @Column(name = "last_name")
    // @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @Column(name = "dob")
    // @NotBlank(message = "Date of Birth is mandatory")
    private Date dob;

    @Column(name = "email", unique = true)
    @Email
    // @NotBlank(message = "Email is mandatory")
    private String email;

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return String return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return String return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
