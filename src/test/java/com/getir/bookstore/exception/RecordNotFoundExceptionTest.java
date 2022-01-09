package com.getir.bookstore.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RecordNotFoundExceptionTest {
    @Test
    void testConstructor() {
        RecordNotFoundException actualRecordNotFoundException = new RecordNotFoundException("An error occurred",
                "Field Name");

        assertNull(actualRecordNotFoundException.getCause());
        assertEquals("RecordNotFoundException(fieldName=Field Name)", actualRecordNotFoundException.toString());
        assertEquals(0, actualRecordNotFoundException.getSuppressed().length);
        assertEquals("An error occurred", actualRecordNotFoundException.getMessage());
        assertEquals("An error occurred", actualRecordNotFoundException.getLocalizedMessage());
        assertEquals("Field Name", actualRecordNotFoundException.getFieldName());
    }
}

