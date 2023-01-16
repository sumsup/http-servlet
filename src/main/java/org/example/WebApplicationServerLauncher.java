package org.example;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class WebApplicationServerLauncher {

    private static final Logger log = LoggerFactory.getLogger(WebApplicationServerLauncher.class);

    public static void main(String[] args) throws LifecycleException {
        // 내장 톰캣. 예제소스인데 안됨 ㅅㄱ.
//        String webappDirLocation = "webapp/";
//        Tomcat tomcat = new Tomcat();
//        tomcat.setPort(8080);
//
//        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
//        logger.info("configuring app with basedir: {}", new File("./" + webappDirLocation).getAbsolutePath());
//
//        tomcat.start();
//        tomcat.getServer().await();

        String webappDirLocation = "webapp/";
        Tomcat tomcat = new Tomcat();

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));

        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        log.info("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work
        File additionWebInfClasses = new File("out/production/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();

    }
}
