package TestOne.countries;

import com.intuit.karate.Results;
import com.intuit.karate.core.Result;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import com.intuit.karate.Runner.Builder;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ParallelRunner {

    @Test
    public void exTest(){
        Builder run=new Builder();
        run.path("classpath:src/test/java/TestOne/countries");
       Results result = run.parallel(4);
       CucumberReporting(result.getReportDir());
       System.out.println("Total Scenarios :"+result.getScenarioCount());
       System.out.println("Total Passed :"+result.getPassCount());
        System.out.println("Total Passed :"+result.getFailCount());
    }
// C:\Users\mlung\Documents\MarkAssessments\KarateTestProject\target\surefire-reports
    public void CucumberReporting(String reportDirectory) {
        File dir=new File(reportDirectory);
       Collection<File> jsonCollection = FileUtils.listFiles(dir ,new String[] {"json"},true);

        List<String> jsonFiles=new ArrayList<String>();
        jsonCollection.forEach(file -> jsonFiles.add(file.getAbsolutePath()));

        Configuration configuration=new Configuration(dir,"Test run");
        ReportBuilder reportBuilder=new ReportBuilder(jsonFiles,configuration);
        reportBuilder.generateReports();


    }
}
