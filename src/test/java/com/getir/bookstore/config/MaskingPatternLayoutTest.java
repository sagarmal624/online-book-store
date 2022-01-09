package com.getir.bookstore.config;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.status.ErrorStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class MaskingPatternLayoutTest {
    @Test
    void testDoLayout() {
        MaskingPatternLayout maskingPatternLayout = new MaskingPatternLayout();
        assertEquals("", maskingPatternLayout.doLayout(new LoggingEvent()));
    }

    @Test
    void testDoLayout2() {
        MaskingPatternLayout maskingPatternLayout = new MaskingPatternLayout();
        maskingPatternLayout.setPatternsProperty("Patterns Property");
        maskingPatternLayout.addStatus(new ErrorStatus(null, null));

        LoggingEvent loggingEvent = new LoggingEvent();
        loggingEvent.setMessage("Not all who wander are lost");
        assertEquals("", maskingPatternLayout.doLayout(loggingEvent));
    }

    @Test
    void testDoLayout3() {
        MaskingPatternLayout maskingPatternLayout = new MaskingPatternLayout();
        maskingPatternLayout.setPatternsProperty("");
        maskingPatternLayout.addStatus(new ErrorStatus(null, null));

        LoggingEvent loggingEvent = new LoggingEvent();
        loggingEvent.setMessage("Not all who wander are lost");
        assertEquals(
                " password:**********N password:**********o password:**********t password:**********  password:**********a"
                        + " password:**********l password:**********l password:**********  password:**********w password:**********h"
                        + " password:**********o password:**********  password:**********w password:**********a password:**********n"
                        + " password:**********d password:**********e password:**********r password:**********  password:**********a"
                        + " password:**********r password:**********e password:**********  password:**********l password:**********o"
                        + " password:**********s password:**********t password:**********",
                maskingPatternLayout.doLayout(loggingEvent));
    }

    @Test
    void testConstructor() {
        MaskingPatternLayout actualMaskingPatternLayout = new MaskingPatternLayout();
        actualMaskingPatternLayout.setPatternsProperty("Patterns Property");
        assertEquals("Patterns Property", actualMaskingPatternLayout.getPatternsProperty());
    }
}

