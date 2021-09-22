package com.groppa.meli.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerReport {
    private long id;
    private String name;
    private String address;
    private String email;
    @JsonFormat(pattern = com.groppa.meli.api.constants.JsonFormat.DATE_TIME_FORMAT)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationDate;
}
