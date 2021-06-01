package com.p1.util;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.*;

public class Log2File {
	
	static Logger logger = LogManager.getLogger();
	public static void logThis(String type, String str) {
		
		switch (type) {
		case "warn":
			logger.warn(str);
			break;
		case "debug":
			logger.debug(str);
			break;
		case "trace":
			logger.trace(str);
			break;
		case "fatal":
			logger.fatal(str);
			break;
		case "info":
			logger.info(str);
			break;
		case "error":
			logger.error(str);
			break;
		default:
			System.out.println("Undefined type");
		}
	}
}