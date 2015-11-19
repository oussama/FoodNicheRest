package com.foodnicherest.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

/**
 * Created by ujuezeoke on 18/11/2015.
 */
public class DevTestRunner {

    public static final int PORT = 3422;
    public static final String PROJECT_FOLDER_NAME = "FoodNicheRest";
    private final Server server = new Server(PORT);

    public void start(){

        WebAppContext context = new WebAppContext();

        context.setResourceBase(findResourceBase());
        context.setContextPath("/");
        context.setParentLoaderPriority(true);

        server.setHandler(context);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String findResourceBase() {
        URL currentLocationUrl = DevTestRunner.class.getResource(".");
        Path currentPath = new File(currentLocationUrl.getFile()).toPath();

        String parentPath = IntStream.range(0, currentPath.getNameCount())
                .filter(isRootFolder(currentPath))
                .mapToObj(index -> currentPath.getName(index).toAbsolutePath().toFile())
                .findAny()
                .orElseThrow(() -> new RuntimeException("Cannot find project folder"))
                .getParent();

        return parentPath + "/src/main/webapp";
    }

    private IntPredicate isRootFolder(Path currentPath) {
        return index -> {
            Path name = currentPath.getName(index);
            return name.toAbsolutePath().toFile().getParent().endsWith(PROJECT_FOLDER_NAME) && name.toFile().isDirectory();
        };
    }

    public static void main(String[] args) {
        new DevTestRunner().start();
    }
}
