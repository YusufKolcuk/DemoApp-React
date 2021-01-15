package com.xperta.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
	
	private static final Logger logger = LoggerFactory.getLogger(Log.class);
	
	public static void warn (String msg) {
		logger.warn(msg);
	}

	public static void debug (String msg) {
		logger.debug(msg);
	}
	
	public static void info (String msg) {
		logger.info(msg);
	}
}
