package com.bot.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String usr;
    private String password;

    public String getPassword(){
        return this.password;
    }
    public String getUsername(){
        return this.usr;
    }
}