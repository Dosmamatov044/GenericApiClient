package com.example.genericapiclient.rv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.genericapiclient.R
import com.example.genericapiclient.model.GenericModel
import kotlin.random.Random


class GenericAdapter(private var list: MutableList<GenericModel>, private var context: Context) :
        RecyclerView.Adapter<GenericAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
                R.layout.generic_item,
                parent,
                false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.typeText.text = list[0][0].type
        holder.setupText.text = list[0][0].setup
        holder.punchlineText.text = list[0][0].punchline

        holder.buttonR.setOnClickListener {
            val animTypeText: Animation = AnimationUtils.loadAnimation(context, R.anim.animation_first)
            holder.typeText.startAnimation(animTypeText)

            val animSetupText: Animation = AnimationUtils.loadAnimation(context, R.anim.animation_second)
            holder.setupText.startAnimation(animSetupText)

            val animPunchlineText: Animation = AnimationUtils.loadAnimation(context, R.anim.animation_third)
            holder.punchlineText.startAnimation(animPunchlineText)

            val animButton: Animation = AnimationUtils.loadAnimation(context, R.anim.animation_first)
            holder.buttonR.startAnimation(animButton)

            val i: Int = Random.nextInt(1, 10)
            holder.typeText.text = list[0][i].type
            holder.setupText.text = list[0][i].setup
            holder.punchlineText.text = list[0][i].punchline
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var typeText: TextView = view.findViewById(R.id.typeText)
        var setupText: TextView = view.findViewById(R.id.setupText)
        var punchlineText: TextView = view.findViewById(R.id.punchlineText)
        var buttonR: Button = view.findViewById(R.id.randomButton)
    }

    fun update(list: MutableList<GenericModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}

