/*
 * WSO2 API Manager - Admin
 * This document specifies a **RESTful API** for WSO2 **API Manager** - **Admin Portal**. Please see [full OpenAPI Specification](https://raw.githubusercontent.com/wso2/carbon-apimgt/v6.7.206/components/apimgt/org.wso2.carbon.apimgt.rest.api.admin.v1/src/main/resources/admin-api.yaml) of the API which is written using [OAS 3.0](http://swagger.io/) specification.  # Authentication Our REST APIs are protected using OAuth2 and access control is achieved through scopes. Before you start invoking the the API you need to obtain an access token with the required scopes. This guide will walk you through the steps that you will need to follow to obtain an access token. First you need to obtain the consumer key/secret key pair by calling the dynamic client registration (DCR) endpoint. You can add your preferred grant types in the payload. A sample payload is shown below. ```   {   \"callbackUrl\":\"www.google.lk\",   \"clientName\":\"rest_api_admin\",   \"owner\":\"admin\",   \"grantType\":\"client_credentials password refresh_token\",   \"saasApp\":true   } ``` Create a file (payload.json) with the above sample payload, and use the cURL shown bellow to invoke the DCR endpoint. Authorization header of this should contain the base64 encoded admin username and password. **Format of the request** ```   curl -X POST -H \"Authorization: Basic Base64(admin_username:admin_password)\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://<host>:<servlet_port>/client-registration/v0.17/register ``` **Sample request** ```   curl -X POST -H \"Authorization: Basic YWRtaW46YWRtaW4=\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://localhost:9443/client-registration/v0.17/register ``` Following is a sample response after invoking the above curl. ``` { \"clientId\": \"fOCi4vNJ59PpHucC2CAYfYuADdMa\", \"clientName\": \"rest_api_admin\", \"callBackURL\": \"www.google.lk\", \"clientSecret\": \"a4FwHlq0iCIKVs2MPIIDnepZnYMa\", \"isSaasApplication\": true, \"appOwner\": \"admin\", \"jsonString\": \"{\\\"grant_types\\\":\\\"client_credentials password refresh_token\\\",\\\"redirect_uris\\\":\\\"www.google.lk\\\",\\\"client_name\\\":\\\"rest_api_admin\\\"}\", \"jsonAppAttribute\": \"{}\", \"tokenType\": null } ``` Next you must use the above client id and secret to obtain the access token. We will be using the password grant type for this, you can use any grant type you desire. You also need to add the proper **scope** when getting the access token. All possible scopes for Admin REST API can be viewed in **OAuth2 Security** section of this document and scope for each resource is given in **authorizations** section of resource documentation. Following is the format of the request if you are using the password grant type. ``` curl -k -d \"grant_type=password&username=<admin_username>&password=<admin_passowrd>&scope=<scopes seperated by space>\" \\ -H \"Authorization: Basic base64(cliet_id:client_secret)\" \\ https://<host>:<gateway_port>/token ``` **Sample request** ``` curl https://localhost:8243/token -k \\ -H \"Authorization: Basic Zk9DaTR2Tko1OVBwSHVjQzJDQVlmWXVBRGRNYTphNEZ3SGxxMGlDSUtWczJNUElJRG5lcFpuWU1h\" \\ -d \"grant_type=password&username=admin&password=admin&scope=apim:admin apim:tier_view\" ``` Shown below is a sample response to the above request. ``` { \"access_token\": \"e79bda48-3406-3178-acce-f6e4dbdcbb12\", \"refresh_token\": \"a757795d-e69f-38b8-bd85-9aded677a97c\", \"scope\": \"apim:admin apim:tier_view\", \"token_type\": \"Bearer\", \"expires_in\": 3600 } ``` Now you have a valid access token, which you can use to invoke an API. Navigate through the API descriptions to find the required API, obtain an access token as described above and invoke the API with the authentication header. If you use a different authentication mechanism, this process may change.  # Try out in Postman If you want to try-out the embedded postman collection with \"Run in Postman\" option, please follow the guidelines listed below. * All of the OAuth2 secured endpoints have been configured with an Authorization Bearer header with a parameterized access token. Before invoking any REST API resource make sure you run the `Register DCR Application` and `Generate Access Token` requests to fetch an access token with all required scopes. * Make sure you have an API Manager instance up and running. * Update the `basepath` parameter to match the hostname and port of the APIM instance.  [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/f5ac2ca9fb22afef6ed6) 
 *
 * The version of the OpenAPI document: v1.2
 * Contact: architecture@wso2.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.wso2.am.integration.clients.admin.api;

import org.wso2.am.integration.clients.admin.ApiCallback;
import org.wso2.am.integration.clients.admin.ApiClient;
import org.wso2.am.integration.clients.admin.ApiException;
import org.wso2.am.integration.clients.admin.ApiResponse;
import org.wso2.am.integration.clients.admin.Configuration;
import org.wso2.am.integration.clients.admin.Pair;
import org.wso2.am.integration.clients.admin.ProgressRequestBody;
import org.wso2.am.integration.clients.admin.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.wso2.am.integration.clients.admin.api.dto.ErrorDTO;
import org.wso2.am.integration.clients.admin.api.dto.KeyManagerDTO;
import org.wso2.am.integration.clients.admin.api.dto.KeyManagerListDTO;
import org.wso2.am.integration.clients.admin.api.dto.KeyManagerWellKnownResponseDTO;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyManagerCollectionApi {
    private ApiClient localVarApiClient;

    public KeyManagerCollectionApi() {
        this(Configuration.getDefaultApiClient());
    }

    public KeyManagerCollectionApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for keyManagersDiscoverPost
     * @param url Well-Known Endpoint (optional)
     * @param type Key Manager Type  (optional, default to &quot;false&quot;)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. KeyManagers returned  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call keyManagersDiscoverPostCall(String url, String type, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/key-managers/discover";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        if (url != null) {
            localVarFormParams.put("url", url);
        }

        if (type != null) {
            localVarFormParams.put("type", type);
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "multipart/form-data"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call keyManagersDiscoverPostValidateBeforeCall(String url, String type, final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = keyManagersDiscoverPostCall(url, type, _callback);
        return localVarCall;

    }

    /**
     * Retrieve Well-known information from Key Manager Well-known Endpoint
     * Retrieve well-known information from key manager&#39;s well-known endpoint 
     * @param url Well-Known Endpoint (optional)
     * @param type Key Manager Type  (optional, default to &quot;false&quot;)
     * @return KeyManagerWellKnownResponseDTO
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. KeyManagers returned  </td><td>  -  </td></tr>
     </table>
     */
    public KeyManagerWellKnownResponseDTO keyManagersDiscoverPost(String url, String type) throws ApiException {
        ApiResponse<KeyManagerWellKnownResponseDTO> localVarResp = keyManagersDiscoverPostWithHttpInfo(url, type);
        return localVarResp.getData();
    }

    /**
     * Retrieve Well-known information from Key Manager Well-known Endpoint
     * Retrieve well-known information from key manager&#39;s well-known endpoint 
     * @param url Well-Known Endpoint (optional)
     * @param type Key Manager Type  (optional, default to &quot;false&quot;)
     * @return ApiResponse&lt;KeyManagerWellKnownResponseDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. KeyManagers returned  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<KeyManagerWellKnownResponseDTO> keyManagersDiscoverPostWithHttpInfo(String url, String type) throws ApiException {
        okhttp3.Call localVarCall = keyManagersDiscoverPostValidateBeforeCall(url, type, null);
        Type localVarReturnType = new TypeToken<KeyManagerWellKnownResponseDTO>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve Well-known information from Key Manager Well-known Endpoint (asynchronously)
     * Retrieve well-known information from key manager&#39;s well-known endpoint 
     * @param url Well-Known Endpoint (optional)
     * @param type Key Manager Type  (optional, default to &quot;false&quot;)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. KeyManagers returned  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call keyManagersDiscoverPostAsync(String url, String type, final ApiCallback<KeyManagerWellKnownResponseDTO> _callback) throws ApiException {

        okhttp3.Call localVarCall = keyManagersDiscoverPostValidateBeforeCall(url, type, _callback);
        Type localVarReturnType = new TypeToken<KeyManagerWellKnownResponseDTO>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for keyManagersGet
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. KeyManagers returned  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call keyManagersGetCall(final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/key-managers";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call keyManagersGetValidateBeforeCall(final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = keyManagersGetCall(_callback);
        return localVarCall;

    }

    /**
     * Get all Key managers
     * Get all Key managers 
     * @return KeyManagerListDTO
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. KeyManagers returned  </td><td>  -  </td></tr>
     </table>
     */
    public KeyManagerListDTO keyManagersGet() throws ApiException {
        ApiResponse<KeyManagerListDTO> localVarResp = keyManagersGetWithHttpInfo();
        return localVarResp.getData();
    }

    /**
     * Get all Key managers
     * Get all Key managers 
     * @return ApiResponse&lt;KeyManagerListDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. KeyManagers returned  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<KeyManagerListDTO> keyManagersGetWithHttpInfo() throws ApiException {
        okhttp3.Call localVarCall = keyManagersGetValidateBeforeCall(null);
        Type localVarReturnType = new TypeToken<KeyManagerListDTO>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get all Key managers (asynchronously)
     * Get all Key managers 
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. KeyManagers returned  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call keyManagersGetAsync(final ApiCallback<KeyManagerListDTO> _callback) throws ApiException {

        okhttp3.Call localVarCall = keyManagersGetValidateBeforeCall(_callback);
        Type localVarReturnType = new TypeToken<KeyManagerListDTO>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for keyManagersPost
     * @param keyManagerDTO Key Manager object that should to be added  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Created. Successful response with the newly created object as entity in the body.  </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call keyManagersPostCall(KeyManagerDTO keyManagerDTO, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = keyManagerDTO;

        // create path and map variables
        String localVarPath = "/key-managers";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call keyManagersPostValidateBeforeCall(KeyManagerDTO keyManagerDTO, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'keyManagerDTO' is set
        if (keyManagerDTO == null) {
            throw new ApiException("Missing the required parameter 'keyManagerDTO' when calling keyManagersPost(Async)");
        }
        

        okhttp3.Call localVarCall = keyManagersPostCall(keyManagerDTO, _callback);
        return localVarCall;

    }

    /**
     * Add a new API Key Manager
     * Add a new API Key Manager 
     * @param keyManagerDTO Key Manager object that should to be added  (required)
     * @return KeyManagerDTO
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Created. Successful response with the newly created object as entity in the body.  </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
     </table>
     */
    public KeyManagerDTO keyManagersPost(KeyManagerDTO keyManagerDTO) throws ApiException {
        ApiResponse<KeyManagerDTO> localVarResp = keyManagersPostWithHttpInfo(keyManagerDTO);
        return localVarResp.getData();
    }

    /**
     * Add a new API Key Manager
     * Add a new API Key Manager 
     * @param keyManagerDTO Key Manager object that should to be added  (required)
     * @return ApiResponse&lt;KeyManagerDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Created. Successful response with the newly created object as entity in the body.  </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<KeyManagerDTO> keyManagersPostWithHttpInfo(KeyManagerDTO keyManagerDTO) throws ApiException {
        okhttp3.Call localVarCall = keyManagersPostValidateBeforeCall(keyManagerDTO, null);
        Type localVarReturnType = new TypeToken<KeyManagerDTO>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Add a new API Key Manager (asynchronously)
     * Add a new API Key Manager 
     * @param keyManagerDTO Key Manager object that should to be added  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Created. Successful response with the newly created object as entity in the body.  </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call keyManagersPostAsync(KeyManagerDTO keyManagerDTO, final ApiCallback<KeyManagerDTO> _callback) throws ApiException {

        okhttp3.Call localVarCall = keyManagersPostValidateBeforeCall(keyManagerDTO, _callback);
        Type localVarReturnType = new TypeToken<KeyManagerDTO>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
