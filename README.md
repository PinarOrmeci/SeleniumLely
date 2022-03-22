### Build with

* **Selenium**  : Browser automation framework
* **Maven** : Dependency management
* **Junit5** : Testing framework
* **WebDriverManager** : Local driver binary management



#### Running Tests With IntelliJ IDE

Clone repo and after opening project, click play button in ide.

![image-20220322161020788](/Users/pinar.ormeci/Library/Application Support/typora-user-images/image-20220322161020788.png)

#### Running Tests With Maven Command

###### Running single test or test class:

mvn surefire:test -Dtest=**testName or className**

<u>Example :</u>  mvn surefire:test -Dtest=TechnicalDocumentsTest

###### Running tests with tag :

mvn surefire:test -groups=**tagName**

<u>Example :</u>  mvn surefire:test -Dtest=technicalDocuments

![image-20220322161550257](/Users/pinar.ormeci/Library/Application Support/typora-user-images/image-20220322161550257.png)

![image-20220322161520667](/Users/pinar.ormeci/Library/Application Support/typora-user-images/image-20220322161520667.png)



##### Parallelism :

Tests can run parallel. This is achieved by using junit5 framework.

In *junit-platform.properties* file :

```
junit.jupiter.execution.parallel.enabled=true
junit.jupiter.execution.parallel.config.fixed.parallelism=2
```

