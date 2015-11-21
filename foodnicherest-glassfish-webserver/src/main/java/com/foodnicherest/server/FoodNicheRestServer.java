package com.foodnicherest.server;

import com.foodnicherest.properties.ServerPropertyLoader;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;

import javax.ejb.embeddable.EJBContainer;
import java.io.File;
import java.util.Properties;
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
            glassfish.start();
            glassfish.getDeployer().deploy(serverPropertyLoader.getDeployResource());
        } catch (GlassFishException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        if(glassfish != null){
            try {
                glassfish.dispose();
            } catch (GlassFishException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
