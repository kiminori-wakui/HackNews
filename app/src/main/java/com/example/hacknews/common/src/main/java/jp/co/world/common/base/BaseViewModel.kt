package jp.co.world.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


abstract class BaseViewModel : ViewModel() {
    //job的列表
    private val jobs: MutableList<Job> = mutableListOf<Job>()
    val isLoading = MutableLiveData<Boolean>()

    protected fun serverAwait(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
        isLoading.value = true
        block.invoke(this)
        isLoading.value = false
    }.addTo(jobs)

    override fun onCleared() {
        jobs.forEach { it.cancel() }
        super.onCleared()
    }
}

private fun Job.addTo(list: MutableList<Job>) {
    list.add(this)
}