package com.mrgan.demo.webrtc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter implements EmbeddedServletContainerCustomizer {
	private static Logger logger = LogManager.getLogger(Application.class.getName());
	@Value("${im.server.host}")
	private String host;

	@Value("${im.server.port}")
	private Integer port;

	@Bean
	public SocketIOServer socketIOServer() {
		Configuration config = new Configuration();
		config.setHostname(host);
		config.setPort(port);

		final SocketIOServer server = new SocketIOServer(config);
		server.addNamespace("/live").addConnectListener(new ConnectListener() {
			@Override
			public void onConnect(SocketIOClient client) {
				// TODO Auto-generated method stub
				String room = client.getHandshakeData().getSingleUrlParam("room");
				System.out.println(room);
			}
		});
		server.addNamespace("/live").addEventListener("message", Object.class, new DataListener<Object>() {

			@Override
			public void onData(SocketIOClient client, Object data, AckRequest ackSender) throws Exception {
				System.out.println(data);
			}
		});
		server.addNamespace("/live").addDisconnectListener(new DisconnectListener() {
			@Override
			public void onDisconnect(SocketIOClient client) {
			}
		});
		server.start();
		return server;
	}

	@Bean
	public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
		return new SpringAnnotationScanner(socketServer);
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class);
	}

	public void customize(ConfigurableEmbeddedServletContainer container) {
		// TODO Auto-generated method stub
		container.setPort(2046);
	}
}
