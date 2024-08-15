package ru.clevertec.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import ru.clevertec.exception.TagAlreadyExistsException

class CheckGitTagExistenceTask extends DefaultTask {
    private static String CURRENT_TAG = "git tag --points-at HEAD"

    @TaskAction
    void gitTagExistence() {
        Optional.ofNullable(CURRENT_TAG.execute().text.trim())
                .filter (it -> !it.isEmpty())
                .ifPresent ({ tag -> throw new TagAlreadyExistsException(tag)})
    }
}
