package com.mrgan.springboot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.mrgan.springboot.config.ConnectionSettings;

@SpringBootApplication
/*
 * @SpringBootApplication is a convenience annotation that adds all of the
 * following:
 * 
 * @Configuration tags the class as a source of bean definitions for the
 * application context.
 * 
 * @EnableAutoConfiguration tells Spring Boot to start adding beans based on
 * classpath settings, other beans, and various property settings. Normally you
 * would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it
 * automatically when it sees spring-webmvc on the classpath. This flags the
 * application as a web application and activates key behaviors such as setting
 * up a DispatcherServlet.
 * 
 * @ComponentScan tells Spring to look for other components, configurations, and
 * services in the the hello package, allowing it to find the HelloController.
 */
public class Application implements EmbeddedServletContainerCustomizer {
	private static Logger logger = LogManager.getLogger(Application.class
			.getName());

	@Bean
	@ConfigurationProperties(prefix = "connection")
	public ConnectionSettings connectionSettings() {
		return new ConnectionSettings();

	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class);
		String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
		for (String profile : activeProfiles) {
			System.out.printf("Spring Boot 使用profile为:%s\n", profile);
		}
		logger.info("hahahahahahaha");
		// System.out.println("Let's inspect the beans provided by Spring Boot:");
		// String[] beanNames = ctx.getBeanDefinitionNames();
		// Arrays.sort(beanNames);
		// for (String beanName : beanNames) {
		// System.out.println(beanName);
		// }
	}

	public void customize(ConfigurableEmbeddedServletContainer container) {
		// TODO Auto-generated method stub
		// container.setPort(2046);
		/*
		 * 此处会覆盖application.properties
		 * server详细配置org/springframework/boot/autoconfigure/web/ServerProperties
		 */
		// container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,
		// "/screen/error")); //配置error页面
	}
}
