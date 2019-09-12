package com.bdd.demo;

import com.bdd.demo.util.TestContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import java.util.Map;
import static com.bdd.demo.util.TestContext.CONTEXT;


@Configuration
public abstract class IntegrationTest {

    @LocalServerPort
    private int port;

    public String baseUrl() {
        return "http://localhost:" + port;
    }

    public TestContext getTestContext() {
        return CONTEXT;
    }



    //***************************POST*************************************************************************

    protected void executePost(String apiPath) {
        executePost(apiPath, null, null);
    }

    protected void executePost(String apiPath, Map<String, String> pathParams) {
        executePost(apiPath, pathParams, null);
    }

    protected void executePost(String apiPath, Map<String, String> pathParams, Map<String, String> queryParamas) {
        final RequestSpecification request = CONTEXT.getRequest();
        final Object payload = CONTEXT.getPayload();

        setPayload(request, payload);
        setQueryParams(pathParams, request);
        setPathParams(queryParamas, request);

        Response response = request.accept(ContentType.JSON).log().all().post(apiPath);

        logResponse(response);

        CONTEXT.setResponse(response);
    }

    //***************************DELETE*************************************************************************

    protected void executeDelete(String apiPath) {
        executeDelete(apiPath, null, null);
    }

    protected void executeDelete(String apiPath, Map<String, String> pathParams) {
        executeDelete(apiPath, pathParams, null);
    }

    protected void executeDelete(String apiPath, Map<String, String> pathParams, Map<String, String> queryParamas) {
        final RequestSpecification request = CONTEXT.getRequest();
        final Object payload = CONTEXT.getPayload();

        setPayload(request, payload);
        setQueryParams(pathParams, request);
        setPathParams(queryParamas, request);

        Response response = request.accept(ContentType.JSON)
                // .log()
                // .all()
                .delete(apiPath);

        logResponse(response);
        CONTEXT.setResponse(response);
    }

    //***************************PUT*************************************************************************

    protected void executePut(String apiPath) {
        executePut(apiPath, null, null);
    }

    protected void executePut(String apiPath, Map<String, String> pathParams) {
        executePut(apiPath, pathParams, null);
    }

    protected void executePut(String apiPath, Map<String, String> pathParams, Map<String, String> queryParamas) {
        final RequestSpecification request = CONTEXT.getRequest();
        final Object payload = CONTEXT.getPayload();

        setPayload(request, payload);
        setPathParams(pathParams, request);
        setQueryParams(queryParamas, request);

        Response response = request.accept(ContentType.JSON).log().all().put(apiPath);

        logResponse(response);
        CONTEXT.setResponse(response);
    }

    //***************************PATCH*************************************************************************

    protected void executePatch(String apiPath) {
        executePatch(apiPath, null, null);
    }

    protected void executePatch(String apiPath, Map<String, String> pathParams) {
        executePatch(apiPath, pathParams, null);
    }

    protected void executePatch(String apiPath, Map<String, String> pathParams, Map<String, String> queryParamas) {
        final RequestSpecification request = CONTEXT.getRequest();
        final Object payload = CONTEXT.getPayload();

        setPayload(request, payload);
        setPathParams(pathParams, request);
        setQueryParams(queryParamas, request);

        Response response = request.accept(ContentType.JSON).log().all().patch(apiPath);

        logResponse(response);

        CONTEXT.setResponse(response);
    }

    //***************************GET*************************************************************************

    protected void executeGet(String apiPath) {
        executeGet(apiPath, null, null);
    }

    protected void executeGet(String apiPath, Map<String, String> pathParams) {
        executeGet(apiPath, pathParams, null);
    }

    protected void executeGet(String apiPath, Map<String, String> pathParams, Map<String, String> queryParamas) {
        final RequestSpecification request = CONTEXT.getRequest();

        setPathParams(pathParams, request);
        setQueryParams(queryParamas, request);

        Response response = request.accept(ContentType.JSON).log().all().get(apiPath);
        // logResponse(response);
        CONTEXT.setResponse(response);
    }

    //***************************************************************************************************************

    private void logResponse(Response response) {
        response.then().log().all();
    }

    private void setQueryParams(Map<String, String> queryParamas, RequestSpecification request) {
        if (null != queryParamas) {
            request.queryParams(queryParamas);
        }
    }

    private void setPathParams(Map<String, String> pathParams, RequestSpecification request) {
        if (null != pathParams) {
            request.pathParams(pathParams);
        }
    }

    private void setPayload(RequestSpecification request, Object payload) {
        if (null != payload) {
            request.contentType(ContentType.JSON).body(payload);
        }
    }
}
