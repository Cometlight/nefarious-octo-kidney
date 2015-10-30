package at.fhv.itb5c.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

/**
 * HOW TO LOAD LOGGER:
 *
 * Add "implements ILogger" interface to class. The logger grabs an instance on his own.
 *
 * HOW TO USE LOGGER:
 *
 * Use "log" function and chose needed the level of logging. Examples:
 * log.info("his is a info message");
 * log.error("this is a error message");
 *
 * HOW TO USE & CHOSE LOGGER LEVELS (RULES):
 *
 * debug levels (developers stuff)
 * TRACE    The TRACE Level designates finer-grained informational events than the DEBUG.
 *          Use for: Very detailed information, intended only for development.
 *          After a feature has been developed and tested, this level should be turned off.
 * DEBUG    The DEBUG Level designates fine-grained informational events that are most useful to debug an application.
 *
 * information level (general)
 * INFO     The INFO level is associated with significant events in the normal life cycle of the application.
 *          Use for: Tells us what the application is doing, so for each action that changes the state of the
 *          application significantly (database update, external system request).
 *
 * error levels (error handling)
 * WARN     The WARN level designates potentially harmful situations.
 *          Use for: Missing or inconsistent input parameters supplied by the user. The process might be continued,
 *          but take extra caution.
 *ERROR     The ERROR level designates error events that might still allow the application to continue running.
 *          Use for: Something terribly wrong had happened, that must be investigated immediately (database unavailable,
 *          use case cannot be continued).Such events usually follows a Java exception.
 *FATAL     The FATAL level designates very severe error events that will presumably lead the application to abort.
 *          Use for: No system can tolerate this level :)
 *
 *others (not for use currently)
 *ALL       The ALL Level has the lowest possible rank and is intended to turn on all logging.
 *OFF       The OFF Level has the highest possible rank and is intended to turn off logging.
 *
 *
 */

public class Logger4J {
    private static final Logger loggerInstance = LogManager.getLogger(Logger4J.class);

    public static Logger getInstance() {
        //loggerInstance.info("[Logging initialized] name:" +  loggerInstance.getName() + " level:" + loggerInstance.getLevel().name());
        return loggerInstance;
    }

    /**
     *
     * @param log
     * @param level
     */
    public static void setLevel(Logger log, Level level) {
        // config stuff for changes during runtime (unused)
        LoggerContext ctx = (LoggerContext)LogManager.getContext(false);
        Configuration conf = ctx.getConfiguration();
        LoggerConfig lconf = conf.getLoggerConfig(log.getName());
        //Level oldLevel = lconf.getLevel();
        lconf.setLevel(level);
        ctx.updateLoggers(conf);
    }
}