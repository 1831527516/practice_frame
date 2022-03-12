package com.frame.test;


import com.frame.bean.User;
import com.frame.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyBatisTest {

    public static void main(String[] args) throws Exception {
        // 1.读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        // 3.工厂创建SqlSession对象
        SqlSession session = sqlSessionFactory.openSession();
        // 4.使用SqlSession创建mapper接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
        // 5.使用代理对象执行方法
        //findAll(userMapper);
        //addUser(session, userMapper);
        User user = new User();
        user.setSex("女");
        user.setId(4);
        Integer updateUser = userMapper.updateUser(user);
        System.out.println(updateUser);
        session.commit();
        // 6.释放资源
        session.close();
        inputStream.close();
    }

    private static void addUser(SqlSession session, UserMapper userMapper) throws ParseException {
        User user = new User();
        user.setUsername("李");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String str = "1999-02-03";
        Date date =  dateFormat.parse(str);
                user.setBirthday(date);
        user.setSex("女");
        user.setAddress("湖南省永州市冷水滩区");
        Integer addUser = userMapper.addUser(user);
        System.out.println(addUser);
        session.commit();
    }

    private static void findAll(UserMapper userMapper) {
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
