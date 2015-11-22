package com.foodnicherest;

import com.foodnicherest.properties.ServerPropertyLoader;
import com.foodnicherest.server.FoodNicheRestServer;
import com.foodnicherest.server.YetAnotherServer;

import java.io.File;
import java.net.URL;

/**
 * Created by ujuezeoke on 19/11/2015.
 */
public class FoodNicheRestDevRunner {
    public static void main(String[] args) {
        FoodNicheRestServer foodNicheRestServer = new FoodNicheRestServer(new TestServerPropertyLoader());
        System.out.println("About to start");
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
        public Integer getServerHttpPort() {
            return 31831;
        }

        @Override
        public String getContextRoot() {
            return "/";
        }

        @Override
        public File getEmbeddedFileSystemRoot() {
           throw new UnsupportedOperationException("Nothing calls you");
        }

        @Override
        public File getDomainXmlFile() {
            throw new UnsupportedOperationException("Nothing calls you");
        }

        @Override
        public Integer getServerHttpsPort() {
            return 31830;
        }

        @Override
        public File getDeployResource() {
            return new File(".", "artifacts/FoodNicheRest.war");
        }
    }
}
