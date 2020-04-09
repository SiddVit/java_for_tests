package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        String json = getExecutor().execute(Request.Get(getProperty("rest.url") + "/issues.json?limit=500"))
                .returnContent().asString();
        JsonElement parsed = JsonParser.parseString(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    }

    public String getStatus(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get(getProperty("rest.url") + String.format("/issues/%s.json", issueId)))
                .returnContent().asString();
        JsonElement parsed = JsonParser.parseString(json);
        return parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0)
                .getAsJsonObject().get("state_name").getAsString();
    }

    int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post(getProperty("rest.url") + "/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = JsonParser.parseString(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    boolean isIssueOpen(int issueId) throws IOException {
        return !getStatus(issueId).equals("Closed");
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    @BeforeMethod
    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }
}
