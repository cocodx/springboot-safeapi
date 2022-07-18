package com.lg.safeapi;

import com.lg.safeapi.entity.Book;
import com.lg.safeapi.mapper.primary.BookMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

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

    @Test
    public void addStudent(){
        //自动提交事务
        SqlSession session = factory.openSession(true);
        BookMapper bookMapper = session.getMapper(BookMapper.class);
        System.out.println(bookMapper.getBooks());
        System.out.println("增加一本书："+bookMapper.insertBook(new Book(null,"人类简史","人类简史",100.0D,new Date(),"好看")));
        System.out.println(bookMapper.getBooks());
    }


    @Test
    public void testLocalCacheScope(){
        SqlSession session1 = factory.openSession(true);
        SqlSession session2 = factory.openSession(true);

        BookMapper bookMapper1 = session1.getMapper(BookMapper.class);
        BookMapper bookMapper2 = session2.getMapper(BookMapper.class);

        System.out.println("book1读取数据："+bookMapper1.getBooks());
        System.out.println("book2读取数据："+bookMapper2.getBooks());

        bookMapper1.updateBookById(new Book(4,"人类简史2"));

        System.out.println("book1读取数据："+bookMapper1.getBooks());
        System.out.println("book2读取数据："+bookMapper2.getBooks());

    }

    /**
     * 测试二级缓存效果，不提交事务，sqlSession1查询完数据后，sqlSession2相同的查询能否会从缓存中获取数据。
     */
    @Test
    public void testCacheWithoutCommitOrClose()throws Exception{
        SqlSession session1 = factory.openSession(true);
        SqlSession session2 = factory.openSession(true);
        SqlSession session3 = factory.openSession(true);

        BookMapper bookMapper1 = session1.getMapper(BookMapper.class);
        BookMapper bookMapper2 = session2.getMapper(BookMapper.class);
        BookMapper bookMapper3 = session3.getMapper(BookMapper.class);

        System.out.println("bookMapper1:"+bookMapper1.getBooks());
        //sqlsession1 提交事务
        session1.commit();
        System.out.println("bookMapper2:"+bookMapper2.getBooks());

        //测试update对二级缓存刷新，确实刷新
        bookMapper3.updateBookById(new Book(4,"人类简史2"));
        session3.commit();
        System.out.println("bookMapper2:"+bookMapper2.getBooks());

    }


}
