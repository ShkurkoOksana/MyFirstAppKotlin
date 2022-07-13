package com.bignerdranch.android.notebookexample.model

class Item(title: String = "", content: String = "") {
    var title = title
        get() { return field }

    var content = content
        get() { return field }
}