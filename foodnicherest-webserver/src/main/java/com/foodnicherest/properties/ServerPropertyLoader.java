package com.foodnicherest.properties;

import java.io.File;

/**
 * Created by ujuezeoke on 19/11/2015.
 */
public interface ServerPropertyLoader {

    String getServerId();

    Integer getJmxPort();

    Boolean isServerVerbose();

    Boolean isLoggerEnabled();

    File getLogFile();

    Integer getServerPort();

    String getContextRoot();

    File getEmbeddedFileSystemRoot();

    File getDomainXmlFile();
}
