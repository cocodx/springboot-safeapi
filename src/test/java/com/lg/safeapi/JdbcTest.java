package com.lg.safeapi;

import com.lg.safeapi.entity.Book;
import org.junit.Test;

import java.sql.*;

/**
 * @author amazfit
 * @date 2022-07-16 下午3:47
 **/
public class JdbcTest {

    @Test
    public void connectedMysql(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //1，加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf-8&serverTimezone=GMT%2B8";
            String userName = "root";
            String password = "password";
            //2，创建连接
            Connection connection = DriverManager.getConnection(url,userName,password);

            //3,SQL语句
            String sql = "select * from book where id=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,1);

            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();

            resultSet.next();

            Book book = new Book();
            book.setId(resultSet.getInt("id"));
            book.setName(resultSet.getString("name"));
            book.setAuthor(resultSet.getString("author"));
            book.setDescription(resultSet.getString("description"));
            book.setPrice(resultSet.getDouble("price"));
            book.setCreateTime(resultSet.getDate("createTime"));

            System.out.println(book);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (conn!=null){
                    conn.close();
                }
                if (pstmt!=null){
                    pstmt.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
