package burp;

import java.io.OutputStream;
import java.io.PrintWriter;

public class LogWriter
{
    final public static int DEBUG_LEVEL = 0;
    final public static int INFO_LEVEL = 1;
    final public static int ERROR_LEVEL = 2;
    final public static int FATAL_LEVEL = 3;

    private PrintWriter out;
    private PrintWriter err;
    private int logLevel;

    public static String levelNameFromInt(final int level)
    {
        switch (level) {
            case LogWriter.DEBUG_LEVEL:
                return "debug";
            case LogWriter.INFO_LEVEL:
                return "info";
            case LogWriter.ERROR_LEVEL:
                return "error";
            case LogWriter.FATAL_LEVEL:
                return "fatal";
        }
        return "*INVALID*";
    }

    public LogWriter(OutputStream outStream, OutputStream errStream, int logLevel)
    {
        this.out = new PrintWriter(outStream, true);
        this.err = new PrintWriter(errStream, true);
        this.logLevel = logLevel;
    }

    public void setLevel(int level) { this.logLevel = level; }

    public int getLevel() { return this.logLevel; }

    private void log(final String message, int level)
    {
        if (this.logLevel <= level) {
            if (this.logLevel >= ERROR_LEVEL) {
                this.err.println(message);
            }
            else {
                this.out.println(message);
            }
        }
    }

    public void debug(final String message)
    {
        log(message, DEBUG_LEVEL);
    }

    public void info(final String message)
    {
        log(message, INFO_LEVEL);
    }

    public void error(final String message)
    {
        log(message, ERROR_LEVEL);
    }

    public void fatal(final String message)
    {
        log(message, FATAL_LEVEL);
    }
}