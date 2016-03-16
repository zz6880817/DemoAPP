package com.example.administrator.imagedemo;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;

public class MainActivity extends AppCompatActivity {
      private SimpleDraweeView imgView;
    public static final String CLOSEURL = "https://img.alicdn.com/bao/uploaded/i3/779430402/TB2YmYglpXXXXXQXpXXXXXXXXXX_!!779430402.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressiveJpegConfig config = new ProgressiveJpegConfig() {
            @Override
            public int getNextScanNumberToDecode(int i) {
                return 3;
            }

            @Override
            public QualityInfo getQualityInfo(int i) {
                return null;
            }
        };

        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setProgressiveJpegConfig(config)
                .build();
        Fresco.initialize(getApplicationContext(), imagePipelineConfig);

        Postprocessor redMeshPostprocessor = new BasePostprocessor() {
            @Override
            public String getName() {
                return "redMeshPostprocessor";
            }

            @Override
            public void process(Bitmap bitmap) {
                for (int x = 0; x < bitmap.getWidth(); x+=2) {
                    for (int y = 0; y < bitmap.getHeight(); y+=2) {
                        bitmap.setPixel(x, y, Color.RED);
                    }
                }
            }
        };



        imgView = (SimpleDraweeView) findViewById(R.id.img);
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(CLOSEURL))
                .setProgressiveRenderingEnabled(true)
                .setPostprocessor(redMeshPostprocessor)
                .build();
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder().setImageRequest(request)
                .setOldController(imgView.getController())
                .build();

        imgView.setController(controller);


//        imgView.setImageURI(Uri.parse(CLOSEURL));


//        ControllerListener listener = new BaseControllerListener(){
//            @Override
//            public void onFinalImageSet(String id, Object imageInfo, Animatable animatable) {
//                super.onFinalImageSet(id, imageInfo, animatable);
//                Log.d("CXX","id:"+id+"imageinfo:"+imageInfo+"abunatable:"+animatable);
//            }
//
//            @Override
//            public void onFailure(String id, Throwable throwable) {
//                super.onFailure(id, throwable);
//            }
//
//            @Override
//            public void onIntermediateImageFailed(String id, Throwable throwable) {
//                super.onIntermediateImageFailed(id, throwable);
//                Log.d("CXX", "onIntermediateImageFailed:id:" + id);
//            }
//        };
//        DraweeController controlle1r = Fresco.newDraweeControllerBuilder()
//                .setUri(Uri.parse(CLOSEURL))
//                .setControllerListener(listener)
//                .build();
//        imgView.setController(controller);
    }
}
