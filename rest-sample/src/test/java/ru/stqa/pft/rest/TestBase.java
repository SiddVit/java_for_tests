package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;


public class TestBase {
    private final Properties properties;

    public TestBase() {
        properties = new Properties();
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth(getProperty("rest.auth"), getProperty("rest.password"));
    }

    public Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get(getProperty("rest.url") + "/issues.json"))
                .returnContent().asString();
        JsonElement parsed = JsonParser.parseString(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    }

    int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post(getProperty("rest.url") + "/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    @BeforeMethod
    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }
}
