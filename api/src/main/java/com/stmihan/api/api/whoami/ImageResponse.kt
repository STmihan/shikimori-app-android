package com.stmihan.api.api.whoami

import com.stmihan.core.domain.models.Image

data class ImageResponse(
    val x148: String?,
    val x16: String?,
    val x160: String?,
    val x32: String?,
    val x48: String?,
    val x64: String?,
    val x80: String?
) {
    fun toImage(): Image {
        return Image(
            x148 = x148,
            x16 = x16,
            x160 = x160,
            x32 = x32,
            x48 = x48,
            x64 = x64,
            x80 = x80
        )
    }
}