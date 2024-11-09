package hello.embed;

import hello.servlet.HelloServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.IOException;
import java.nio.file.Files;

public class EmbedTomcatServletMain {

    public static void main(String[] args) throws LifecycleException, IOException {
        System.out.println("EmbedTomcatServletMain.main");

        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        String docBase = Files.createTempDirectory("tomcat-basedir").toString();
        Context context = tomcat.addContext("", docBase);
        tomcat.addServlet("", "helloServlet", new HelloServlet());
        context.addServletMappingDecoded("/hello-servlet", "helloServlet");
        tomcat.start();
    }
}
