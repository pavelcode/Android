package com.cblue.event;

import java.util.ArrayList;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.Toast;

import com.cblue.android.R;

public class GestureEventActivity extends Activity implements OnGesturePerformedListener
{
	 GestureLibrary mLibrary;  
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.gesture);  
	        GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);  
	        gestures.addOnGesturePerformedListener(this);  
	        mLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);  
	        if (!mLibrary.load()) {  
	            finish();  
	        }  
	    }  
	  
	    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {  
	        ArrayList predictions = mLibrary.recognize(gesture);  
	  
	        // We want at least one prediction  
	        if (predictions.size() > 0) {  
	            Prediction prediction = (Prediction) predictions.get(0);  
	            // We want at least some confidence in the result  
	            if (prediction.score > 1.0) {  
	                // Show the spell  
	                Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT).show();  
	            }  
	        }  
	          
	    }  
}
