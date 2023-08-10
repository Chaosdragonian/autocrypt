package com.dlgustjr02124.autocrypt.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class NetworkResponse(
    @SerializedName("currentCount")
    @Expose
    val currentCount: Int,
    @SerializedName("data")
    @Expose
    val data: List<Data>,
    @SerializedName("matchCount")
    @Expose
    val matchCount: Int,
    @SerializedName("page")
    @Expose
    val page: Int,
    @SerializedName("perPage")
    @Expose
    val perPage: Int,
    @SerializedName("totalCount")
    @Expose
    val totalCount: Int
) {
    @Parcelize
    data class Data(
        @SerializedName("address")
        @Expose
        val address: String,
        @SerializedName("centerName")
        @Expose
        val centerName: String,
        @SerializedName("centerType")
        @Expose
        val centerType: String,
        @SerializedName("createdAt")
        @Expose
        val createdAt: String,
        @SerializedName("facilityName")
        @Expose
        val facilityName: String,
        @SerializedName("id")
        @Expose
        val id: Int,
        @SerializedName("lat")
        @Expose
        val lat: String,
        @SerializedName("lng")
        @Expose
        val lng: String,
        @SerializedName("org")
        @Expose
        val org: String,
        @SerializedName("phoneNumber")
        @Expose
        val phoneNumber: String,
        @SerializedName("sido")
        @Expose
        val sido: String,
        @SerializedName("sigungu")
        @Expose
        val sigungu: String,
        @SerializedName("updatedAt")
        @Expose
        val updatedAt: String,
        @SerializedName("zipCode")
        @Expose
        val zipCode: String
    ) : Parcelable
}
