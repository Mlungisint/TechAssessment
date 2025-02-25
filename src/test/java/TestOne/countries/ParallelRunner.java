package TestOne.countries;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.intuit.karate.Runner.Builder;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ParallelRunner {

    @Test
    public void exTest() {
        File file = new File("target/cucumber-html-reports/");
        String[] myFiles;
        if (file.isDirectory()) {
            myFiles = file.list();
            for (int i = 0; i < myFiles.length; i++) {
                File myFile = new File(file, myFiles[i]);
                System.out.println("Deleteing files: " + myFile);
                myFile.delete();
            }
        }

        String reportDirectory = "target/surefire-reports/";
        long starttime = System.nanoTime();
        long endtime = System.nanoTime();
        System.setProperty("karate.env", System.getProperty("karate.env"));

        Builder run = new Builder();
        run.path("classpath:src/test/java/TestOne/countries");
        Results result = Runner.path("classpath:src/test/java/TestOne/countries")
                .outputCucumberJson(true)
                .parallel(1);
//        Results result =run.parallel(4).;
        ParallelRunner.this.CucumberReporting(result.getReportDir());
        CucumberReporting(result.getReportDir());
        System.out.println("Total Scenarios :" + result.getScenariosTotal());
        System.out.println("Total Passed :" + result.getScenariosPassed());
        System.out.println("Total Passed :" + result.getScenariosFailed());
    }

    // C:\Users\mlung\Documents\MarkAssessments\KarateTestProject\target\surefire-reports
    public void CucumberReporting(String reportDirectory) {
        File dir = new File(reportDirectory);
        Collection<File> jsonCollection = FileUtils.listFiles(dir, new String[]{"json"}, true);

        List<String> jsonFiles = new ArrayList<String>(jsonCollection.size());
        jsonCollection.forEach(file -> jsonFiles.add(file.getAbsolutePath()));

        Configuration configuration = new Configuration(dir, "Test run");
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();


    }
}
