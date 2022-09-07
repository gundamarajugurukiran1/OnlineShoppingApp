package com.shoppingapp.userservice.model;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user_details")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Userdata {
    @Column
//    @Schema(description = "First name of the user",required = true,example="gundamaraju")
    @NotBlank(message="First name should not be empty")
    private String firstname;
    @Column
//    @Schema(description = "Last name of the user",required = true,example="Gurukiran")
    @NotBlank(message="Last name should not be empty")
    private String lastname;
    @Id
//    @Schema(description = "user name of the user",required = true,example="Gundamaraju@gmail.com")
    @Pattern(regexp = "[a-zA-Z0-9@.]*$", message = "user name should contain only alphabets and digits")
    private String username;
    @Column
//    @Schema(description = "Password of the user",required = true,example="Gundamaraju")
    @NotBlank(message="Password should not be empty")
    @Size(min = 8, message = "minimum 8 Characters required")
    private String password;
    @Column
//    @Schema(description = "Contact Number of the user",required = true,example="9898989898")
    private long contactNo;
}
