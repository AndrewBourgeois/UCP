// ========================================================================
// LOGBack configuration file for the tests
// ========================================================================
// =======================================================
// Useful API
// =======================================================
// root(Level level, List<String> appenderNames = [])
// logger(String name, Level level, List<String> appenderNames = [], Boolean additivity = null)
// appender(String name, Class clazz, Closure closure = null)

// =======================================================
// Required imports
// =======================================================
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.FileAppender
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.classic.html.HTMLLayout
import ch.qos.logback.core.encoder.LayoutWrappingEncoder
import static ch.qos.logback.classic.Level.*

// =======================================================
// Appender that will log to STDOUT
// =======================================================
appender("rootLogConsole", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%date{yyyy-MM-dd HH:mm:ss,SSS} [%thread] %-5level - \\(%logger\\) - %message%n"
  }
}

// =======================================================
// Rootlogger: Every log goes there by default. 
// It uses the rootLog appender to log to a file
// =======================================================
//root(INFO, ["rootLog", "rootLogHtml"])
root(DEBUG, ["rootLogConsole"])
//
// =======================================================
// Example of another logger that can be used to log transactions
// =======================================================
//logger("be.demmel.protocol.ucp.logging.TransactionLogger", INFO, ["txnLog"], false)

// =======================================================
// Example of a logger that's declared just to limit the verbose of a package
// =======================================================
// logger("org.hibernate", WARN, ["rootLogConsole"], false)