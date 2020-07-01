package com.huiyu.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String password;
    private String username;
    private String email;
    private Long createdAt;
    private Long updatedAt;
    private boolean isDeleted;

}
