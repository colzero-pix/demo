package com.jie.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int userId;

    private String username;

    private String email;

    private String phone;

    private LocalDateTime gmtCreat;


}
