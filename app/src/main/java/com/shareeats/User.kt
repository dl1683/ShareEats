package com.shareeats
 //custom userClass
import com.google.firebase.database.IgnoreExtraProperties
@IgnoreExtraProperties
class User {
    data class user(var name: String? = "",
                    var password: String? = "",
                    var email: String? = "",
                    var phoneNumber: String?="",
                    var uid: String? = "",
                    var volunteer: Boolean?=false

    )

}