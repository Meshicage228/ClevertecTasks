package ru.clevertec.task

import ru.clevertec.ext.TagExtension
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import ru.clevertec.exception.UncommitedChangesException

class CheckUncommitedChangesTask extends DefaultTask {
    private static String GIT_STATUS = "git status --porcelain"

    @TaskAction
    void uncommitedChanges() {
        def myExt = project.extensions.getByType(TagExtension)
        def process = GIT_STATUS.execute()

        Optional.ofNullable(process.text.trim())
                .filter(it -> !it.isEmpty())
                .ifPresent({ tag -> throw new UncommitedChangesException(myExt.getTagVersion().get())})
    }
}
