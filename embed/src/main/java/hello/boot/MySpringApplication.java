package hello.boot;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class MySpringApplication {

    public static void run(Class configClass, String[] args) throws IOException {
        System.out.println("MySpringBootApplication.run args=" + List.of(args));

        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(configClass);

        DispatcherServlet dispatcher = new DispatcherServlet(appContext);

        String docBase = Files.createTempDirectory("tomcat-basedir").toString();
        Context context = tomcat.addContext("", docBase);
        tomcat.addServlet("", "dispatcher", dispatcher);
        context.addServletMappingDecoded("/", "dispatcher");
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }


}
