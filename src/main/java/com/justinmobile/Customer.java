/**
 * @author dyc
 * com.justinmobile
 * Customer.java
 * 
 * 2016年5月11日-下午5:22:18
 *  2016XX公司-版权所有
 * 
 */
package com.justinmobile;


public class Customer {

	public static void main(String[] args) {/*

		String user = ActiveMQConnection.DEFAULT_USER;

		String password = ActiveMQConnection.DEFAULT_PASSWORD;

		String url = ActiveMQConnection.DEFAULT_BROKER_URL;

		String subject = "test.queue";

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);

		Connection connection;

		try {

			connection = connectionFactory.createConnection();

			connection.start();

			final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue(subject);

			MessageConsumer message = session.createConsumer(destination);

			message.setMessageListener(new MessageListener() {

				public void onMessage(Message msg) {

					MapMessage message = (MapMessage) msg;

					try {

						System.out.println("--收到消息：" + new Date() + "--" + message.getInt("va"));
						if (message.getInt("va") == 15) {
							session.rollback();
						} else
							session.commit();

					} catch (JMSException e) {

						e.printStackTrace();

					}

				}

			});

			Thread.sleep(30000);

			session.close();

			Thread.sleep(30000);

			connection.close();

			Thread.sleep(30000);

		} catch (JMSException e) {

			e.printStackTrace();

		} catch (InterruptedException e) {

			e.printStackTrace();

		}

	*/}

}
