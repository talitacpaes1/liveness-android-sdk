package br.com.oititec.facecaptchasample

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith




/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

private const val BASIC_SAMPLE_PACKAGE = "br.com.oititec.facecaptchasample"
private const val LAUNCH_TIMEOUT = 5000L
private const val DEFAULT_TIMEOUT = 60000L
private const val EXISTS_TIMEOUT = 5000L

@RunWith(AndroidJUnit4::class)
class CompleteFlowTest {

    private lateinit var device: UiDevice

    @Before
    fun startMainActivityFromHomeScreen() {

        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage: String = device.launcherPackageName
        assertThat(launcherPackage, notNullValue())
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT
        )

        // Launch the app
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(
            BASIC_SAMPLE_PACKAGE)?.apply {
            // Clear out any previous instances
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT
        )
    }

    @Test
    fun executeCompleteFlow() {

        // Opens FaceCaptcha
        val startButton = device.findObject(UiSelector().text("INICIAR"))
        startButton.waitForExists(EXISTS_TIMEOUT)
        startButton.clickAndWaitForNewWindow()

        // Camera permission
        val gotItButton = device.findObject(UiSelector().text("ENTENDI"))
        gotItButton.waitForExists(EXISTS_TIMEOUT)
        if (gotItButton.exists()) {
            gotItButton.click()
            val allowPermissions = device.findObject(UiSelector().clickable(true).checkable(false).index(1))
            allowPermissions.waitForExists(EXISTS_TIMEOUT)
            if (allowPermissions.exists()) {
                allowPermissions.click()
            }
        }

        // Start challenge
        var startFaceCaptchaButton = device.findObject(UiSelector().text("INICIAR"))
        startFaceCaptchaButton.waitForExists(EXISTS_TIMEOUT)
        if (!startFaceCaptchaButton.exists()) {
            // Try to get button again
            startFaceCaptchaButton = device.findObject(UiSelector().className("android.widget.Button").instance(0))
        }
        startFaceCaptchaButton.clickAndWaitForNewWindow(DEFAULT_TIMEOUT)

        // Start single doc flow
        val oneDocText = "1 foto com\nfrente e verso"
        val oneDocButton = device.findObject(UiSelector().text(oneDocText))
        oneDocButton.waitForExists(DEFAULT_TIMEOUT)
        oneDocButton.clickAndWaitForNewWindow()

        // Capture picture
        var captureButton = device.findObject(UiSelector().className("android.widget.ImageButton").instance(0))
        captureButton.waitForExists(DEFAULT_TIMEOUT)
        captureButton.click()

        // Confirm picture
        var confirmImageButton = device.findObject(UiSelector().text("USAR FOTO"))
        confirmImageButton.waitForExists(DEFAULT_TIMEOUT)
        confirmImageButton.clickAndWaitForNewWindow()

        // Send picture
        var sendButton = device.findObject(UiSelector().text("ENVIAR FOTOS"))
        sendButton.waitForExists(EXISTS_TIMEOUT)
        sendButton.clickAndWaitForNewWindow()

        // Start two docs flow
        val twoDocsText = "2 fotos:"
        val twoDocsButton = device.findObject(UiSelector().text(twoDocsText))
        twoDocsButton.waitForExists(DEFAULT_TIMEOUT)
        twoDocsButton.clickAndWaitForNewWindow()

        // Rotate device to landscape
        device.setOrientationLeft()

        // Capture front picture
        captureButton = device.findObject(UiSelector().className("android.widget.ImageButton").instance(0))
        captureButton.waitForExists(DEFAULT_TIMEOUT)
        captureButton.click()
        confirmImageButton = device.findObject(UiSelector().text("USAR FOTO"))
        confirmImageButton.waitForExists(DEFAULT_TIMEOUT)
        confirmImageButton.click()

        // Capture back picture
        captureButton.click()
        confirmImageButton.clickAndWaitForNewWindow()

        // Rotate device to portrait
        device.setOrientationNatural()

        // Send picture
        sendButton = device.findObject(UiSelector().text("ENVIAR FOTOS"))
        sendButton.waitForExists(EXISTS_TIMEOUT)
        sendButton.clickAndWaitForNewWindow()

        // Wait until doc activity appears
        device.wait(Until.hasObject(By.text(oneDocText)), DEFAULT_TIMEOUT)

        // Close doc screen
        device.pressBack()

        // Close app
        device.pressBack()
    }
}
