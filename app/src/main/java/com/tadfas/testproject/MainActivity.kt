package com.tadfas.testproject

import android.app.ProgressDialog
import android.content.ClipboardManager
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loopj.android.http.JsonHttpResponseHandler
import com.tadfas.testproject.databinding.ActivityMainBinding
import com.tadfas.testproject.instaparse.*
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.net.URI

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var data = mutableListOf<VideoModel>()
    private lateinit var adapter: ResultVideoAdapter
    val ISINSTALOGIN = "IsInstaLogin"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        initConfigApp()


        Glide.with(this)
            .load(R.drawable.giphy50)
            .into(binding.imgBanner)

        binding.btnPaste.setOnClickListener {
            try {
                val clipManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clipdata = clipManager.primaryClip
                clipdata?.let {
                    val item = it.getItemAt(0)
                    val text = item.text.toString()
                    binding.etPaste.setText(text)
                    binding.btnPaste.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        val url_download = "https://www.instagram.com/p/CTq9j13KvfC/?utm_medium=copy_link"

        val progressDialog = ProgressDialog(this, R.style.AppCompatProgressDialogStyle)
        progressDialog.setMessage("Extract data...")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.setCancelable(true)
        progressDialog.setCanceledOnTouchOutside(true)

//        if (mPrefs.getInt(ISINSTALOGIN, 0) == 0) {
//            dialogLogin(url_download)
//        } else {
//            progressDialog.show()
//
//            downloadInsta(url_download)
//        }

        var caption = "Chuyên gia viết lời bài hát siêu bắt tai \uD83D\uDE0D\\nNguồn tiktok: @chibrillie99\\n#tranchauden";
        caption = caption.replace("\n", "")
        val videoModel = VideoModel(
            "https://instagram.fhan5-7.fna.fbcdn.net/v/t51.2885-15/e15/243203890_375914467525368_8086397711094079055_n.jpg?_nc_ht=instagram.fhan5-7.fna.fbcdn.net&_nc_cat=100&_nc_ohc=bAo9iIl8qOUAX_KM467&edm=AABBvjUBAAAA&ccb=7-4&oh=c3cf140703a680c06b691c3e4a2e797f&oe=61552875&_nc_sid=83d603",
            caption,
            "https://scontent.cdninstagram.com/v/t50.2886-16/242779432_249996763721703_1443465947745877326_n.mp4?_nc_ht=instagram.fhan5-7.fna.fbcdn.net&_nc_cat=103&_nc_ohc=nV0oi6R35JAAX9rywMA&edm=AABBvjUBAAAA&ccb=7-4&oe=61549DFE&oh=dfe85c40bec123981fffa22123c10b04&_nc_sid=83d603"
        )

        data.add(videoModel)
        data.add(videoModel)
        adapter = ResultVideoAdapter()
        adapter.setData(data)


        binding.recycleView.layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        binding.recycleView.adapter = adapter

//        data.add(StreamOtherInfo("test1",
//            "https://www.instagram.com/p/CUPSJoKroKO/?utm_medium=copy_link",
//            "instagram",
//            arrayOf("https://instagram.fhan5-7.fna.fbcdn.net/v/t50.2886-16/242779432_249996763721703_1443465947745877326_n.mp4?_nc_ht=instagram.fhan5-7.fna.fbcdn.net&_nc_cat=103&_nc_ohc=PiYTcEHd7xUAX__qEL_&edm=AABBvjUBAAAA&ccb=7-4&oe=6153F53E&oh=090af6d76ff72cd6053d2cbe00f99901&_nc_sid=83d603")))
//
//        data.add(StreamOtherInfo("test2",
//            "https://www.instagram.com/p/CUPSJoKroKO/?utm_medium=copy_link",
//            "instagram",
//            arrayOf("https://instagram.fhan5-7.fna.fbcdn.net/v/t50.2886-16/242779432_249996763721703_1443465947745877326_n.mp4?_nc_ht=instagram.fhan5-7.fna.fbcdn.net&_nc_cat=103&_nc_ohc=PiYTcEHd7xUAX__qEL_&edm=AABBvjUBAAAA&ccb=7-4&oe=6153F53E&oh=090af6d76ff72cd6053d2cbe00f99901&_nc_sid=83d603")))


    }

    private fun getUrlWithoutParameters(url: String): String {
        return try {
            val uri = URI(url)
            URI(
                uri.scheme,
                uri.authority,
                uri.path,
                uri.fragment
            ).toString()
        } catch (e: Exception) {
            e.printStackTrace()

            ""
        }
    }

    private fun downloadInsta(url: String) {
        instagramCallback(url, object : MusicallyDelegate {
            override fun OnFailure(str: String?) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Can not extract!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun OnSuccess(musicallyModel: ArrayList<VideoModel>) {
                runOnUiThread {
                    data = musicallyModel

                }
            }
        })
    }

    private fun instagramCallback(str: String, musicallyDelegate: MusicallyDelegate?) {


        val USERID = "user_id"
        val SESSIONID = "session_id"
        var UrlWithoutQP: String = getUrlWithoutParameters(str)
        UrlWithoutQP = "$UrlWithoutQP?__a=1"
        val coockie = "ds_user_id="
//        + mPrefs.getString(USERID, "")
//            .toString() + "; sessionid=" + mPrefs
//            .getString(SESSIONID, "")

        ServiceHandler.get(UrlWithoutQP, null, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                throwable: Throwable,
                errorResponse: JSONObject
            ) {
                super.onFailure(statusCode, headers, throwable, errorResponse)

                musicallyDelegate?.OnFailure("try again later")

            }

            override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONObject) {
                super.onSuccess(statusCode, headers, response)

                val listType = object : TypeToken<ModelResponse?>() {}.type
                val modelResponse: ModelResponse? = Gson().fromJson(response.toString(), listType)
                if (modelResponse != null) {
                    if (modelResponse.modelGraphql != null) {
                        if (modelResponse.modelGraphql!!.shortcode_media != null) {
                            val modelEdgeSidecarToChildren: ModelEdgeSidecarToChildren? =
                                modelResponse.modelGraphql!!.shortcode_media!!.edge_sidecar_to_children


                            val modelEdgeChildren =
                                modelResponse.modelGraphql!!.shortcode_media!!.edge_media_to_caption!!.modelEdgeChildren
                            val caption = modelEdgeChildren!![0].ModelNodeCaption!!.text

                            val videoModel = ArrayList<VideoModel>()
                            if (modelEdgeSidecarToChildren != null) {
                                val modelEdgeChildArrayList: List<ModelEdgeChild> =
                                    modelEdgeSidecarToChildren.modelEdgeChildren!!
                                for (i in modelEdgeChildArrayList.indices) {
                                    if (modelEdgeChildArrayList[i].modelNode!!.isIs_video) {
                                        // fixme get arraylist of VideoUrl here
                                        val videoUrl =
                                            modelEdgeChildArrayList[i].modelNode!!.video_url.toString()
                                        val thumbnail =
                                            modelEdgeChildArrayList[i].modelNode!!.display_url.toString()
                                        val igtvModel = VideoModel()

                                        igtvModel.caption = caption
                                        igtvModel.videoUrl = videoUrl
                                        igtvModel.thumbnail = thumbnail

                                        videoModel.add(igtvModel)
                                        musicallyDelegate?.OnSuccess(videoModel)

                                    } else {
                                        musicallyDelegate?.OnFailure("try again later")
                                    }
//                                    else {
//                                        // fixme get arraylist of images here
//                                        PhotoUrl = modelEdgeArrayList[i].getModelNode()
//                                            .getDisplay_resources().get(
//                                                modelEdgeArrayList[i].getModelNode()
//                                                    .getDisplay_resources().size() - 1
//                                            ).getSrc()
//                                        val igtvModel = CommonModel()
//                                        //                                        igtvModel.setImagePath(PhotoUrl);
//                                        igtvModel.videoPath = PhotoUrl
//                                        igtvModel.title = System.currentTimeMillis().toString()
//                                        val stringBuilder2: String =
//                                            com.allinone.videosaver.free.videodownloader.statussaver.utility.VideoDownload.videoUniquePath + ".jpg"
//                                        igtvModel.videoUniquePath = stringBuilder2
//                                        commonModels.add(igtvModel)
//                                    }
                                }
                            } else {
                                val isVideo: Boolean =
                                    modelResponse.modelGraphql!!.shortcode_media!!.isIs_video
                                if (isVideo) {
                                    val videoUrl =
                                        modelResponse.modelGraphql!!.shortcode_media!!.video_url.toString()
                                    val thumbnail =
                                        modelResponse.modelGraphql!!.shortcode_media!!.display_url.toString()

                                    val igtvModel = VideoModel()

                                    igtvModel.caption = caption
                                    igtvModel.videoUrl = videoUrl
                                    igtvModel.thumbnail = thumbnail
                                    videoModel.add(igtvModel)

                                    musicallyDelegate?.OnSuccess(videoModel)

                                } else {
                                    musicallyDelegate?.OnFailure("try again later")
                                }
//                                else {
//                                    PhotoUrl = modelResponse.getModelGraphql().getShortcode_media()
//                                        .getDisplay_resources()
//                                        .get(
//                                            modelResponse.getModelGraphql().getShortcode_media()
//                                                .getDisplay_resources().size() - 1
//                                        ).getSrc()
//                                    val igtvModel = CommonModel()
//                                    //                                    igtvModel.setImagePath(PhotoUrl);
//                                    igtvModel.videoPath = PhotoUrl
//                                    igtvModel.title = System.currentTimeMillis().toString()
//                                    val stringBuilder2: String =
//                                        com.allinone.videosaver.free.videodownloader.statussaver.utility.VideoDownload.videoUniquePath + ".jpg"
//                                    igtvModel.videoUniquePath = stringBuilder2
//                                    commonModels.add(igtvModel)
//                                }
                            }
                        } else {
                            musicallyDelegate?.OnFailure("try again later")
                            // error
                        }
                    }
                } else {
                    musicallyDelegate?.OnFailure("try again later")
                    //error
                }
            }
        }, true, coockie)
    }

    private fun dialogLogin(url: String) {
        val mBottomSheetDialog = RoundedBottomSheetDialog(this)
        val sheetView = layoutInflater.inflate(R.layout.dialog_rounded_bottom_sheet, null)
        val login = sheetView.findViewById<Button>(R.id.btn_login)
        val tv_insta2 = sheetView.findViewById<TextView>(R.id.insta_2)
        val spannable = SpannableString(getString(R.string.insta_2))
        val indexStart = getString(R.string.insta_2).indexOf(getString(R.string.official_insta))
        val indexEnd = getString(R.string.official_insta).length + indexStart
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            indexStart,
            indexEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tv_insta2.text = spannable
        login.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            intent.putExtra("url_video", url)
            startActivityForResult(intent, 111)
            mBottomSheetDialog.dismiss()
        }
        mBottomSheetDialog.setContentView(sheetView)
        mBottomSheetDialog.show()
    }


}