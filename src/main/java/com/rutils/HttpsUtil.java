package com.rutils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;


public class HttpsUtil{

    static HttpClient client;

    public static void init(){}
    static{
        try{
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(HttpsUtil.class.getClassLoader().getResourceAsStream("auth/ksclient.p12"), "client7548".toCharArray());

            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);

            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(keyStore, "client7548".toCharArray());

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            client = HttpClient.newBuilder()
                .sslContext(sslContext)
                .build();
        }
        catch(Exception e){
            e.printStackTrace();
        }   
        
    }

    public static int registerUser(String username, String password, String passwordAgain){
        if(password.equals(passwordAgain) && username.length() != 0 && password.length() != 0){
            String jsonPayload = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password);

            try{
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://rafayahmad.serveminecraft.net:25565/register"))
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(jsonPayload))
                    .build();
                
                HttpResponse<String> httpResponse = client.send(request, BodyHandlers.ofString());
                int response = Integer.parseInt(httpResponse.body());

                return response;
            }
            catch(URISyntaxException e){
                System.err.println("Invalid Uri provided for http request");
                e.printStackTrace();
            }
            catch(IOException e){
                System.err.println("sending request interuppterd Eroor");
                e.printStackTrace();
            }
            catch(InterruptedException e){
            System.err.println("io exeception when sending http request");
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static int verifyCredentials(String username, String password){
        try{
            String jsonPayload = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password);

            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://rafayahmad.serveminecraft.net:25565/login"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonPayload))
                .build();

            HttpResponse<String> httpResponse = client.send(request, BodyHandlers.ofString());
            
            int response = Integer.parseInt(httpResponse.body());
            return response;
        }
        catch(URISyntaxException e){
            System.err.println("Invalid Uri provided for http request");
            e.printStackTrace();
        }
        catch(InterruptedException e){
            System.err.println("sending request interuppterd Eroor");
            e.printStackTrace();
        }
        catch(IOException e){
            System.err.println("io exeception when sending http request");
            e.printStackTrace();
        }
        
        return 0;
    }
}