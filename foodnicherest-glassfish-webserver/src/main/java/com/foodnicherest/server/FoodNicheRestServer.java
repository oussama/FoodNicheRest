package com.foodnicherest.server;

import com.foodnicherest.properties.ServerPropertyLoader;
import org.glassfish.embeddable.*;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ujuezeoke on 19/11/2015.
 */
public class FoodNicheRestServer {
    private final GlassFishProperties glassfishProperties;
    private GlassFish glassfish;
    private ServerPropertyLoader serverPropertyLoader;

    public FoodNicheRestServer(ServerPropertyLoader serverPropertyLoader) {
        this.serverPropertyLoader = serverPropertyLoader;
        glassfishProperties = new GlassFishProperties();
        glassfishProperties.setPort("http-listener", serverPropertyLoader.getServerHttpPort());
        glassfishProperties.setPort("https-listener", serverPropertyLoader.getServerHttpsPort());
    }

    public void start() {
        try {
            glassfish = GlassFishRuntime.bootstrap().newGlassFish(glassfishProperties);
            controllingTheLogs();

            glassfish.start();
            performAdminTasks();

            Deployer deployer = glassfish.getDeployer();
            serverPropertyLoader.getDeployResources()
                    .stream()
                    .filter(File::exists)
                    .forEach(deployFile(deployer));
        } catch (GlassFishException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Embedded Glassfish server has started at http://localhost:%d %n%n", serverPropertyLoader.getServerHttpPort());
    }


    private Consumer<File> deployFile(Deployer deployer) {
        return file -> {
            try {
                deployer.deploy(file);
            } catch (GlassFishException e) {
                throw new RuntimeException(String.format("Cannot deploy '%s'", file.getName()),e);
            }
        };
    }

    private void controllingTheLogs() {
        Logger.getLogger("").getHandlers()[0].setLevel(Level.OFF);
        Logger.getLogger("javax.enterprise.system.tools.deployment").setLevel(Level.OFF);
        Logger.getLogger("javax.enterprise.system").setLevel(Level.OFF);
    }

    private void performAdminTasks() throws GlassFishException {
        CommandRunner commandRunner = glassfish.getCommandRunner();
        commandResultPrinter(commandRunner.run("list-jdbc-resources"));
        commandResultPrinter(commandRunner.run("create-jdbc-connection-pool", "--host", "localhost", "--port", "5432",
                "--datasourceclassname", "org.postgresql.ds.PGConnectionPoolDataSource",
                "--restype", "javax.sql.ConnectionPoolDataSource", "postgress-connection-pool"));

        commandResultPrinter(commandRunner.run("add-resources", glassfishResourceLocation()));
        commandResultPrinter(commandRunner.run("list-jdbc-resources"));

    }

    private String glassfishResourceLocation() {
        try {
            return new File(".", "foodnicherest-core/src/main/resources/META-INF/glassfish-resources.xml").getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException("Could not find glassfish-resource.xml", e);
        }
    }

    private void commandResultPrinter(CommandResult commandResult) {
        Optional<String> failureCause = Optional.ofNullable(commandResult.getFailureCause()).map(Throwable::getMessage);
        Optional<String> exitStatus = Optional.ofNullable(commandResult.getExitStatus().name());

        System.out.printf("%n Output: '%s'%nExitStatus: '%s'%nFailure Cause: '%s'%n%n",
                commandResult.getOutput(), exitStatus.orElse("No Exist Status"), failureCause.orElse("No Failure Cause"));
    }

    public void stop() {
        if (glassfish != null) {
            try {
                glassfish.dispose();
            } catch (GlassFishException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
