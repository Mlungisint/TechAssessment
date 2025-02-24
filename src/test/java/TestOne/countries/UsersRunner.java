package TestOne.countries;

import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.Test;

class UsersRunner {
    
    @Karate.Test
    Karate firstTest() {
        return Karate.run("allCountries");
    }    

}
