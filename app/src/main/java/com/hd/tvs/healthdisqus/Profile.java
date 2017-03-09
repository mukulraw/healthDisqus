package com.hd.tvs.healthdisqus;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import interfaces.allAPIs;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Profile extends AppCompatActivity {


    CircleImageView image;
    TextView picture , password;
    ProgressBar progress;
    Toolbar toolbar;
    private final int PICK_IMAGE_REQUEST = 2;
    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        toolbar = (Toolbar)findViewById(R.id.toolbar);
        image = (CircleImageView)findViewById(R.id.image);
        picture = (TextView)findViewById(R.id.picture);
        password = (TextView)findViewById(R.id.pass);


        setSupportActionBar(toolbar);

        try {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            toolbar.setTitle("My Profile");
            toolbar.setTitleTextColor(Color.WHITE);

        } catch (NullPointerException e1) {
            e1.printStackTrace();
        }


        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);

            }
        });


        toolbar.setNavigationIcon(R.drawable.back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        progress = (ProgressBar)findViewById(R.id.progress);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.healthdisqus.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        final allAPIs cr = retrofit.create(allAPIs.class);

        progress.setVisibility(View.VISIBLE);

        final bean b = (bean)getApplicationContext();

        Call<imageBean> call = cr.getImage(b.id);

        call.enqueue(new Callback<imageBean>() {
            @Override
            public void onResponse(Call<imageBean> call, Response<imageBean> response) {

                ImageLoader loader = ImageLoader.getInstance();

                loader.displayImage(response.body().getProfilePic() , image);

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<imageBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {


            //browse_image.setImageBitmap(bitmap);
            Uri selectedImageUri = data.getData();

            //bean.setBrowse(bitmap);


            mCurrentPhotoPath = getPath(getApplicationContext() , selectedImageUri);


            File file = new File(mCurrentPhotoPath);



            RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            MultipartBody.Part body = MultipartBody.Part.createFormData("pic_name", file.getName(), reqFile);


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.healthdisqus.com/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            final allAPIs cr = retrofit.create(allAPIs.class);

            progress.setVisibility(View.VISIBLE);

            final bean b = (bean)getApplicationContext();


            Call<updateBean> call = cr.updateImage(b.id , body);

            call.enqueue(new Callback<updateBean>() {
                @Override
                public void onResponse(Call<updateBean> call, Response<updateBean> response) {

                    Call<imageBean> call2 = cr.getImage(b.id);

                    call2.enqueue(new Callback<imageBean>() {
                        @Override
                        public void onResponse(Call<imageBean> call, Response<imageBean> response) {

                            ImageLoader loader = ImageLoader.getInstance();

                            loader.displayImage(response.body().getProfilePic() , image);

                            progress.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<imageBean> call, Throwable t) {
                            progress.setVisibility(View.GONE);
                        }
                    });

                }

                @Override
                public void onFailure(Call<updateBean> call, Throwable t) {

                }
            });


        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    private static String getPath(final Context context, final Uri uri)
    {
        final boolean isKitKatOrAbove = Build.VERSION.SDK_INT >=  Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isKitKatOrAbove && DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    if ("primary".equalsIgnoreCase(type)) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }

                    // TODO handle non-primary volumes
                }
                // DownloadsProvider
                else if (isDownloadsDocument(uri)) {

                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                    return getDataColumn(context, contentUri, null, null);
                }
                // MediaProvider
                else if (isMediaDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }

                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[] {
                            split[1]
                    };

                    return getDataColumn(context, contentUri, selection, selectionArgs);
                }
            }
            // MediaStore (and general)
            else if ("content".equalsIgnoreCase(uri.getScheme())) {
                return getDataColumn(context, uri, null, null);
            }
            // File
            else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }

        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

}
