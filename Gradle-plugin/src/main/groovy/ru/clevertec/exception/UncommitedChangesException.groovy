package ru.clevertec.exception

import org.gradle.api.GradleException

class UncommitedChangesException extends GradleException{

    UncommitedChangesException(String tagVersion) {
        super("Uncommitted changes, skipping tag creation. Version: ${tagVersion}.uncommitted")
    }
}
