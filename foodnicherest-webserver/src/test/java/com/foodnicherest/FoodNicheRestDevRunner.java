package com.foodnicherest;

import com.foodnicherest.properties.ServerPropertyLoader;
import com.foodnicherest.server.FoodNicheRestServer;
import com.sun.enterprise.util.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by ujuezeoke on 19/11/2015.
 */
public class FoodNicheRestDevRunner {
    public static void main(String[] args) {
        FoodNicheRestServer foodNicheRestServer = new FoodNicheRestServer(new TestServerPropertyLoader());

        foodNicheRestServer.start();
    }

    private static class TestServerPropertyLoader implements ServerPropertyLoader {
        @Override
        public String getServerId() {
            return "test";
        }

        @Override
        public Integer getJmxPort() {
            return 15593;
        }

        @Override
        public Boolean isServerVerbose() {
            return true;
        }

        @Override
        public Boolean isLoggerEnabled() {
            return true;
        }

        @Override
        public File getLogFile() {
            return new File("test.log");
        }

        @Override
        public Integer getServerPort() {
            return 2334;
        }

        @Override
        public String getContextRoot() {
            return "/test";
        }

        @Override
        public File getEmbeddedFileSystemRoot() {
            return new File("/Users/ujuezeoke/trunk/FoodNicheRest/test-emmbeded");
        }

        @Override
        public File getDomainXmlFile() {
            return new File("/Users/ujuezeoke/trunk/FoodNicheRest/foodnicherest-webserver/src/main/resources/domain.xml");
        }
    }
}
