package com.whoiscan.resttest.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperRequest {
    private Integer id;
    private String name;
    private String office;
    private Integer salary;

}
