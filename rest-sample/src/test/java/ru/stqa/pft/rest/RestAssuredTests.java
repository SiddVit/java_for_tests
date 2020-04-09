package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase {
    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    @Test
    public void testCreateIssue() {
        Set<Issue> oldIssues = getAssuredIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
        int issueId = createAssuredIssue(newIssue);
        Set<Issue> newIssues = getAssuredIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }

    public Set<Issue> getAssuredIssues() {
        String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json?limit=500").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    int createAssuredIssue(Issue newIssue) {
        String json = RestAssured.given().parameter("subject", newIssue.getSubject())
                .parameter("description", newIssue.getDescription())
                .post("https://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = JsonParser.parseString(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}
