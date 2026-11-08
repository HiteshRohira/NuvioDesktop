package com.nuvio.app.core.ui

import coil3.ComponentRegistry
import coil3.ImageLoader
import coil3.memory.MemoryCache

// Decoded artwork is native Skia memory; keep the hot set small and let Coil's
// existing disk cache handle artwork that is no longer visible.
private const val ImageMemoryCacheBytes = 64L * 1024 * 1024

internal actual val platformProvidesImageLoader: Boolean = false

internal actual fun ImageLoader.Builder.configurePlatformImageLoader(): ImageLoader.Builder {
    return memoryCache {
        MemoryCache.Builder()
            .maxSizeBytes(ImageMemoryCacheBytes)
            .build()
    }
}

internal actual fun ComponentRegistry.Builder.addPlatformDecoders() {
    add(SkiaGifDecoder.Factory())
    add(SvgEmbeddedImageDecoder.Factory())
}
