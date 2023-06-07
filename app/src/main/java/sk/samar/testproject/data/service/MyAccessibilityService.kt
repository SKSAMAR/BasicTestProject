package sk.samar.testproject.data.service

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

class WhatsAppAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        // Check if the event is triggered by opening WhatsApp
        if (event.packageName?.toString() == "com.whatsapp" && event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            // Display a toast message
            Toast.makeText(applicationContext, "WhatsApp Launched.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onInterrupt() {
        // This method is called when the service is interrupted.
    }

    override fun onServiceConnected() {
        // This method is called when the service is connected.
    }
}

