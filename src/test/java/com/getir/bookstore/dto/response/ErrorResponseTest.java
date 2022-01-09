package com.getir.bookstore.dto.response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class ErrorResponseTest {
    @Test
    void testConstructor() {
        ErrorResponse actualErrorResponse = new ErrorResponse("Field Name", "Not all who wander are lost");
        actualErrorResponse.setFieldName("Field Name");
        actualErrorResponse.setMessage("Not all who wander are lost");
        assertEquals("Field Name", actualErrorResponse.getFieldName());
        assertEquals("Not all who wander are lost", actualErrorResponse.getMessage());
        assertEquals("ErrorResponse(fieldName=Field Name, message=Not all who wander are lost)",
                actualErrorResponse.toString());
    }
}

