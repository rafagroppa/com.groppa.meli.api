package com.groppa.meli.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerFilter {
    private Long id;
    private String name;
    private String address;
    private String email;
}
