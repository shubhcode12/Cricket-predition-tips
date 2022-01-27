package com.crickpred.app.Util;

import com.crickpred.app.Interface.ApiServices;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAPI  {



    public static final String BASE_URL="https://darwinbark.com/projects/gopunt/";
    public static final String URL= BASE_URL +"/api_v1/api.php?";
    public static final String URL1= BASE_URL +"api_v1/";
    public static final String PURL=BASE_URL + "/api_v1/api_withdraw_request.php?";
    public static final String CHECK_USER=URL + "check_user";
    public static final String KYC_UPDATE=URL + "kyc_update";
    public static final String API_Registation=URL+ "user_register";
    public static final String API_Login=URL+ "users_login";
    public static final String API_Device_Login=URL + "device_login";
    public static final String API_Login_Logs=URL + "user_logs";
    public static final String API_User_Balance=URL + "user_balance";
    public static final String API_Balance_Update=URL + "balance_update";
    public static final String API_Coin_History=URL + "user_coin_history";
    public static final String API_Coin_withdrawal_History = URL + "user_withdrawal_history";
    public static final String API_Refer_History=URL + "user_refer_history";
    public static final String API_Settings=URL + "settings";
    public static final String API_GET_CREATEID=URL + "create_id";
    public static final String API_GET_MYID=URL + "my_id";
    public static final String API_VIEW_IDTXN=URL + "idviewtxn";
    public static final String API_GET_PASSBOOK=URL + "passbook";
    public static final String API_Spin_Count=URL + "user_coin_count";
    public static final String API_Video_Ads_Count=URL + "video_ads_count";
    public static final String API_Video_Ads_Count_update=URL + "video_ads_count_update";
    public static final String API_Spin_Count_Update=URL + "coin_count_update";
    public static final String API_Forgot_Pass=URL + "forgot_pass";
    public static final String API_Contact_Us=URL + "support_ticket";
    public static final String API_Payment_Request=PURL;
    public static final String API_insert_payment_verification=URL +"insert_payment_verification";
    public static final String API_Paytm_Url=BASE_URL + "paytmsdk/generateChecksum.php";


    public static Retrofit getRetrofitInstance(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiServices getApiService(){
        return getRetrofitInstance().create(ApiServices.class);
    }

    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
