package br.com.stoom.store.common.validators;

import br.com.stoom.store.common.enums.EStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    @Test
    void validateStatus_mustReturnTrue_ifStatusIsValid() {
        assertThat(Validator.validateStatus(EStatus.valueOf("A"))).isTrue();
    }

    @Test
    void validateStatus_mustReturnFalse_ifStatusIsInvalid() {
        boolean isValid;
        try {
            EStatus status = EStatus.valueOf("S");
            isValid = Validator.validateStatus(status);
        } catch (IllegalArgumentException e) {
            isValid = false;
        }

        assertThat(isValid).isFalse();
    }
}
