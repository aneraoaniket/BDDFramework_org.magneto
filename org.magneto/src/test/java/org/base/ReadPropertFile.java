package org.base;

import java.io.FileReader;

import java.util.Properties;

public class ReadPropertFile {
		Properties pro;

	public ReadPropertFile() {

		try {
			FileReader fr = new FileReader("./configuration/config.properties");
			pro = new Properties();
			pro.load(fr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  String getBrowser() {
		String browserName = pro.getProperty("browser");
		return browserName;
	}

	public  String getUrl() {
		String url = pro.getProperty("testUrl");
		return url;
	}

//	public static String getUserName() {
//		String userName = pro.getProperty("username");
//		return userName;
//	}
//
//	public static String getPassword() {
//		String password = pro.getProperty("password");
//		return password;
//	}
	public String getReportConfigPath(){
		String reportConfigPath = pro.getProperty("C:\\Eclips workspace-3\\ecommerceBDD.test\\src\\main\\resources\\extent-config.xml");
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}
}
