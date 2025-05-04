package com.nhatnguyenba.ml_kit.di

import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.label.ImageLabeler
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.nhatnguyenba.ml_kit.data.BarcodeRepositoryImpl
import com.nhatnguyenba.ml_kit.data.ImageLabelingRepositoryImpl
import com.nhatnguyenba.ml_kit.data.TranslationRepositoryImpl
import com.nhatnguyenba.ml_kit.domain.repository.BarcodeRepository
import com.nhatnguyenba.ml_kit.domain.repository.ImageLabelingRepository
import com.nhatnguyenba.ml_kit.domain.repository.TranslationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBarcodeScanner(): BarcodeScanner {
        return BarcodeScanning.getClient(
            BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
                .build()
        )
    }

    @Provides
    @Singleton
    fun provideImageLabeler(): ImageLabeler {
        return ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)
    }

    @Provides
    @Singleton
    fun provideTranslator(): Translator {
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.VIETNAMESE)
            .build()
        return Translation.getClient(options)
    }

    @Provides
    @Singleton
    fun provideBarcodeRepository(barcodeScanner: BarcodeScanner): BarcodeRepository {
        return BarcodeRepositoryImpl(barcodeScanner)
    }

    @Provides
    @Singleton
    fun provideImageLabelingRepository(imageLabeler: ImageLabeler): ImageLabelingRepository {
        return ImageLabelingRepositoryImpl(imageLabeler)
    }

    @Provides
    @Singleton
    fun provideTranslatorRepository(translator: Translator): TranslationRepository {
        return TranslationRepositoryImpl(translator)
    }
}