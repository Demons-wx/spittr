package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
@ComponentScan("spittr.web")
public class WebConfig extends WebMvcConfigurerAdapter {
	
	/* 它会查找JSP文件，在查找的时候，它会在视图名称上加一个特定的前缀和后缀
	 * 例如：名为home的视图将被解析为/WEB-INF/views/home.jsp */
	@Bean
	public ViewResolver viewResolver() { // 配置JSP视图解析器
		InternalResourceViewResolver resolver = new InternalResourceViewResolver(); 
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	/* 通过调用DefaultServletHandlerConfigurer的enable()方法，我们要求DispatcherServlet将对静态资源
	 * 的请求转发到Servlet容器中默认的Servlet上，而不是使用DispatcherServlet本身来处理此类请求 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) { // 配置静态资源的处理
		configurer.enable();
	}
}