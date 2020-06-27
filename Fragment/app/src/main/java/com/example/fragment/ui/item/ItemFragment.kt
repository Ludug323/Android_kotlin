package com.example.fragment.ui.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.fragment.R

class ItemFragment : Fragment() {

    private lateinit var itemModel: ItemModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        itemModel =
            ViewModelProviders.of(this).get(ItemModel::class.java)
        val root = inflater.inflate(R.layout.fragment_item, container, false)
        val textView: TextView = root.findViewById(R.id.text_item)
            itemModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
