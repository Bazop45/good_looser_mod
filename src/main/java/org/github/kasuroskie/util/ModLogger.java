package org.github.kasuroskie.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for obtaining loggers in a consistent manner.
 * Provides a centralized way to get loggers for different classes.
 */
public class ModLogger {
    private ModLogger() {
        // Prevent instantiation
    }

    /**
     * Gets a logger for the specified class.
     *
     * @param clazz The class for which to obtain a logger
     * @return A logger instance
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    /**
     * Gets a logger with the specified name.
     *
     * @param name The name for the logger
     * @return A logger instance
     */
    public static Logger getLogger(String name) {
        return LoggerFactory.getLogger(name);
    }
}