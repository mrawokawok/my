package com.my.meme;

import android.accessibilityservice.AccessibilityButtonController;
import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class VolumeButtonService extends AccessibilityService {

    private static final String TAG = "VolumeService";
    private AccessibilityButtonController buttonController;
    private AccessibilityButtonController.AccessibilityButtonCallback buttonCallback;

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();

        buttonController = getAccessibilityButtonController();

        buttonCallback = new AccessibilityButtonController.AccessibilityButtonCallback() {
            @Override
            public void onClicked(AccessibilityButtonController controller) {
                AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                if (audioManager != null) {
                    audioManager.adjustStreamVolume(
                        AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_SAME,
                        AudioManager.FLAG_SHOW_UI
                    );
                }
            }

            @Override
            public void onAvailabilityChanged(AccessibilityButtonController controller, boolean available) {
                Log.d(TAG, "Button available: " + available);
            }
        };

        buttonController.registerAccessibilityButtonCallback(buttonCallback);
        Log.d(TAG, "Layanan Terhubung!");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {}

    @Override
    public void onInterrupt() {}
}