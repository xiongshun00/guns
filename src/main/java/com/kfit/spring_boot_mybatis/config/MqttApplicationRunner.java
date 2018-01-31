//package com.kfit.spring_boot_mybatis.config;
//
//
//import java.net.URISyntaxException;
//
//import org.fusesource.mqtt.client.Future;
//import org.fusesource.mqtt.client.FutureConnection;
//import org.fusesource.mqtt.client.MQTT;
//import org.fusesource.mqtt.client.Message;
//import org.fusesource.mqtt.client.QoS;
//import org.fusesource.mqtt.client.Topic;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.stylefeng.guns.modular.biz.service.ITestService;
//
//@Component
//@Order(value = 1)
//public class MqttApplicationRunner implements ApplicationRunner{
//	private final static String CONNECTION_STRING = "tcp://121.40.238.123:1883";
//	private final static boolean CLEAN_START = true;
//	private final static short KEEP_ALIVE = 30;// 低耗网络，但是又需要及时获取数据，心跳30s
//	private final static String CLIENT_ID = "0865352030262521";
//	public static Topic[] topics = { new Topic("/iot/usr/363/app/f72554ca-2d46-48c4-a19d-6bd0fb00507d", QoS.EXACTLY_ONCE) };
//
//	public final static long RECONNECTION_ATTEMPT_MAX = 6;
//	public final static long RECONNECTION_DELAY = 2000;
//
//	public final static int SEND_BUFFER_SIZE = 2 * 1024 * 1024;// 发送最大缓冲为2M
//	
//	@Autowired
//	ITestService testService;
//	
//	@Override
//	public void run(ApplicationArguments arg0) throws Exception {
//		// 创建MQTT对象
//		MQTT mqtt = new MQTT();
//		try {
//			// 设置mqtt broker的ip和端口
//			mqtt.setHost(CONNECTION_STRING);
//			// 连接前清空会话信息
//			mqtt.setCleanSession(CLEAN_START);
//			// 设置重新连接的次数
//			mqtt.setReconnectAttemptsMax(RECONNECTION_ATTEMPT_MAX);
//			// 设置重连的间隔时间
//			mqtt.setReconnectDelay(RECONNECTION_DELAY);
//			// 设置心跳时间
//			mqtt.setKeepAlive(KEEP_ALIVE);
//			// 设置缓冲的大小
//			mqtt.setSendBufferSize(SEND_BUFFER_SIZE);
//			// 设置客户端id
//			mqtt.setClientId(CLIENT_ID);
//			mqtt.setUserName("alkx2017");// 服务器认证用户名
//			mqtt.setPassword("123456");// 服务器认证密码
//			// 获取mqtt的连接对象BlockingConnection
//			final FutureConnection connection = mqtt.futureConnection();
//			connection.connect();
//			connection.subscribe(topics);
//			while (true) {
//				Future<Message> futrueMessage = connection.receive();
//				Message message = futrueMessage.await();
//				System.out.println("MQTTFutureClient.Receive Message " + "Topic Title :" + message.getTopic()
//						+ " context :" + String.valueOf(message.getPayloadBuffer()));
//				testService.testBiz();//这里可以引用springbean的方法
//			}
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//		}		
//	}
//
//}