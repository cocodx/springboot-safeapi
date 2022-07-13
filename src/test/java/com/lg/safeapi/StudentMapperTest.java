package com.lg.safeapi;

import com.lg.safeapi.mapper.primary.BookMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author amazfit
 * @date 2022-07-14 上午2:16
 **/
public class StudentMapperTest {

    private SqlSessionFactory factory;

    @Before
    public void setUp() throws IOException {
        factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
    }

    @Test
    public void testLocalCache(){
        SqlSession session = factory.openSession(true);//自动提交事务
        BookMapper bookMapper = session.getMapper(BookMapper.class);

        System.out.println(bookMapper.getBooks());
        System.out.println(bookMapper.getBooks());
        System.out.println(bookMapper.getBooks());
        session.close();
    }
}
