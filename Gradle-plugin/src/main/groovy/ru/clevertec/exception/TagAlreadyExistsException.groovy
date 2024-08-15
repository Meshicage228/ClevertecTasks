package ru.clevertec.exception

import org.gradle.api.GradleException

class TagAlreadyExistsException extends GradleException {

    TagAlreadyExistsException(String tagVersion) {
        super("You cannot add tag, because project has tag ${tagVersion} already")
    }
}
