package ru.clevertec.plugin

import ru.clevertec.ext.TagExtension
import ru.clevertec.task.*
import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleTestPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.extensions.create("tagExt", TagExtension)

        project.tasks.register("checkGitInstalledTask", CheckGitInstalledTask) {
            group = "utilTasks"
            description = "Checks that git is installed"
        }
        project.tasks.register("checkGitTagExistenceTask", CheckGitTagExistenceTask) {
            group = "utilTasks"
            description = "Checks current tag existence"

            dependsOn("checkGitInstalledTask")
        }
        project.tasks.register("collectGitStatisticsTask", CollectGitStatisticsTask) {
            group = "utilTasks"
            description = "Collects git info : current branch name, last tag"

            dependsOn("checkGitInstalledTask")
        }
        project.tasks.register("checkUncommitedChangesTask", CheckUncommitedChangesTask) {
            group = "utilTasks"
            description = "Throws an exception, if project contains uncommited changes"

            dependsOn("collectGitStatisticsTask")
        }

        project.tasks.register("gitTagPushTask", GitTagPushTask) {
            group = "tag versioning"
            description = "Creates a new version tag and pushes it to origin"

            dependsOn("checkUncommitedChangesTask")

            dependsOn("checkGitTagExistenceTask")
        }
    }
}
