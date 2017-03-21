package spittr.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {RootConfig.class};
	}

	protected Class<?>[] getServletConfigClasses() { // 指定配置类
		return new Class<?>[] {WebConfig.class};
	}
	
	protected String[] getServletMappings() {	// 将DispatcherServlet映射到"/"
		return new String[] { "/" };
	}

}

