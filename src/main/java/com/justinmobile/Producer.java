/**
 * @author dyc
 * com.justinmobile
 * Producer.java
 * 
 * 2016年5月11日-下午5:18:29
 *  2016XX公司-版权所有
 * 
 */
package com.justinmobile;


public class Producer {/*

	public static void main(String[] args) {

		String user = ActiveMQConnection.DEFAULT_USER;

		String password = ActiveMQConnection.DEFAULT_PASSWORD;

		String url = ActiveMQConnection.DEFAULT_BROKER_URL;

		String subject = "test.queue";

		ConnectionFactory contectionFactory = new ActiveMQConnectionFactory(user, password, url);

		try {

			Connection connection = contectionFactory.createConnection();

			connection.start();

			Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue(subject);

			MessageProducer producer = session.createProducer(destination);

			for (int i = 0; i <= 20; i++) {

				MapMessage message = session.createMapMessage();

				Date date = new Date();

				message.setLong("count", date.getTime());

				Thread.sleep(100);
				message.setInt("va", i);
				producer.send(message);

				System.out.println("--发送消息：" + date);

			}

			Thread.sleep(200);

			session.commit();

			session.close();

			connection.close();

		} catch (JMSException e) {

			e.printStackTrace();

		} catch (InterruptedException e) {

			e.printStackTrace();

		}

	}
*/}
