package com.optivem.kata.banking.core.usecases;

import com.optivem.kata.banking.core.domain.exceptions.ValidationException;
import com.optivem.kata.banking.core.domain.exceptions.ValidationMessages;
import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountRequest;
import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountResponse;
import com.optivem.kata.banking.core.usecases.openaccount.OpenAccountUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OpenAccountUseCaseTest {

    private OpenAccountUseCase useCase;

    @BeforeEach
    void init() {
        this.useCase = new OpenAccountUseCase();
    }

    @Test
    void nothing() {
        var request = new OpenAccountRequest();
        request.setFirstName("John");

        var expectedResponse = new OpenAccountResponse();

        var response = useCase.handle(request);

        assertThat(response).usingRecursiveComparison().isEqualTo(expectedResponse);
    }

    @ParameterizedTest
    @MethodSource
    void should_throw_exception_when_first_name_is_empty(String firstName) {
        var request = new OpenAccountRequest();
        request.setFirstName(firstName);

        var exception = assertThrows(ValidationException.class, () -> useCase.handle(request));

        assertThat(exception.getMessage()).isEqualTo(ValidationMessages.FIRST_NAME_EMPTY);
    }

    private static Stream<String> should_throw_exception_when_first_name_is_empty() {
        return Stream.of(null, "", "  ");
    }
}
