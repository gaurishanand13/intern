package com.example.internapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.internapp.DataClass.Article
import com.squareup.picasso.Picasso


interface myAdapterInterface {
    abstract fun onCLick()
}
class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)



class myAdapter(val context: Context,val list: ArrayList<Article>): RecyclerView.Adapter<myViewHolder>() {
    var myInterface : myAdapterInterface? = null




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {


        val layoutInflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.recycler_item, parent, false)
        var courseViewHolder = myViewHolder(view!!)
        return courseViewHolder

    }



    override fun getItemCount() = list.size



    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        val imageView = holder.itemView.findViewById<ImageView>(R.id.image)
        val textView = holder.itemView.findViewById<TextView>(R.id.text)

        textView.text = list.get(position).title
        Picasso.get().load(list.get(position).urlToImage).into(imageView);


        holder.itemView.setOnClickListener {
            val intent = Intent(context,webVieww::class.java)
            intent.putExtra("url",list.get(position).url)
            context.startActivity(intent)
//            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse())
//            context.startActivity(browserIntent)
        }



    }


}
