package com.getir.bookstore.dto.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class PageRequestDtoTest {
    @Test
    void testCanEqual() {
        assertFalse((new PageRequestDto()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        PageRequestDto pageRequestDto = new PageRequestDto();

        PageRequestDto pageRequestDto1 = new PageRequestDto();
        pageRequestDto1.setPage(0);
        pageRequestDto1.setSize(3);
        assertTrue(pageRequestDto.canEqual(pageRequestDto1));
    }

    @Test
    void testConstructor() {
        PageRequestDto actualPageRequestDto = new PageRequestDto();
        actualPageRequestDto.setPage(1);
        actualPageRequestDto.setSize(3);
        assertEquals(1, actualPageRequestDto.getPage().intValue());
        assertEquals(3, actualPageRequestDto.getSize().intValue());
        assertEquals("PageRequestDto(size=3, page=1)", actualPageRequestDto.toString());
    }

    @Test
    void testEquals() {
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(1);
        pageRequestDto.setSize(3);
        assertFalse(pageRequestDto.equals(null));
    }

    @Test
    void testEquals2() {
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(1);
        pageRequestDto.setSize(3);
        assertFalse(pageRequestDto.equals("Different type to PageRequestDto"));
    }

    @Test
    void testEquals3() {
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(1);
        pageRequestDto.setSize(3);
        assertTrue(pageRequestDto.equals(pageRequestDto));
        int expectedHashCodeResult = pageRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, pageRequestDto.hashCode());
    }

    @Test
    void testEquals4() {
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(1);
        pageRequestDto.setSize(3);

        PageRequestDto pageRequestDto1 = new PageRequestDto();
        pageRequestDto1.setPage(1);
        pageRequestDto1.setSize(3);
        assertTrue(pageRequestDto.equals(pageRequestDto1));
        int expectedHashCodeResult = pageRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, pageRequestDto1.hashCode());
    }

    @Test
    void testEquals5() {
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(0);
        pageRequestDto.setSize(3);

        PageRequestDto pageRequestDto1 = new PageRequestDto();
        pageRequestDto1.setPage(1);
        pageRequestDto1.setSize(3);
        assertFalse(pageRequestDto.equals(pageRequestDto1));
    }

    @Test
    void testEquals6() {
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(null);
        pageRequestDto.setSize(3);

        PageRequestDto pageRequestDto1 = new PageRequestDto();
        pageRequestDto1.setPage(1);
        pageRequestDto1.setSize(3);
        assertFalse(pageRequestDto.equals(pageRequestDto1));
    }

    @Test
    void testEquals7() {
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(1);
        pageRequestDto.setSize(0);

        PageRequestDto pageRequestDto1 = new PageRequestDto();
        pageRequestDto1.setPage(1);
        pageRequestDto1.setSize(3);
        assertFalse(pageRequestDto.equals(pageRequestDto1));
    }

    @Test
    void testEquals8() {
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(1);
        pageRequestDto.setSize(null);

        PageRequestDto pageRequestDto1 = new PageRequestDto();
        pageRequestDto1.setPage(1);
        pageRequestDto1.setSize(3);
        assertFalse(pageRequestDto.equals(pageRequestDto1));
    }

    @Test
    void testEquals9() {
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(1);
        pageRequestDto.setSize(3);

        PageRequestDto pageRequestDto1 = new PageRequestDto();
        pageRequestDto1.setPage(0);
        pageRequestDto1.setSize(3);
        assertFalse(pageRequestDto.equals(pageRequestDto1));
    }

    @Test
    void testEquals10() {
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(null);
        pageRequestDto.setSize(3);

        PageRequestDto pageRequestDto1 = new PageRequestDto();
        pageRequestDto1.setPage(null);
        pageRequestDto1.setSize(3);
        assertTrue(pageRequestDto.equals(pageRequestDto1));
        int expectedHashCodeResult = pageRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, pageRequestDto1.hashCode());
    }

    @Test
    void testEquals11() {
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(1);
        pageRequestDto.setSize(null);

        PageRequestDto pageRequestDto1 = new PageRequestDto();
        pageRequestDto1.setPage(1);
        pageRequestDto1.setSize(null);
        assertTrue(pageRequestDto.equals(pageRequestDto1));
        int expectedHashCodeResult = pageRequestDto.hashCode();
        assertEquals(expectedHashCodeResult, pageRequestDto1.hashCode());
    }
}

