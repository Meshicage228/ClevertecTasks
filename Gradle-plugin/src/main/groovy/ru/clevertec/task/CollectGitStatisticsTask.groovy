package ru.clevertec.task

import ru.clevertec.ext.TagExtension
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class CollectGitStatisticsTask extends DefaultTask {
    private static String ALL_TAGS = "git tag -l"
    private static String CURRENT_BRANCH_NAME = "git rev-parse --abbrev-ref HEAD"

    @TaskAction
    void collectStatistics() {
        def myExt = project.extensions.getByType(TagExtension)
        def allTags = ALL_TAGS.execute().text
                .split('\n')
                .collect {it.trim()}
        myExt.allTags.set(allTags)

        def branchName = CURRENT_BRANCH_NAME.execute().text.trim()

        def maxTag = allTags.stream()
                .max(Comparator.naturalOrder())
                .orElse("v0.0")

        def versionArray = maxTag.replace('v', '').split('\\.').collect { it as int }
        myExt.tagVersion.set(getVersion(versionArray as int[], branchName))
    }

    def getVersion(int[] arr, branch) {
        def major = arr[0]
        def minor = arr[1]

        switch (branch) {
            case "dev":
            case "qa":
                minor++
                break
            case "stage":
                minor++
                return "${major}.${minor}-rc"
            case "master":
                minor = 0
                major++
                break
            default:
                return "${major}.${minor}-SNAPSHOT"
        }

        "v${major}.${minor}"
    }
}
