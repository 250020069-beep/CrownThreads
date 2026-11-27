#ifndef TEXTUREASSET_H
#define TEXTUREASSET_H

#include <GLES2/gl2.h>
#include <android/asset_manager.h>
#include <memory>
#include <string>

class TextureAsset {
public:
    explicit TextureAsset(GLuint textureId) : mTextureId(textureId) {}
    ~TextureAsset() = default;

    static std::shared_ptr<TextureAsset> loadAsset(AAssetManager *assetManager, const std::string &assetPath);

    GLuint getTextureId() const { return mTextureId; }

private:
    GLuint mTextureId;
};

#endif // TEXTUREASSET_H