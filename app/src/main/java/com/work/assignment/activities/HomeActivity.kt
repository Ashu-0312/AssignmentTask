package com.work.assignment.activities

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.p_bee.retrofit.Response
import com.work.assignment.R
import com.work.assignment.adapter.ImageListAdapter
import com.work.assignment.application.MyApplication
import com.work.assignment.baseUtils.BaseActivity
import com.work.assignment.databinding.ActivityHomeBinding
import com.work.assignment.dialogs.DetailDialog
import com.work.assignment.factory.MainViewModelFactory
import com.work.assignment.interfaces.RecyclerItemClickListener
import com.work.assignment.model.ListResModelItem
import com.work.assignment.utils.ProgressDialog
import com.work.assignment.utils.toast
import com.work.assignment.viewmodel.ImageViewModel
import javax.inject.Inject

class HomeActivity : BaseActivity(), RecyclerItemClickListener {
    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(
            layoutInflater
        )
    }

    private lateinit var viewModel: ImageViewModel
    private var layoutManager: LinearLayoutManager? = null

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val PAGE_SIZE = 10
    private var page: Int = 1

    //check, list load first time or after page refresh or with pagination
    private var type: String = "new"
    private var isLoading = false
    private var isLastPage: Boolean = false
    private val imageAdapter by lazy { ImageListAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setWhite()
        (application as MyApplication).applicationComponent.inject(this)
        viewModel = ViewModelProvider(this, mainViewModelFactory)[ImageViewModel::class.java]
        setAdapter()
        initView()
        setObserver()
    }

    private fun initView() {

        //refreshing the page with swipe
        binding.swipeRefresh.setColorSchemeResources(R.color.purple_200)
        binding.swipeRefresh.setOnRefreshListener {
            type = "refresh"
            resetData()
            binding.swipeRefresh.isRefreshing = false
        }
    }


    private fun setAdapter() {
        layoutManager = LinearLayoutManager(this)
        binding.listRecycler.layoutManager = layoutManager
        binding.listRecycler.adapter = imageAdapter
        binding.listRecycler.addOnScrollListener(recyclerViewOnScrollListener)
        getData()
    }

    private val recyclerViewOnScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // your pagination code
                val visibleItemCount = layoutManager?.childCount
                val totalItemCount = layoutManager?.itemCount
                val firstVisibleItemPosition = layoutManager?.findFirstVisibleItemPosition()
                if (!isLoading && !isLastPage) {
                    type = "pagination"
                    if (visibleItemCount!! + firstVisibleItemPosition!! >= totalItemCount!! && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                        page += 1
                        isLoading = true
                        getData()
                    }
                }
            }
        }

    private fun resetData() {
        page = 1
        isLoading = false
        isLastPage = false
        getData()
    }

    private fun getData() {
        viewModel.imageList(
            page,
            PAGE_SIZE
        )
    }

    private fun setObserver() {
        viewModel.imageListResponse.observe(this, Observer {
            when (it) {
                is Response.Loading -> {
                    if (type == "new")
                        ProgressDialog.showLoader(supportFragmentManager)
                    else if (type == "pagination")
                        binding.progressBar.visibility = View.VISIBLE
                }

                is Response.Success -> {
                    if (type == "new")
                        ProgressDialog.dismissLoader(supportFragmentManager)
                    else if (type == "pagination")
                        binding.progressBar.visibility = View.GONE
                    it.data?.let {
                        if (page == 1) {
                            imageAdapter.clearList()
                        }
                        isLastPage = it.size < PAGE_SIZE
                        isLoading = false
                        imageAdapter.setData(
                            it
                        )

                    }
                }

                is Response.Error -> {
                    if (type == "new")
                        ProgressDialog.dismissLoader(supportFragmentManager)
                    else if (type == "pagination")
                        binding.progressBar.visibility = View.GONE
                    toast(it.errorMessage)
                }
            }
        })
    }

    override fun onRecyclerItemClick(pos: Int, data: Any, type: String, view: View) {
        //for show item details
        //there was no key for description in the api response that why i am showing author name in the dialog and also in the list
        if (type == "details") {
            DetailDialog.newInstance(data as ListResModelItem)
                .show(supportFragmentManager, "details")
        }
    }
}