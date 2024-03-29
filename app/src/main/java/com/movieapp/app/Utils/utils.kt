package com.movieapp.app.Utils

import android.app.ProgressDialog
import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.Patterns
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.movieapp.app.R
import java.util.*

class utils {


    /**
     *This class is use for to all common functionality.
     * */

    class Util {

        companion object {

            const val api_key = "c9856d0cb57c3f14bf75bdc6c063b8f3"
            private var dialog: ProgressDialog? = null

            /**
             *  this function is use for adjustFontScale
             *  */
            fun adjustFontScale(configuration: Configuration, context: Context) {
                configuration.fontScale = 1.0.toFloat()
                val metrics: DisplayMetrics = context.resources.displayMetrics
                val wm: WindowManager =
                    context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                wm.getDefaultDisplay().getMetrics(metrics)
                metrics.scaledDensity = configuration.fontScale * metrics.density
                context.getResources().updateConfiguration(configuration, metrics)
            }

            /**
             * this function is use for Toast
             * */
            fun DebugToast(context: Context, message: String) {
                //if (BuildConfig.DEBUG) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                //}
            }

            /**
             * this function is use for Language Selection
             * */
            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            fun setLanguage(
                contexts: Context,
                language: String?
            ) {

                val res = contexts.resources
                val dm = res.displayMetrics
                val conf = res.configuration
                conf.setLocale(Locale(language))
                res.updateConfiguration(conf, dm)

            }

            /**
             * this function is use for Progress dialog
             * */
            fun ShowProgress(context: Context) {
                dialog = ProgressDialog(context, R.style.MyAlertDialogStyle);
                dialog!!.setMessage(context.getString(R.string.Please_wait));
                dialog!!.setCancelable(false)
                dialog!!.show();
            }

            fun HideProgress() {
                if (dialog!!.isShowing) {
                    dialog!!.dismiss()
                }
            }

            /**
             * this function is use for check internet connection
             * */
            fun isConnect(context: Context): Boolean {
                var isConnected = false
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val capabilities =
                        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    if (capabilities != null) {
                        when {
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                                isConnected = true
                            }
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                                isConnected = true
                            }
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                                isConnected = true
                            }
                        }
                    }
                } else {
                    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
                    isConnected = activeNetwork?.isConnectedOrConnecting == true

                }
                if (!isConnected) {
                    HideProgress()
                    DebugToast(context, context.getString(R.string.check_internet))
                }
                return isConnected
            }

            /**
             *this function is use for check empty String
             * */
            fun Any?.checkEmptyString(): Boolean {
                return if (this == null)
                    true
                else
                    TextUtils.isEmpty(this.toString())
            }

            /**
             * to check email pattern
             * */
            fun Any?.checkEmailPattern(): Boolean {
                return !Patterns.EMAIL_ADDRESS.matcher(this.toString()).matches()
            }

        }
    }
}

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(context)
        .load("https://image.tmdb.org/t/p/w500$imageUrl")
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
        .into(this)
}