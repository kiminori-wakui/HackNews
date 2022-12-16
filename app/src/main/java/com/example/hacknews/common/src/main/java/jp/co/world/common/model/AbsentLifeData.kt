package jp.co.world.common.model

import androidx.lifecycle.LiveData

class AbsentLifeData<T: Any?> private constructor(): LiveData<T>() {

    init {
        postValue(null)
    }

    companion object {
        fun <T: Any?> create(): LiveData<T> {
            return AbsentLifeData()
        }
    }

}