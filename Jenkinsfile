node('docker') {
    sh 'env'
    stage 'Checkout'
    // Get some code from a GitHub repository
    checkout scm

    stage 'Build'
    def mvnHome = tool 'Maven 3.3.9'
    sh "${mvnHome}/bin/mvn -B install site sonar:sonar -Dsonar.branch=${env.BRANCH_NAME}"

    stage 'Test'
    step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml', allowEmptyResults: true])

    stage 'Production'
    step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar, **/target/*.war, **/target/*.ear', fingerprint: true])
    //step([$class: 'JavadocArchiver', javadocDir: '/target/site/apidocs', keepAll: false])
    step([$class: 'FindBugsPublisher', isRankActivated: true])
    step([$class: 'CheckStylePublisher'])
    step([$class: 'DryPublisher'])
    step([$class: 'PmdPublisher'])
    step([$class: 'TasksPublisher', pattern: '**/*.*', ignoreCase: true, asRegexp: false, high: 'FIXME', normal: 'TODO', low: '@Deprecated'])
    step([$class: 'WarningsPublisher', consoleParsers: [[parserName: 'Maven'], [parserName: 'Java Compiler (javac)'], [parserName: 'JavaDoc Tool']]])
    step([$class: 'AnalysisPublisher'])
}
