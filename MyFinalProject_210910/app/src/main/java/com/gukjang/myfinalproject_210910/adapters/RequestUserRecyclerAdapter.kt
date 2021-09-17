package com.gukjang.myfinalproject_210910.adapters

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gukjang.myfinalproject_210910.AddFriendActivity
import com.gukjang.myfinalproject_210910.R
import com.gukjang.myfinalproject_210910.datas.BasicResponse
import com.gukjang.myfinalproject_210910.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestUserRecyclerAdapter(
    val mContext : Context,
    val mList : List<UserData>) : RecyclerView.Adapter<RequestUserRecyclerAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val profileImg = view.findViewById<ImageView>(R.id.profileImg)
        val nicknameTxt = view.findViewById<TextView>(R.id.nicknameTxt)
        val socialLoginImg = view.findViewById<ImageView>(R.id.socialLoginImg)
        val acceptBtn = view.findViewById<Button>(R.id.acceptBtn)
        val refuseBtn = view.findViewById<Button>(R.id.refuseBtn)

        fun bind(context: Context, data : UserData){
            Glide.with(context).load(data.profileImgURL).into(profileImg)
            nicknameTxt.text = data.nickName
            when(data.provider){
                "facebook" -> {
                    socialLoginImg.visibility = View.VISIBLE
                    socialLoginImg.setImageResource(R.drawable.facebook_icon)
                }
                "kakao" -> {
                    socialLoginImg.visibility = View.VISIBLE
                    socialLoginImg.setImageResource(R.drawable.kakaotalk_icon)
                }
                else -> {
                    socialLoginImg.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.request_user_list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(mContext, mList[position])
    }

    override fun getItemCount() = mList.size
}