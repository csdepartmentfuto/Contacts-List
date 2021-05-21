package com.example.android.contacts_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.contacts_list.databinding.ContactsBinding

class FamilyActivity : AppCompatActivity() {

    private lateinit var binding: ContactsBinding
    private val familyAdapter = ContactAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpContact()
    }

    private fun setUpContact() {

        val gridLayoutManger = GridLayoutManager(this, 2)
        val familyRecyclerView = binding.contactsRv

        familyRecyclerView.layoutManager = gridLayoutManger
        familyRecyclerView.adapter = familyAdapter

        val builder = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.add_contact_dialogue, null)
        builder.setView(view)

        val alertDialog = builder.create()

        binding.addFab.setOnClickListener {
            alertDialog.show()
        }

        val name = view.findViewById<TextView>(R.id.nameEt)
        val phoneNo = view.findViewById<TextView>(R.id.phoneNoEt)
        val saveButton = view.findViewById<TextView>(R.id.saveBtn)

        phoneNo.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                saveButton.isEnabled = s?.length == 11
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        saveButton.setOnClickListener {
            val contact = Contact(name.text.toString(), phoneNo.text.toString())
            val contacts = mutableListOf(contact)
            familyAdapter.setUpData(contacts)
            alertDialog.dismiss()
        }
    }

}