package com.foodnicherest.server;

import com.foodnicherest.properties.ServerPropertyLoader;
import org.glassfish.api.deployment.DeployCommandParameters;
import org.glassfish.api.deployment.archive.ReadableArchiveFactory;
import org.glassfish.internal.embedded.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by ujuezeoke on 19/11/2015.
 */
public class FoodNicheRestServer {

    private final Server server;
    private final Port port;
    private final DeployCommandParameters deployCommandParameters;

    public FoodNicheRestServer(ServerPropertyLoader serverPropertyLoader) {
        EmbeddedFileSystem.Builder efsb = new EmbeddedFileSystem.Builder();
//        efsb.autoDelete(true);
//        efsb.configurationFile(serverPropertyLoader.getDomainXmlFile())
//        .installRoot(serverPropertyLoader.getEmbeddedFileSystemRoot())
//        .instanceRoot(serverPropertyLoader.getEmbeddedFileSystemRoot());
        EmbeddedFileSystem efs = efsb.build();

        server = buildServer(serverPropertyLoader, efs);
        server.addContainer(ContainerBuilder.Type.web);

        deployCommandParameters = new DeployCommandParameters();
        deployCommandParameters.setContextRoot(serverPropertyLoader.getContextRoot());
        port = setupPort(serverPropertyLoader);
    }

    private Port setupPort(ServerPropertyLoader serverPropertyLoader) {
        try {
            return server.createPort(serverPropertyLoader.getServerPort());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void start() {
        try {
//            new File("/Users/ujuezeoke/trunk/FoodNicheRest/src/main/webapp")
            server.start();
            server.getDeployer().deploy(new File("/Users/ujuezeoke/trunk/FoodNicheRest/target/FoodNicheRest-1.0-SNAPSHOT.war"), deployCommandParameters);
            System.out.println(server);
        } catch (LifecycleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void stop(){
        try {
            server.getDeployer().undeployAll();
            server.stop();
        } catch (LifecycleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Server buildServer(ServerPropertyLoader serverPropertyLoader, EmbeddedFileSystem efs) {
        return new Server.Builder(serverPropertyLoader.getServerId()).jmxPort(serverPropertyLoader.getJmxPort())
                .verbose(serverPropertyLoader.isServerVerbose())
                .logger(serverPropertyLoader.isLoggerEnabled())
                .logFile(serverPropertyLoader.getLogFile())
                .embeddedFileSystem(efs)
                .build();
    }
}
