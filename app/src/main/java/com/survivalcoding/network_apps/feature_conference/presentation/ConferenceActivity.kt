package com.survivalcoding.network_apps.feature_conference.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.survivalcoding.network_apps.R

class ConferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conference)


/*      queries가 없으면 com.example.std4가 안나온다.
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val ris : List<ResolveInfo> = packageManager.queryIntentActivities(intent, 0)
        for (ri in ris){
            println(ri.activityInfo.packageName)
        }
 */
        /* package visibility need when launch user app
        val test = packageManager.getPackageInfo("com.example.std4",0)

        val i = packageManager.getLaunchIntentForPackage("com.example.std4")
        startActivity(i)
         */
    }
}