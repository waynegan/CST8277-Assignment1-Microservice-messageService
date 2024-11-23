package org.cst8277.GanWei.messageservice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.cst8277.GanWei.messageservice.config.AsyncSyncConfiguration;
import org.cst8277.GanWei.messageservice.config.EmbeddedMongo;
import org.cst8277.GanWei.messageservice.config.JacksonConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { MessageServiceApp.class, JacksonConfiguration.class, AsyncSyncConfiguration.class })
@EmbeddedMongo
public @interface IntegrationTest {
}
