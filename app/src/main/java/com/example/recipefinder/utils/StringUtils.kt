package com.example.recipefinder.utils

fun String.formatString():String{
    val formattedString=this.replace("<b>","").replace("</b>","")
    return formattedString
}