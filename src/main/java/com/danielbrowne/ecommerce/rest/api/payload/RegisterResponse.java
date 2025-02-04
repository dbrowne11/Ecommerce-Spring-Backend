package com.danielbrowne.ecommerce.rest.api.payload;

import com.danielbrowne.ecommerce.rest.api.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private String message;
    private boolean error;
    private User user;
}
