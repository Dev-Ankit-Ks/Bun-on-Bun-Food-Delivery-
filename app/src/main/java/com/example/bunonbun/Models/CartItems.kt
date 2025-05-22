package com.example.bunonbun.Models

import android.os.Parcel
import android.os.Parcelable

data class CartItems(
    val FoodName: String? = "",
    val FoodImage : String?= "",
    val FoodPrice: Double? = null,
    val key: String? = "",
    var quantity: Int
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    // Define a function to convert the object to a Map
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "FoodName" to FoodName,
            "FoodImage" to FoodImage,
            "FoodPrice" to FoodPrice,
            "quantity" to quantity.toLong()
        )
    }

    // Implement the Parcelable interface
    // ...
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(FoodName)
        parcel.writeString(FoodImage)
        parcel.writeValue(FoodPrice)
        parcel.writeString(key)
        parcel.writeInt(quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CartItems> {
        override fun createFromParcel(parcel: Parcel): CartItems {
            return CartItems(parcel)
        }

        override fun newArray(size: Int): Array<CartItems?> {
            return arrayOfNulls(size)
        }
    }
}