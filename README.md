# Spring Startup Test

Sample app showing differences in Spring startup based on run method.

Pre-conditions:
- using `EntityManagerFactoryBuilder` instead of automatic spring creation
  - `org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration.entityManagerFactory` calls `org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties.applyScanner` disables Hibernate Scanner by default
- having a lot of large files in `/src/main/resource/static/`
  - here auto-generated with `for i in $(seq -w 1 200); do     dd if=/dev/urandom of=filler_$i.bin bs=1K count=1000; done`

Times observed (quite old hardware):
- ran each 3-4 times, times consistent to the first decimal
- executable jar `java -jar spring-startup-test-0.0.1-SNAPSHOT.jar`
  -  `Started SpringStartupTestApplication in 7.248 seconds (process running for 7.856)`
- unpacked jar with `jar -xf spring-startup-test-0.0.1-SNAPSHOT.jar`
- JarLauncher `java org.springframework.boot.loader.launch.JarLauncher`
  - `Started SpringStartupTestApplication in 4.567 seconds (process running for 4.941)`
- natural main method `java -cp "BOOT-INF/classes:BOOT-INF/lib/*" com.example.MyApplication`
  - `Started SpringStartupTestApplication in 4.243 seconds (process running for 4.561)`

Notes:
- depends a lot on actual files both amount and size
- having multiple different persistence context (like for different databases) incurs the Hibernate Scanner cost for each of them
- hibernate scanner can be disabled by calling `.properties(Map.of("hibernate.archive.scanner","org.hibernate.boot.archive.scan.internal.DisabledScanner"))` on EntityManagerFactoryBuilder
- 