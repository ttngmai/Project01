package com.project.lsh.config;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.lsh.beans.UserBean;
import com.project.lsh.interceptor.CheckAuthStatusInterceptor;
import com.project.lsh.interceptor.CheckCommentWriterInterceptor;
import com.project.lsh.interceptor.CheckContentWriterInterceptor;
import com.project.lsh.interceptor.CheckLoginInterceptor;
import com.project.lsh.interceptor.TopNavInterceptor;
import com.project.lsh.mapper.AuthMapper;
import com.project.lsh.mapper.BoardMapper;
import com.project.lsh.mapper.CommentMapper;
import com.project.lsh.mapper.LikeMapper;
import com.project.lsh.mapper.TopNavMapper;
import com.project.lsh.mapper.UserMapper;
import com.project.lsh.service.BoardService;
import com.project.lsh.service.CommentService;
import com.project.lsh.service.TopNavService;

@Configuration
@EnableWebMvc
@ComponentScan("com.project.lsh.controller")
@ComponentScan("com.project.lsh.dao")
@ComponentScan("com.project.lsh.service")
@PropertySource("/WEB-INF/properties/db.properties")
public class ServletAppContext implements WebMvcConfigurer {
	@Value("${db.classname}")
	private String db_classname;
	@Value("${db.url}")
	private String db_url;
	@Value("${db.username}")
	private String db_username;
	@Value("${db.password}")
	private String db_password;
	@Autowired
	private TopNavService topNavService;
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	@Autowired
	private BoardService boardService;
	@Autowired
	private CommentService commentService;
	
	// Controller의 메서드가 반환하는 JSP의 이름 앞뒤에 경로와 확장자를 붙혀주도록 설정한다.
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	// 정적 파일의 경로를 Mapping한다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	}
	
	// DB 접속 정보를 관리하는 객체
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource source = new BasicDataSource();
		
		source.setDriverClassName(db_classname);
		source.setUrl(db_url);
		source.setUsername(db_username);
		source.setPassword(db_password);
		
		return source;
	}
	
	// 쿼리문과 접속 정보를 관리하는 객체
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		
		factoryBean.setDataSource(source);
		
		SqlSessionFactory factory = factoryBean.getObject();
		
		return factory;
	}
	
	// 쿼리문 실행을 위한 객체(Mapper 관리)
	@Bean
	public MapperFactoryBean<BoardMapper> getBoardMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<BoardMapper> factoryBean = new MapperFactoryBean<BoardMapper>(BoardMapper.class);
		
		factoryBean.setSqlSessionFactory(factory);
		
		return factoryBean;
	}
	
	@Bean
	public MapperFactoryBean<TopNavMapper> getTopNavMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<TopNavMapper> factoryBean = new MapperFactoryBean<TopNavMapper>(TopNavMapper.class);
		
		factoryBean.setSqlSessionFactory(factory);
		
		return factoryBean;
	}
	
	@Bean
	public MapperFactoryBean<UserMapper> getUserMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<UserMapper> factoryBean = new MapperFactoryBean<UserMapper>(UserMapper.class);
		
		factoryBean.setSqlSessionFactory(factory);
		
		return factoryBean;
	}
	
	@Bean
	public MapperFactoryBean<AuthMapper> getAuthMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<AuthMapper> factoryBean = new MapperFactoryBean<AuthMapper>(AuthMapper.class);
		
		factoryBean.setSqlSessionFactory(factory);
		
		return factoryBean;
	}
	
	@Bean
	public MapperFactoryBean<CommentMapper> getCommentMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<CommentMapper> factoryBean = new MapperFactoryBean<CommentMapper>(CommentMapper.class);
		
		factoryBean.setSqlSessionFactory(factory);
		
		return factoryBean;
	}
	
	@Bean
	public MapperFactoryBean<LikeMapper> getLikeMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<LikeMapper> factoryBean = new MapperFactoryBean<LikeMapper>(LikeMapper.class);
		
		factoryBean.setSqlSessionFactory(factory);
		
		return factoryBean;
	}
	
	// Interceptor를 등록한다.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
		
		TopNavInterceptor topNavInterceptor = new TopNavInterceptor(topNavService, loginUserBean);
		InterceptorRegistration reg1 = registry.addInterceptor(topNavInterceptor);
		reg1.addPathPatterns("/**");
		
		CheckLoginInterceptor checkLoginInterceptor = new CheckLoginInterceptor(loginUserBean);
		InterceptorRegistration reg2 = registry.addInterceptor(checkLoginInterceptor);
		reg2.addPathPatterns("/user/modify", "/user/logout", "/board/*");
		reg2.excludePathPatterns("/board/main");
		
		CheckAuthStatusInterceptor checkAuthStatusInterceptor = new CheckAuthStatusInterceptor(loginUserBean);
		InterceptorRegistration reg3 = registry.addInterceptor(checkAuthStatusInterceptor);
		reg3.addPathPatterns("/board/*");
		reg3.excludePathPatterns("/board/main");
		
		CheckContentWriterInterceptor checkContentWriterInterceptor = new CheckContentWriterInterceptor(loginUserBean, boardService);
		InterceptorRegistration reg4 = registry.addInterceptor(checkContentWriterInterceptor);
		reg4.addPathPatterns("/board/modify", "/board/delete");
		
		CheckCommentWriterInterceptor checkCommentWriterInterceptor = new CheckCommentWriterInterceptor(loginUserBean, commentService);
		InterceptorRegistration reg5 = registry.addInterceptor(checkCommentWriterInterceptor);
		reg5.addPathPatterns("/comment/modify", "/comment/delete");
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
		
		res.setBasename("/WEB-INF/properties/error_message");
		
		return res;
	}
	
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
}
