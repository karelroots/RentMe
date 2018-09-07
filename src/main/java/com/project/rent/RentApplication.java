package com.project.rent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

@SpringBootApplication
public class RentApplication {

	public static void main(String[] args) throws Exception {
        SpringApplication.run(RentApplication.class, args);

        /*Connection conn = SQLConnector.connect("postgres", "sql");


        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Kasutajad WHERE id = 1");
       *//* while (rs.next())
        {
            System.out.print("Column 1 returned ");
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getString(4));
        }*//*
        rs.close();
        st.close();*/

	}

   /* @Bean
    public ViewResolver viewResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("XHTML");
        templateResolver.setPrefix("views/");
        templateResolver.setSuffix(".html");

        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver);

        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(engine);
        return viewResolver;
    }*/
}
