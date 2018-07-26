package gov.cdc.nccdphp.esurveillance.RENAME_ME.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import gov.cdc.nccdphp.esurveillance.View;
import gov.cdc.nccdphp.esurveillance.rest.ApiVersion;
import gov.cdc.nccdphp.esurveillance.RENAME_ME.About;
import gov.cdc.nccdphp.esurveillance.RENAME_ME.EipServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by marcelo on 10/4/16.
 */

@RestController
@RequestMapping("/info/")
@ApiVersion({1})
public class InfoServiceController {
    @Autowired
    private About about;

    @Autowired
    private EipServiceConfig config;

    @JsonView(View.Summary.class)
    @GetMapping(value="/about")
           // produces = {"application/cdc.info.about-v1+json"}) //This forces Safari to download the file instead of opening it on the browser.
    @ResponseBody
    public About about() throws JsonProcessingException {
        return about;
    }

    @GetMapping(value="/version")
    public String getVersion() {
        return "Version: " + getClass().getPackage().getImplementationVersion();
    }

    @GetMapping("/ping")
    public String ping() {
        return "Hello There! I'm alive.\nYou pinged me at " + ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);
    }

    @JsonView(View.Summary.class)
    @GetMapping(value = "/config", produces = "application/json")
    public EipServiceConfig getConfig() {
        return config;
    }

}

