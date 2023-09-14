package com.solera.characters.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.solera.characters.R
import com.solera.characters.model.CharactersItem
import com.solera.characters.view.fragments.CharacterBottomSheetFragment


class CharacterAdapter(private val fragmentManager: FragmentManager) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var characterList: MutableList<CharactersItem> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(characters: List<CharactersItem>) {
        characterList.clear()
        characterList.addAll(characters)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.character_preview, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characterList[position]
        holder.bind(character)

        holder.itemView.setOnClickListener {
            val bottomSheetFragment = CharacterBottomSheetFragment(character)
            bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
        }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }


    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tvName)
        private val actorTextView: TextView = itemView.findViewById(R.id.tvActorName)
        private val dobTextView: TextView = itemView.findViewById(R.id.tvDOB)
        private val imageView: ImageView = itemView.findViewById(R.id.ivCharacterImage)


        @SuppressLint("SetTextI18n")
        fun bind(character: CharactersItem) {
            nameTextView.text = "Name : ${character.name}"
            actorTextView.text = "Actor Name : ${character.actor}"
            dobTextView.text = "DOB : ${character.dateOfBirth}"

            // Load character image using Picasso (or Glide)
            Glide.with(itemView.context).load(character.image)
                .placeholder(R.drawable.error_image_placeholder).into(imageView)
        }
    }

}



