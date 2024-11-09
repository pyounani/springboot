package hello.embed;

import hello.spring.HelloConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;
import java.nio.file.Files;

public class EmbedTomcatSpringMain {

    public static void main(String[] args) throws IOException, LifecycleException {
        System.out.println("EmbedTomcatSpringMain.main");

        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        AnnotationConfigWebApplicationContext appContext = new Annotatio:wq

        DispatcherServlet dispatcher = new DispatcherServlet(appContext);

        String docBase = Files.createTempDirectory("tomcat-basedir").toString();
        Context context = tomcat.addContext("", docBase);
        tomcat.addServlet("", "dispatcher", dispatcher);
        context.addServletMappingDecoded("/", "dispatcher");

        tomcat.start();
    }

}
