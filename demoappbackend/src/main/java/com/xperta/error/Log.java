package com.xperta.error;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

/**
 * 
 * class to handle logging
 * <p/>
 * Created on 05.09.2019
 *
 * @author kuec
 *
 */
public class Log {
  
  private static Logger logger;
  
  static{
    try {
      logger = Logger.getRootLogger();
      SimpleLayout layout = new SimpleLayout();
      ConsoleAppender consoleAppender = new ConsoleAppender( layout );
      logger.addAppender( consoleAppender );  
      
        FileAppender fileAppender = new FileAppender( layout, "c:/tmp/demoapp.log", false );
        logger.addAppender( fileAppender );
           
      // ALL | DEBUG | INFO | WARN | ERROR | FATAL | OFF:
      logger.setLevel( Level.DEBUG );
    } catch( Exception ex ) {
      logger.error(ex);
    }    
  }
  
  public static void debug(String msg){
    logger.debug(msg);
  }

  public static void info(String msg){
    logger.info(msg);
  }

  public static void warn(String msg){
    logger.warn(msg);
  }
  
  public static void error(String msg){
    logger.error(msg);
  }
}
