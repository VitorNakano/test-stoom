package br.com.stoom.store.common.validators;

import br.com.stoom.store.common.enums.EStatus;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Validator {

    private static final Set<EStatus> STATUSES = Stream.of(EStatus.A, EStatus.I).collect(Collectors.toSet());

    public static boolean validateStatus(EStatus status) {
        return STATUSES.contains(status);
    }

    private Validator(){

    }
}
