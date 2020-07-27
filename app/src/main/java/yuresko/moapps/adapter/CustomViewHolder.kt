package yuresko.moapps.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import yuresko.moapps.R
import yuresko.moapps.mainview.model.ItemModel
import yuresko.moapps.webview.WebViewActivity

class CustomViewHolder(parent: ViewGroup, private val context: Context) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.rv_item, parent, false
        )
    ) {

    private var image: ImageView? = itemView.findViewById(R.id.appIconItem)
    private var appName: TextView? = itemView.findViewById(R.id.textViewAppName)
    private var payStatus: TextView? = itemView.findViewById(R.id.paymentTextView)
    private var redTv: TextView? = itemView.findViewById(R.id.redTextView)
    private val selectedItem: LinearLayout = itemView.findViewById(R.id.linearInCardView)

    fun bind(itemModel: ItemModel) {
//        Picasso.get()
//            .load(itemModel.image)
//            .into(image)
        appName?.text = itemModel.appName
        payStatus?.text = itemModel.isPaid.toString()
        redTv?.text = itemModel.incomplete.toString()

        selectedItem.setOnClickListener {
            val intent = Intent(context, WebViewActivity::class.java)
            context.startActivity(intent)

//            webView!!.settings.javaScriptEnabled = true
//
//
//            webView!!.webViewClient = WebViewClient()
//            webView!!.webChromeClient = WebChromeClient()
//            webView!!.loadUrl(itemModel.appLink)

        }
    }
}