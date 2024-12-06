package com.example.moneytherapy.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle

fun Activity.recreateWithAnimation() {
    val intent = Intent(this, this::class.java)
    val bundle = Bundle()
    // Preserva o estado atual
    intent.putExtra("state", bundle)

    // Configura a animação de fade
    finish()
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    startActivity(intent)
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
}