#include "TextureAsset.h"
#include <android/asset_manager.h>
#include <android/imagedecoder.h>
#include <GLES2/gl2.h>
#include <memory>
#include <vector>
#include <cassert>
#include <string>

std::shared_ptr<TextureAsset>
TextureAsset::loadAsset(AAssetManager *assetManager, const std::string &assetPath) {
    auto pAndroidRobotPng = AAssetManager_open(
            assetManager,
            assetPath.c_str(),
            AASSET_MODE_BUFFER);

    if (!pAndroidRobotPng) {
        return nullptr;
    }

    AImageDecoder *pAndroidDecoder = nullptr;
    auto result = AImageDecoder_createFromAAsset(pAndroidRobotPng, &pAndroidDecoder);
    assert(result == ANDROID_IMAGE_DECODER_SUCCESS);

    AImageDecoder_setAndroidBitmapFormat(pAndroidDecoder, ANDROID_BITMAP_FORMAT_RGBA_8888);

    const AImageDecoderHeaderInfo *pAndroidHeader = AImageDecoder_getHeaderInfo(pAndroidDecoder);

    auto width = AImageDecoderHeaderInfo_getWidth(pAndroidHeader);
    auto height = AImageDecoderHeaderInfo_getHeight(pAndroidHeader);
    auto stride = AImageDecoder_getMinimumStride(pAndroidDecoder);

    // Asignación de memoria limpia usando std::vector
    std::vector<uint8_t> androidImageData(height * stride);

    auto decodeResult = AImageDecoder_decodeImage(
            pAndroidDecoder,
            androidImageData.data(),
            stride,
            androidImageData.size());
    assert(decodeResult == ANDROID_IMAGE_DECODER_SUCCESS);

    AImageDecoder_delete(pAndroidDecoder);
    AAsset_close(pAndroidRobotPng);

    GLuint textureId;
    glGenTextures(1, &textureId);
    glBindTexture(GL_TEXTURE_2D, textureId);

    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

    glTexImage2D(
            GL_TEXTURE_2D,
            0,
            GL_RGBA,
            width,
            height,
            0,
            GL_RGBA,
            GL_UNSIGNED_BYTE,
            androidImageData.data()
    );

    return std::make_shared<TextureAsset>(textureId);
}