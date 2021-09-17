package com.gukjang.myfinalproject_210910.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gukjang.myfinalproject_210910.R
import com.gukjang.myfinalproject_210910.adapters.RequestUserRecyclerAdapter
import com.gukjang.myfinalproject_210910.databinding.FragmentRequestedUserListBinding
import com.gukjang.myfinalproject_210910.datas.BasicResponse
import com.gukjang.myfinalproject_210910.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestedUserListFragment : BaseFragment() {
    lateinit var binding : FragmentRequestedUserListBinding

    val mRequestUserList = ArrayList<UserData>()

    lateinit var mRequestedUserAdapter : RequestUserRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_requested_user_list, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        mRequestedUserAdapter = RequestUserRecyclerAdapter(mContext, mRequestUserList)
        binding.requestUserRecyclerView.adapter = mRequestedUserAdapter
        binding.requestUserRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }

    override fun onResume() {
        super.onResume()
        getRequestUserListFromServer()
    }

    fun getRequestUserListFromServer(){
        apiService.getRequestFriendList("requested").enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful){
                    val basicResponse = response.body()!!

                    mRequestUserList.clear()
                    mRequestUserList.addAll(basicResponse.data.friends)

                    mRequestedUserAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }

}