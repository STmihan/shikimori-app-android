package com.stmihan.api.api.whoami


import com.google.gson.annotations.SerializedName
import com.stmihan.core.domain.models.User

data class WhoAmIResponse(
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("birth_on")
    val birthOn: String?,
    @SerializedName("id")
    val id: Long,
    @SerializedName("image")
    val image: ImageResponse?,
    @SerializedName("last_online_at")
    val lastOnlineAt: String?,
    @SerializedName("locale")
    val locale: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("sex")
    val sex: String?,
    @SerializedName("website")
    val website: String?
) {
    fun toUser(): User {
        return User(
            avatar = avatar,
            birthOn = birthOn,
            id = id,
            image = image?.toImage(),
            lastOnlineAt = lastOnlineAt,
            locale = locale,
            name = name,
            nickname = nickname,
            sex = sex,
            website = website
        )
    }
}