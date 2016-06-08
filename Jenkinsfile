node('docker') {
    sh 'env'
    stage 'Checkout'
    // Get some code from a GitHub repository
    checkout scm

    stage 'Build'
    def mvnHome = tool 'Maven 3.3.9'
    sh "${mvnHome}/bin/mvn -B install sonar:sonar -Dsonar.branch=${env.JOB_NAME}"

    stage 'Test'
    step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml', allowEmptyResults: true])

    stage 'Production'
    step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar, **/target/*.war, **/target/*.ear', fingerprint: true])
    //step([$class: 'JavadocArchiver', javadocDir: '/target/site/apidocs', keepAll: false])
    step([$class: 'FindBugsPublisher', pattern: '**/*.*', isRankActivated: true])
    step([$class: 'CheckStylePublisher', pattern: '**/*.*'])
    step([$class: 'DryPublisher', pattern: '**/*.*'])
    step([$class: 'PmdPublisher', pattern: '**/*.*'])
    step([$class: 'TasksPublisher', pattern: '**/*.*', ignoreCase: true, asRegexp: false, high: 'FIXME', normal: 'TODO', low: '@Deprecated'])
    step([$class: 'AnalysisPublisher'])
}
