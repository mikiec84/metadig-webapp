package edu.ucsb.nceas.mdq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.servlet.annotation.WebListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import edu.ucsb.nceas.mdqengine.Controller;

public class MetadigContextListener implements ServletContextListener {

    public static Log log = LogFactory.getLog(MetadigContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
            log.debug("Metadig 'contextInitialized' called.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.debug("Metadig 'contextDestroyed' called.");
        Controller controller = Controller.getInstance();
        if(controller.getIsStarted()) {
            try {
                log.debug("Shutting down controller...");
                controller.shutdown();
                log.info("Controller shutdonw successfully.");
            } catch (IOException | TimeoutException e) {
                log.error("Error shutting down metadig controller.");
                e.printStackTrace();
            }
        }
    }
}
