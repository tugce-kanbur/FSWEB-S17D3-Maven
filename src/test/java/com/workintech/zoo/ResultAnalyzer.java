package com.workintech.zoo;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResultAnalyzer implements TestWatcher, AfterAllCallback {
    private final List<TestResultStatus> testResultsStatus = new ArrayList<>();
    private static final String taskId = "156";

    private enum TestResultStatus { SUCCESSFUL, ABORTED, FAILED, DISABLED }

    @Override public void testDisabled(ExtensionContext context, Optional<String> reason) { testResultsStatus.add(TestResultStatus.DISABLED); }
    @Override public void testSuccessful(ExtensionContext context) { testResultsStatus.add(TestResultStatus.SUCCESSFUL); }
    @Override public void testAborted(ExtensionContext context, Throwable cause) { testResultsStatus.add(TestResultStatus.ABORTED); }
    @Override public void testFailed(ExtensionContext context, Throwable cause) { testResultsStatus.add(TestResultStatus.FAILED); }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        Map<TestResultStatus, Long> summary = testResultsStatus.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long success = summary.getOrDefault(TestResultStatus.SUCCESSFUL, 0L);
        long failure = summary.getOrDefault(TestResultStatus.FAILED, 0L);

        double denom = success + failure;
        double score = denom == 0 ? 0.0 : (double) success / denom;
        String userId = "304237";


        String json = String.format(Locale.US,
                "{\"score\":%.6f,\"taskId\":\"%s\",\"userId\":\"%s\"}",
                score, taskId, userId);

        sendTestResult(json);
    }

    private void sendTestResult(String result) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://coursey-gpt-backend.herokuapp.com/nextgen/taskLog/saveJavaTasks"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(result))
                .build();

        client.send(request, HttpResponse.BodyHandlers.discarding());
    }
}