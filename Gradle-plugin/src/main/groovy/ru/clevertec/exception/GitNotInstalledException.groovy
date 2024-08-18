package ru.clevertec.exception

import org.gradle.api.GradleException

class GitNotInstalledException extends GradleException{

    GitNotInstalledException() {
        super("Git is not installed")
    }
}
