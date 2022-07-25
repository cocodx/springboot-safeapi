package com.lg.safeapi.mapper.primary;

import com.lg.safeapi.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookMapperTest {

    //会注入到spring里面去
    @MockBean
    BookMapper bookMapper;

    @Test
    void insertBook() {
        //定义了mock对象的行为
        when(bookMapper.insertBook(new Book())).thenReturn(1);
        //然后去执行，预定义的mock方法
        int result = bookMapper.insertBook(new Book());
        //判断结果
        Assertions.assertEquals(1,result);
    }
}