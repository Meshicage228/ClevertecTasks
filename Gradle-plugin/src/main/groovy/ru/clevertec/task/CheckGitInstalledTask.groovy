package ru.clevertec.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import ru.clevertec.exception.GitNotInstalledException

class CheckGitInstalledTask extends DefaultTask{
    private static String CURRENT_GIT_VERSION = "git --version"

    @TaskAction
    void gitInstalledTask(){
        def waitFor = CURRENT_GIT_VERSION.execute().waitFor()
        if (waitFor != 0) {
            throw new GitNotInstalledException()
        }
    }
}
