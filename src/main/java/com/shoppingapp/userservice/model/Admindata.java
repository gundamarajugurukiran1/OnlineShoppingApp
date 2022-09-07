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
@Table(name="admin_details")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Admindata {

    @Id
//  @Schema(description = "user name of the user",required = true,example="admin@gmail.com")
  @Pattern(regexp = "[a-zA-Z0-9@.]*$", message = "user name should contain only alphabets and digits")
  private String username;
  @Column
//  @Schema(description = "Password of the user",required = true,example="admin")
  @NotBlank(message="Password should not be empty")
  @Size(min = 8, message = "minimum 8 Characters required")
  private String password;
}
