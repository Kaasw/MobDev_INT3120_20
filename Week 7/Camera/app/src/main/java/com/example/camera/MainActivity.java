package com.example.camera;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String IMG_URL = "https://firebasestorage.googleapis.com/v0/b/calo-a7a97.appspot.com/o/egg.png?alt=media&token=72163b7e-056a-40b8-909e-ea9e92248aa3";
    private static final String API_USER_TOKEN = "4c89d7bacc291b3bf1a1535dd9461db2e464b782";
    private static final String LOGMEAL_API_URL = "https://api.logmeal.es/v2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                // Step 1: Download Image
                byte[] imgData = downloadImage(IMG_URL);

                if (imgData != null) {
                    // Step 2: Upload Image for Segmentation
                    String imageId = uploadImageForSegmentation(imgData);

                    // Step 3: Get Nutritional Info
                    getNutritionalInfo(imageId);
                }
            } catch (IOException | JSONException e) {
                Log.e("ExecutorError: ", String.valueOf(e));
            }
        });
    }

    private byte[] downloadImage(String imageUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(imageUrl)
                .addHeader("Authorization", "Bearer " + API_USER_TOKEN)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().bytes();
            } else {
                Log.d("DownloadImageError: ", response.body().string());
                throw new IOException("Failed to download image");
            }
        }
    }

    private String uploadImageForSegmentation(byte[] imgData) throws IOException {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", "image.jpg", RequestBody.create(imgData, MediaType.parse("image/*")))
                .build();

        Request request = new Request.Builder()
                .url(LOGMEAL_API_URL + "/image/segmentation/complete")
                .addHeader("Authorization", "Bearer " + API_USER_TOKEN)
                .post(requestBody)
                .build();

        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (response.isSuccessful()) {
                JSONObject jsonResponse = new JSONObject(response.body().string());
                return jsonResponse.getString("imageId");
            } else {
                Log.d("UploadImageError: ", response.body().string());
                throw new IOException("Failed to upload image for segmentation");
            }
        } catch (JSONException e) {
            Log.d("DownloadImageErrorbutdifferent: ", String.valueOf(e));
            throw new RuntimeException(e);
        }
    }

    private void getNutritionalInfo(String imageId) throws IOException, JSONException {
        JSONObject json = new JSONObject();
        json.put("imageId", imageId);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());

        Request request = new Request.Builder()
                .url(LOGMEAL_API_URL + "/recipe/nutritionalInfo")
                .addHeader("Authorization", "Bearer " + API_USER_TOKEN)
                .post(requestBody)
                .build();

        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonData = response.body().string();
                JSONObject jsonObject = new JSONObject(jsonData);
                Log.d("NutritionalInfo: ",jsonObject.toString(4)); // Indented with 4 spaces
            } else {
                Log.d("NutritionalInfoError: ", response.body().string());
                throw new IOException("Failed to get nutritional info");
            }
        }
    }
}
