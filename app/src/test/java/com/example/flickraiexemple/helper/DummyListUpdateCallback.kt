package com.example.flickraiexemple.helper

import androidx.recyclerview.widget.ListUpdateCallback

@Suppress("EmptyFunctionBlock")
class DummyListUpdateCallback : ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
}
