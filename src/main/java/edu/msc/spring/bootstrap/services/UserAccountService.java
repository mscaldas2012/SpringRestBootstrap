package edu.msc.spring.bootstrap.services;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import edu.msc.spring.bootstrap.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by marcelo on 11/7/16.
 */
@Service
public class UserAccountService {

    private static final String SERVICE_NAME = "USERACCOUNTSERVICES";
    @Autowired
    private UserAcctRemoteService remote;



    //TODO::This is going through the fallbackmethod even for a 404. Don't want that.
    //Need to differentiate between an error and a 404 - user not found.
    @HystrixCommand(fallbackMethod = "userAccountServiceDown")
    public ResponseEntity<UserAccount> getUser(String username) {
        return remote.getUserByUsername(username);
    }



    public ResponseEntity<UserAccount> userAccountServiceDown(String name) {
        //LOG.debug("Seems like our useraccount-service is down: ");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    public InstanceInfo.InstanceStatus statusNotFound() {
        return InstanceInfo.InstanceStatus.DOWN;
    }

    @FeignClient(SERVICE_NAME)
    interface UserAcctRemoteService  {
        @RequestMapping(value="/user/{username}", method = GET)
        ResponseEntity<UserAccount> getUserByUsername(@PathVariable(value = "username") String username) ;

    }
}
