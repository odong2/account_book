package com.account.accountbook.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@ToString
public class TestDto {

    @NotNull
    private String name;

    @NotNull
    private String nick;
}
