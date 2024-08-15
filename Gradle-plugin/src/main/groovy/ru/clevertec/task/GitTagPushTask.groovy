package ru.clevertec.task

import ru.clevertec.ext.TagExtension
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class GitTagPushTask extends DefaultTask {
    @TaskAction
    void gitTagPush() {
        def myExt = project.extensions.getByType(TagExtension)
        def tagToPush = myExt.getTagVersion().get()

        if (!myExt.getAllTags().get().contains(tagToPush)) {
            "git tag ${tagToPush}".execute()
            "git push origin ${tagToPush}".execute()
        }
    }
}
