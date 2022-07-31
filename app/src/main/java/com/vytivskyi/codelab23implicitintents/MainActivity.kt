package com.vytivskyi.codelab23implicitintents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import com.vytivskyi.codelab23implicitintents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mWebsiteEditText: EditText
    private lateinit var mLocationEditText: EditText
    private lateinit var mShareTextEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mWebsiteEditText = binding.websiteEdittext
        mLocationEditText = binding.locationEdittext
        mShareTextEditText = binding.shareEdittext


    }

    fun openWebsite(view: View) {

        val url: String = mWebsiteEditText.text.toString()
        val webpage: Uri = Uri.parse(url)

        val intent = Intent(Intent.ACTION_VIEW, webpage)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("lol", "Can't handle this")
        }
    }

    fun openLocation(view: View) {
        val loc: String = mLocationEditText.text.toString()
        val addressUri = Uri.parse("geo:0,0?q=$loc")

        val intent = Intent(Intent.ACTION_VIEW, addressUri)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent);
        } else {
            Log.d("lol", "Can't handle this intent!");
        }

    }

    fun shareText(view: View) {

        val txt: String = mShareTextEditText.text.toString()
        val mimeType: String = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Share this text with: ")
            .setText(txt)
            .startChooser()

    }
}