package ru.clevertec.ext

import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property

interface TagExtension {
    Property<String> getTagVersion();

    ListProperty<String> getAllTags();
}