package com.devarthur.easyshave.dataModel

data class UserProfile(var uid: String,
                       var username : String,
                       var useremail : String,
                       var userbirthdate : String,
                       var userType : String){




    constructor() : this("", "", "", "", "")
}