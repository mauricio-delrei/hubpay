package com.microservices.auth.dto;

import lombok.*;

import java.io.Serializable;



@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDTO implements Serializable {

    //private static final long serialVersionUID = 1L;

    private String userName;
    private String password;
}
