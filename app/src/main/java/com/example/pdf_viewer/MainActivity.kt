package com.example.pdf_viewer

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pdf_viewer.ui.theme.Pdf_viewerTheme
import com.pspdfkit.configuration.activity.PdfActivityConfiguration
import com.pspdfkit.ui.PdfActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            setContentView(R.layout.activity_main)

            //val url= Uri.parse("file:///android_asset/sample.pdf")
            //val config = PdfActivityConfiguration.Builder(this).build()
            //PdfActivity.showDocument(this, url, config)

            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "application/pdf"
            }

            //startActivityForResult(intent, PICK_PDF_FILE)
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                onDone(result)
            }.launch(intent)
        }
        /*
            override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
                super.onActivityResult(requestCode, resultCode, resultData)
                if(requestCode == PICK_PDF_FILE && resultCode == Activity.RESULT_OK){
                    resultData?.data?.also {uri ->
                        val documentUri = Uri.parse(uri.toString())
                        val config = PdfActivityConfiguration.Builder(this).build()
                        PdfActivity.showDocument(this, documentUri, config)
                    }
                }
            }*/
        private fun onDone(result: ActivityResult) {
            if(result.resultCode == Activity.RESULT_OK) {
                val resultData = result.data
                resultData?.data?.also { uri ->
                    val documentUri = Uri.parse(uri.toString())
                    val config = PdfActivityConfiguration.Builder(this).build()
                    PdfActivity.showDocument(this, documentUri, config)
                }
            }
        }
    }
