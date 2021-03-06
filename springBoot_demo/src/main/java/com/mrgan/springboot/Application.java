package com.mrgan.springboot;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mrgan.springboot.config.ConnectionSettings;
import com.mrgan.springboot.thrift.Calculator;
import com.mrgan.springboot.thrift.CalculatorHandler;

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
// swagger2
public class Application extends WebMvcConfigurerAdapter implements
		EmbeddedServletContainerCustomizer {
	private static Logger logger = LogManager.getLogger(Application.class
			.getName());

	// @Bean
	// public Docket api() {
	// return new Docket(DocumentationType.SWAGGER_2).select() //
	// 选择那些路径和api会生成document
	// .apis(RequestHandlerSelectors.any()) // 对所有api进行监控
	// .paths(PathSelectors.any()) // 对所有路径进行监控
	// .build();
	// }

	@Bean
	@ConfigurationProperties(prefix = "connection")
	public ConnectionSettings connectionSettings() {
		return new ConnectionSettings();

	}

	@Bean
	// 这方法不加也行
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class);
		String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
		for (String profile : activeProfiles) {
			System.out.printf("Spring Boot 使用profile为:%s\n", profile);
		}
		logger.info("hahahahahahaha");
		// System.out.println("Let's inspect the beans provided by Spring
		// Boot:");
		// String[] beanNames = ctx.getBeanDefinitionNames();
		// Arrays.sort(beanNames);
		// for (String beanName : beanNames) {
		// System.out.println(beanName);
		// }
		initServer();
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

	private static void initServer() {
		try {
			Calculator.Iface serviceInterface = new CalculatorHandler();

			// Create the processor
			TProcessor processor = new Calculator.Processor<>(serviceInterface);
			TServerTransport serverTransport = new TServerSocket(9090);
			// TServer server = new TSimpleServer(
			// new Args(serverTransport).processor(processor));

			// Use this for a multithreaded server
			TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(
					serverTransport).processor(processor));

			System.out.println("Starting the simple server...");
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HandlerInterceptorAdapter() {
			@Override
			public void postHandle(HttpServletRequest request,
					HttpServletResponse response, Object handler,
					ModelAndView modelAndView) throws Exception {
				// TODO Auto-generated method stub
				System.out.println(request.getContextPath());
				System.out.println("2interceptor====");
				super.postHandle(request, response, handler, modelAndView);
			}

			@Override
			public boolean preHandle(HttpServletRequest request,
					HttpServletResponse response, Object handler)
					throws Exception {
				System.out.println(request.getContextPath());
				System.out.println("interceptor====");
				return false;
			}
		}).addPathPatterns("/pages/*");
	}
}
