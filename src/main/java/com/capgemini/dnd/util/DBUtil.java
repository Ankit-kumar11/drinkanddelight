package com.capgemini.dnd.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DBUtil {
	private static Connection conn = null;
	private static DBUtil instance = null;
	private static Properties props = null;
	private static MysqlDataSource dataSource = null;


	/*************************************************************************************
	 *  - Private Constructor
	 *  - Author : CAPGEMINI 
	 *  - Desc:Loads the  jdbc.properties file and Driver Class and gets the connection
	 ***************************************************************************************/
	private DBUtil() throws Exception {
		try {
			props = loadProperties();
			dataSource = prepareDataSource();
		} catch (IOException e) {
			throw new Exception(
					" Could not read the database details from properties file ");
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}

	}

	/*****************************************************************
	 *  - Method Name:getInstance() 
	 *  - Input Parameters : 
	 *  - Return Type :DBConnection instance
	 *  - Throws : DonorException 
	 *  - Author : CAPGEMINI 
	 *  - Description : Singleton and Thread safe class
	 *******************************************************************/
	
	public static DBUtil getInstance() throws Exception {
		synchronized (DBUtil.class) {
			if (instance == null) {
				instance = new DBUtil();
			}
		}
		return instance;
	}
	
	/*****************************************************************
	 *  - Method Name:getConnection() 
	 *  - Input Parameters : 
	 *  - Return Type :DBConnection instance 
	 *  - Author : CAPGEMINI 
	 *  - Description :  Returns connection object
	 *******************************************************************/
	public Connection getConnection() throws Exception {
		try {

			conn = dataSource.getConnection();

		} catch (SQLException e) {
			throw new Exception(" Database connection problem");
		}
		return conn;
	}
	
	/*****************************************************************
	 *  - Method Name:loadProperties()
	 *  - Input Parameters : 
	 *  - Return Type :Properties object
	 *  - Author : CAPGEMINI 
	 *  - Description : Returns Properties object
	 *******************************************************************/
	
	private Properties loadProperties() throws IOException {

		if (props == null) {
			Properties newProps = new Properties();
			String fileName = "resources/jdbc.properties";

			InputStream inputStream = new FileInputStream(fileName);
			newProps.load(inputStream);

			inputStream.close();

			return newProps;
		} else {
			return props;
		}
	}


	/*****************************************************************
	 *  - Method Name:prepareDataSource() 
	 *  - Input Parameters : 
	 *  - Return Type :MysqlSource object
	 *  - Author : CAPGEMINI 
	 *  - Description : Returns MysqlSource object
	 *******************************************************************/
	private MysqlDataSource prepareDataSource() throws SQLException {

		if (dataSource == null) {
			if (props != null) {
				String connectionURL = props.getProperty("dburl");
				String username = props.getProperty("username");
				String password = props.getProperty("password");

				dataSource = new MysqlDataSource();
				dataSource.setURL(connectionURL);
				dataSource.setUser(username);
				dataSource.setPassword(password);
			}
		}
		return dataSource;
	}
	public static Date stringtoDate(java.util.Date m_d) {
        return new Date(m_d.getTime());
        
    }

 

    public static long diffBetweenDays(Date afterDate, Date beforeDate) {
        long diff = afterDate.getTime() - beforeDate.getTime();
        return  TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}


