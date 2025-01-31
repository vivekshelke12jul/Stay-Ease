package com.crio.starEasy.exchanges.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResonse {
    private String message;
    private String token;
}
