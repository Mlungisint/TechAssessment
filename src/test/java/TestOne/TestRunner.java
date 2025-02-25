package TestOne;

import com.intuit.karate.junit5.Karate;


public class TestRunner {

    @Karate.Test
    Karate TestApi(){
        return Karate.run("allCountries").relativeTo(getClass());
    }


}
