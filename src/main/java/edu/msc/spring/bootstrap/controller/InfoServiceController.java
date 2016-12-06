package edu.msc.spring.bootstrap.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonGeneratorImpl;
import com.netflix.client.http.HttpResponse;
import edu.msc.spring.bootstrap.About;
import edu.msc.rest.ApiVersion;
import edu.msc.spring.bootstrap.model.UserAccount;
import edu.msc.spring.bootstrap.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by marcelo on 10/4/16.
 */

@RestController
@RequestMapping("/info/")
@ApiVersion({1, 2})
public class InfoServiceController {
    @Autowired
    private About about;

    @Autowired
    private UserAccountService userAccountService;


    @RequestMapping(value="/about", method = GET)
           // produces = {"application/cdc.info.about-v1+json"}) //This forces Safari to download the file instead of opening it on the browser.
    @ResponseBody
    public About about() throws JsonProcessingException {
        return about;
    }

    @RequestMapping("/ping")
    public String ping() {
        return "Hello There! I'm alive";
    }


    @RequestMapping(value = "/user/{username}", method=GET)
    public ResponseEntity<UserAccount> getUserInfo(@PathVariable String username) {
        return  userAccountService.getUser(username);

    }

    // Playing with Eureka...

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value="/serviceUrl", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String serviceUrl() throws IOException {
        List<String> services = discoveryClient.getServices();
        JsonFactory factory = new JsonFactory();

        Writer w = new StringWriter();
        JsonGenerator generator = factory.createGenerator(w);
        generator.writeStartObject();
        generator.writeFieldName("Services");
        if (services != null && services.size() > 0) {
            generator.writeStartArray();
            for (String srv: services) {
                generator.writeStartObject();
                generator.writeStringField("name", srv);
                List<ServiceInstance> list = discoveryClient.getInstances(srv);
                if (list != null && list.size() > 0 ) {
                    generator.writeStringField("url", String.valueOf(list.get(0).getUri()));
                }
                generator.writeEndObject();
             }
             generator.writeEndArray();

        }
        generator.writeEndObject();
        generator.close();
        return w.toString();
    }

}

