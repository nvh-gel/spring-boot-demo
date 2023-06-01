package org.eden.web.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {

    private Long id;
    private String username;
    private String email;
    private String password;
}
