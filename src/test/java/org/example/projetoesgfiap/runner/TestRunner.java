package org.example.projetoesgfiap.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"org.example.projetoesgfiap"},
        tags = "@regressivo",
        plugin = {"html:target/cucumber-reports.html"})
public class TestRunner {
}
