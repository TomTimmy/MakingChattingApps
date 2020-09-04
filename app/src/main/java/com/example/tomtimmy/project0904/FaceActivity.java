package com.example.tomtimmy.project0904;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionPoint;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceContour;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark;

import java.util.List;

import androidx.annotation.NonNull;

public class FaceActivity extends Activity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_face);
        mContext = this;


        final RelativeLayout RelativeLayout_main = findViewById(R.id.RelativeLayout_main);

        // High-accuracy landmark detection and face classification
        FirebaseVisionFaceDetectorOptions options =
                new FirebaseVisionFaceDetectorOptions.Builder()
                        .setPerformanceMode(FirebaseVisionFaceDetectorOptions.ACCURATE)
                        .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                        .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                        .build();

        final Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.face);

        final FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);

        FirebaseVisionFaceDetector detector = FirebaseVision.getInstance()
                .getVisionFaceDetector(options);

        Task<List<FirebaseVisionFace>> result =
                detector.detectInImage(image)
                        .addOnSuccessListener(
                                new OnSuccessListener<List<FirebaseVisionFace>>() {
                                    @Override
                                    public void onSuccess(List<FirebaseVisionFace> faces) {
                                        // Task completed successfully
                                        // ...

                                        Point p = new Point();
                                        Display display = getWindowManager().getDefaultDisplay();
                                        display.getSize(p);

//                                        p.x; p.y;
                                        //1:10=10:x


                                        for (FirebaseVisionFace face : faces) {

                                            FirebaseVisionFaceLandmark leftEye = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_EYE);
                                            float lex = leftEye.getPosition().getX();
                                            float ley =leftEye.getPosition().getY();

                                            FirebaseVisionFaceLandmark leftCheek = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_CHEEK);
                                            float lcx = leftCheek.getPosition().getX();
                                            float lcy = leftCheek.getPosition().getY();

                                            FirebaseVisionFaceLandmark rightCheek = face.getLandmark(FirebaseVisionFaceLandmark.RIGHT_CHEEK);
                                            float rcx = rightCheek.getPosition().getX();
                                            float rcy = rightCheek.getPosition().getY();

                                            ImageView imageLE = new ImageView(mContext);
                                            imageLE.setImageResource(R.drawable.m);
                                            imageLE.setX(p.x * lex / bitmap.getWidth() - 100);
                                            imageLE.setY(p.y * ley / bitmap.getHeight() - 100);
                                            imageLE.setLayoutParams(new RelativeLayout.LayoutParams( 200, 200));
                                            RelativeLayout_main.addView(imageLE);

                                            ImageView imageLC = new ImageView(mContext);
                                            imageLC.setImageResource(R.drawable.lw);
                                            imageLC.setX(p.x * lcx / bitmap.getWidth() - 100);
                                            imageLC.setY(p.y * lcy / bitmap.getHeight() - 100);
                                            imageLC.setLayoutParams(new RelativeLayout.LayoutParams( 200, 200));
                                            RelativeLayout_main.addView(imageLC);

                                            ImageView imageRC = new ImageView(mContext);
                                            imageRC.setImageResource(R.drawable.rw);
                                            imageRC.setX(p.x * rcx / bitmap.getWidth() - 100);
                                            imageRC.setY(p.y * rcy / bitmap.getHeight() - 100);
                                            imageRC.setLayoutParams(new RelativeLayout.LayoutParams( 200, 200));
                                            RelativeLayout_main.addView(imageRC);

                                        }
                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                    }
                                });


    }
}
