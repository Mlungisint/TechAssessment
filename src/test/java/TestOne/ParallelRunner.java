package TestOne;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
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

//        Builder run = new Builder();
//        run.path("classpath:TestOne/features");
//        Results result = run.parallel(4);
        Results results= Runner.path("classpath:TestOne")
                        .outputCucumberJson(true)
                                .parallel(1);
        CucumberReporting(results.getReportDir());
        System.out.println("Total Scenarios :" + results.getScenariosTotal());
        System.out.println("Total Passed :" + results.getScenariosPassed());
        System.out.println("Total Passed :" + results.getScenariosFailed());
    }

    public void CucumberReporting(String reportDirectory) {
        File dir = new File(reportDirectory);
        Collection<File> jsonCollection = FileUtils.listFiles(dir, new String[]{"json"}, true);

        List<String> jsonFiles = new ArrayList<String>(jsonCollection.size());
        jsonCollection.forEach(file -> jsonFiles.add(file.getAbsolutePath()));

        Configuration configuration = new Configuration(dir, "target");
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}
