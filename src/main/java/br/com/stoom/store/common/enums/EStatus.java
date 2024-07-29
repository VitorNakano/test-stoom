package br.com.stoom.store.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EStatus {

    A("ACTIVE"), I("INACTIVE");

    private final String status;
}
