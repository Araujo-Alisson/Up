package com.agenda.up

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Parcel
import android.os.Parcelable

class Notificacao() : Service() {
    constructor(parcel: Parcel) : this()

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Notificacao> {
        override fun createFromParcel(parcel: Parcel): Notificacao {
            return Notificacao(parcel)
        }

        override fun newArray(size: Int): Array<Notificacao?> {
            return arrayOfNulls(size)
        }
    }
}
