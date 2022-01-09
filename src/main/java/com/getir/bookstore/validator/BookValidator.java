package com.getir.bookstore.validator;

import com.getir.bookstore.domain.Book;
import com.getir.bookstore.dto.request.BookRequestDto;
import com.getir.bookstore.repository.BookRepository;
import com.getir.bookstore.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@Slf4j
public class BookValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookRequestDto bookRequestDto = (BookRequestDto) target;
        if (StringUtils.isNotEmpty(bookRequestDto.getTitle())) {
            BookRepository bookRepository = BeanUtil.getBean(BookRepository.class);
            Optional<Book> optionalBook = bookRepository.findByTitle(bookRequestDto.getTitle());
            if (optionalBook.isPresent()) {
                errors.rejectValue("title", "Unique.bookRequestDto.title");
            }
        }
    }
}
