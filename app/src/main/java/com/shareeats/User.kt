package com.shareeats
 //custom userClass
import android.location.Location
import com.google.firebase.database.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
class User {
    data class user(var name: String? = "",
                    var password: String? = "",
                    var email: String? = "",
                    var phoneNumber: String?="",
                    var uid: String? = "",
                    var volunteer: Boolean?=false,
                    var items: MutableList<Item>?=null

    )
    fun user.editName(newNumber:String){
        this.name=newNumber
    }
    fun user.editNumber(newNumber:String){
        this.phoneNumber=newNumber
    }
    fun user.editMail(newNumber:String){
        this.email=newNumber
    }
    fun user.editVolunteer(newNumber:Boolean){
        this.volunteer=newNumber
    }

    @IgnoreExtraProperties
    data class Item(
        var name: String?="",
        var expiry: Date?=null,
        var donator: String?=null,
        var pickupGuy: String?=null,
        var location: Location?=null
    )

}