package com.zsxk.online.teacherservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@MapperScan("com.zsxk.online.teacherservice.mapper")
public class TeacherConfig {
}
