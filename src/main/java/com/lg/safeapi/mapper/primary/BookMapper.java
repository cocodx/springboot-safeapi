package com.lg.safeapi.mapper.primary;

import com.lg.safeapi.entity.Book;

import java.util.List;

/**
 * @author 正能量导师
 * @date 2022/6/15-9:36
 * @Description
 */
public interface BookMapper {

    List<Book> getBooks();

    void deleteBooks(String ids);

    void deleteBooks2(String ids);

    int insertBook(Book book);

    int updateBookById(Book Book);
}
