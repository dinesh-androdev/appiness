package com.appiness.homeScreen

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.appiness.TaskCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val _noDataFound = MutableStateFlow<String?>(null)
    val noDataFound: StateFlow<String?> = _noDataFound

    private val _signInStatus = MutableStateFlow<List<HomeListModelItem>?>(null)
    val signInStatus: StateFlow<List<HomeListModelItem>?> = _signInStatus

    val recyclerViewAdapter: HomeListAdapter = HomeListAdapter()

    private fun setAdapterData(list: List<HomeListModelItem>) {
        recyclerViewAdapter.setDataList(list)
    }

    fun getData() {
        homeRepository.getData(object : TaskCallback<List<HomeListModelItem>> {
                override fun onComplete(result: List<HomeListModelItem>?) {
                    if (result.isNullOrEmpty()) {
                        _noDataFound.value = "No data found"
                    } else {
                        _noDataFound.value = ""
                        _signInStatus.value = result
                        setAdapterData(result)
                    }
                }

                override fun onException(t: Throwable?) {
                    _noDataFound.value = "No data found"
                }
            })
    }

    fun printToken() {
        homeRepository.getFcmToken()
    }
}
